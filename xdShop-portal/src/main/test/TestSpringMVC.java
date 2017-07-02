import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by pro on 17/1/7.
 */
public class TestSpringMVC {

    //测试视图解析器
    @Test
    public  void test(){
        ApplicationContext context= new ClassPathXmlApplicationContext("spring/*.xml");
        InternalResourceViewResolver resolver=context.getBean(InternalResourceViewResolver.class);
        System.out.println(resolver);
    }
    @Test
    public void testSolrUrl(){
    }
}
