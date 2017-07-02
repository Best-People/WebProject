import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by pro on 16/11/20.
 */
public class TestJedis {
    public static void main(String[] args) {
//        JedisPool pool=new JedisPool("192.169.1.104",6379);
//        Jedis jedis=pool.getResource();
        Jedis jedis=new Jedis("192.168.1.104",6379);
        System.out.println(jedis.ping());
        jedis.set("key1","testJedis");
        String s=jedis.get("key1");
        System.out.println(s);
        jedis.close();
    }
    @Test
    public void test(){
        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring/applicationContext*");
        JedisPool pool= (JedisPool) context.getBean("redisClient");
        Jedis jedis=pool.getResource();
        jedis.set("a","hhh");
        String s=jedis.get("a");
        System.out.println(s);

    }
    @Test
    public void ttt(){
        String s="   ";
        System.out.println(s.isEmpty());
    }
}
