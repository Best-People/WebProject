import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by pro on 16/11/20.
 */
public class TestRedisCluster {

    @Test
    public void test(){
        Set<HostAndPort> nodes=new HashSet<HostAndPort>();
        nodes.add(new HostAndPort("192.168.1.104",7001));
        nodes.add(new HostAndPort("192.168.1.104",7002));
        nodes.add(new HostAndPort("192.168.1.104",7003));
        nodes.add(new HostAndPort("192.168.1.104",7004));
        nodes.add(new HostAndPort("192.168.1.104",7005));
        nodes.add(new HostAndPort("192.168.1.104",7006));

        JedisCluster cluster=new JedisCluster(nodes);

        cluster.set("key","hello");
        String s=cluster.get("key");
        System.out.println(s);
        cluster.close();
    }
    @Test
    public void testWithSpring(){
        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring/*");
        JedisCluster cluster= (JedisCluster) context.getBean("redisCluster");

        cluster.set("cluster","hey man!!!");
        System.out.println(cluster.get("cluster"));
    }
}
