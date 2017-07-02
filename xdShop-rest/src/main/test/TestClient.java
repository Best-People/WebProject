import com.xdShop.rest.dao.JedisClient;
import com.xdShop.rest.dao.impl.JedisClientCluster;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by pro on 16/11/30.
 */
public class TestClient {

//    @Test
//    public void testSingle(){
//        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*");
//        JedisClient single=context.getBean("JedisClientSingle",JedisClientSingle.class);
//        String s=single.set("bb","你好");
//        String s1=single.get("bb");
//        long l=single.expire("bb",200);
//        long ttl=single.ttl("bb");
//        System.out.println(s);
//        System.out.println(s1);
//        System.out.println(l);
//        System.out.println(ttl);
//    }

    @Test
    public void tesrCluster(){
        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*");
        JedisClient cluster=context.getBean("JedisClientCluster", JedisClientCluster.class);
        String s=cluster.set("bb","你好");
        String s1=cluster.get("bb");
        long l=cluster.expire("bb",200);
        long ttl=cluster.ttl("bb");
        System.out.println(s);
        System.out.println(s1);
        System.out.println(l);
        System.out.println(ttl);
    }
}
