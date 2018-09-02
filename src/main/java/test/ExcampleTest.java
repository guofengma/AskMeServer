package test;

        import org.junit.After;
        import org.junit.Before;
        import org.junit.Test;

        import static junit.framework.TestCase.assertEquals;

/**
 * Created by Administrator on 2018/9/2 0002.
 */
public class ExcampleTest {

    @Before
    public void before() {
        System.out.println("befor");
    }

    @After
    public void after() {
        System.out.println("after");
    }

    @Test
    public void test1(){
        System.out.println("test..");
        int i = 2+3;
        assertEquals(5,i);
    }
}
