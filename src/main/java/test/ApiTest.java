package test;

/**
 * Created by Administrator on 2018/9/2 0002.
 */

import com.askme.app.WebApp;
import com.askme.app.common.ApiResult;
import com.askme.app.model.Question;
import com.askme.app.service.QuestionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=WebApp.class)
public class ApiTest {

    @Autowired
    QuestionService questionService;


    @Test
    public void getQuestionList(){
        ApiResult apiResult = new ApiResult();

        List<Question> questionList = questionService.selectAll();
        System.out.println(questionList);
    }

}
