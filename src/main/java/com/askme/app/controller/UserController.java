package com.askme.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.askme.app.common.ApiResult;
import com.askme.app.common.CodeStatus;
import com.askme.app.common.util.HttpService;
import com.askme.app.common.util.JwtUtils;
import com.askme.app.common.util.MD5Generate;
import com.askme.app.common.util.StringHelp;
import com.askme.app.common.util.WeixinHelper;
import com.askme.app.model.Question;
import com.askme.app.model.UserInfo;
import com.askme.app.service.QuestionService;
import com.askme.app.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * Created by zhanyd@sina.com on 2018/2/13 0013.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;
    
    @Autowired
    QuestionService questionService;


   

    /**
     * 注册新用户
     * @param tel 电话号码
     * @param password 密码
     * @param identifyingCode 验证码
     * @param wxUnionId 微信unionId
     * @param alipayUserId 支付宝userId
     * @return
     */
    @RequestMapping("/register")
    public ApiResult register(String tel,String password,String identifyingCode,String wxUnionId,String alipayUserId){
        ApiResult apiResult = new ApiResult();
        if(StringHelp.isEmpty(tel)){
            return apiResult.fail(CodeStatus.VERIFICATION_ERROR,"电话号码不能为空");
        }

        if(StringHelp.isEmpty(password)){
            return apiResult.fail(CodeStatus.VERIFICATION_ERROR,"密码不能为空");
        }

        if(StringHelp.isEmpty(identifyingCode)){
            return apiResult.fail(CodeStatus.VERIFICATION_ERROR,"验证码不能为空");
        }

        //查找电话号码
        UserInfo userInfo = userService.selectByTel(tel);
        if(userInfo != null){
            return apiResult.fail(CodeStatus.EXIST_ERROR,"该手机号码已注册");
        }
        //新增用户
        userInfo = new UserInfo();
        userInfo.setTel(tel);
        userInfo.setPassword(MD5Generate.getMD5(password.getBytes()));
        userInfo.setUnionId(wxUnionId);
        userInfo.setCreateTime(new Date());
        int count = userService.insertSelective(userInfo);
        return apiResult.success(count);
    }


    /**
     * 登录
     * @param tel 电话号码
     * @param password 密码
     * @param wxUnionId 微信unionId
     * @param alipayUserId 支付宝userId
     * @return
     */
    @RequestMapping("/login")
    public ApiResult login(String tel,String password,String wxUnionId,String alipayUserId){
        ApiResult apiResult = new ApiResult();
        Map<String,String> param = new HashMap<String, String>();
        String token = null;
        if(!StringHelp.isEmpty(tel)){
            param.put("tel",tel);
            param.put("password",MD5Generate.getMD5(password.getBytes()));
            List<UserInfo> userList = userService.selectByParam(param);
            if(userList.isEmpty()){
                return apiResult.fail(CodeStatus.LOGIN_ERROR,"用户名或密码错误");
            }
            token = JwtUtils.signJWT(tel);
        }else if(!StringHelp.isEmpty(wxUnionId)){
            param.clear();
            param.put("wxUnionId",wxUnionId);
            List<UserInfo> userList = userService.selectByParam(param);
            if(userList.isEmpty()){
                return apiResult.fail(CodeStatus.LOGIN_ERROR,"微信登录失败");
            }
            token = JwtUtils.signJWT(userList.get(0).getTel());
        }else if(!StringHelp.isEmpty(alipayUserId)){
            param.clear();
            param.put("alipayUserId",alipayUserId);
            List<UserInfo> userList = userService.selectByParam(param);
            if(userList.isEmpty()){
                return apiResult.fail(CodeStatus.LOGIN_ERROR,"支付宝登录失败");
            }
            token = JwtUtils.signJWT(userList.get(0).getTel());
        }else{
            return apiResult.fail(CodeStatus.VERIFICATION_ERROR,"参数有误");
        }

        if(token == null){
            return apiResult.fail(CodeStatus.LOGIN_ERROR,"token生成失败");
        }else{
            logger.info(tel + " 登录成功");
            return apiResult.success(token);
        }
    }
    
    /**
     * 新增问题
     * @param content
     * @param userId
     * @return
     */
    @RequestMapping("/addNewQuestion")
    public ApiResult addNewQuestion(String title,String content,Integer userId){
    	ApiResult apiResult = new ApiResult();
    	Question question = new Question();
    	question.setTitle(title);
    	question.setContent(content);
    	question.setUserId(userId);
    	question.setCreateTime(new Date());
    	
    	int count = questionService.insertSelective(question);
    	return apiResult.success(count);
    }
    
    
    /**
     * 获取Openid和Session_key
     * @param code
     * @return
     */
    @RequestMapping("/getOpenidAndSession")
    public ApiResult getOpenidAndSession(String code){
    	ApiResult apiResult = new ApiResult();
    	//获取Openid和Session_key
		String getUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=" + WeixinHelper.APP_ID + "&secret=" + WeixinHelper.CORPSECRET + "&js_code=" + code + "&grant_type=authorization_code";
		String returnContent = HttpService.post(getUrl);
		logger.info("returnContent = " + returnContent);
		JSONObject jsonObject = JSONObject.parseObject(returnContent);
		String openid = jsonObject.getString("openid");
		String sessionKey = jsonObject.getString("session_key");
		logger.info("openid = " + openid);
		logger.info("session_key = " + sessionKey);
		
		//判断openid是否已经存在
		Map<String,String> param = new HashMap<String,String>();
		param.put("openid",openid);
        UserInfo userInfo = userService.selectByOpenid(openid);
        if(userInfo == null){
        	userInfo = new UserInfo();
        	userInfo.setOpenid(openid);
        	userInfo.setSessionKey(sessionKey);
        	userInfo.setCreateTime(new Date());
        	userService.insertSelective(userInfo);
        }else{
        	userInfo.setSessionKey(sessionKey);
        	userService.updateByPrimaryKeySelective(userInfo);
        }
        
        jsonObject.put("userId", userInfo.getId());
        return apiResult.success(jsonObject);
    }
}
