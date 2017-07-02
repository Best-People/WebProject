import com.sun.javafx.fxml.builder.URLBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by pro on 16/11/13.
 */
public class TestHttpClient {
    public static void main(String[] args) {
        try {
            CloseableHttpClient httpClient= HttpClients.createDefault();

            HttpGet get=new HttpGet("http://www.sina.com.cn");

            CloseableHttpResponse response = httpClient.execute(get);

            //获取响应状态码
            int status=response.getStatusLine().getStatusCode();
            System.out.println("response status code is "+status);
            //获取网页源码
            HttpEntity enitty=response.getEntity();
            //将内容转码
            String info= EntityUtils.toString(enitty,"UTF-8");
            System.out.println(info);
            response.close();
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
