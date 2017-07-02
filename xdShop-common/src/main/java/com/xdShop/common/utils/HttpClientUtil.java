package com.xdShop.common.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by pro on 16/11/15.
 */
public class HttpClientUtil {

    public static String doGet(String url, Map<String, String> params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response=null;
        String info=null;
        try {
            //用于构建参数列表
            URIBuilder builder=new URIBuilder(url);
            if(params!=null){
                for(String key:params.keySet())
                    builder.addParameter(key,params.get(key));
            }
            URI url2=builder.build();
            HttpGet get = new HttpGet(url2);
            response=httpClient.execute(get);
            //获取响应状态码
            int status = response.getStatusLine().getStatusCode();
//            System.out.println("response status code is " + status);
            //获取网页源码
            HttpEntity enitty = response.getEntity();
            //将内容转码
            info=EntityUtils.toString(response.getEntity(),"UTF-8");
//            System.out.println("这个是在httpUtil显示的 :"+info);
            response.close();
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return info;
    }

    public static String doPost(String url, Map<String, String> params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response=null;
        String info=null;
        try {
            //用于构建参数列表
            HttpPost post=new HttpPost(url);
            List<BasicNameValuePair> paramsList=new ArrayList<BasicNameValuePair>();
            if(params!=null){
                for(String key:params.keySet())
                    paramsList.add(new BasicNameValuePair(key,params.get(key)));
            UrlEncodedFormEntity entity=new UrlEncodedFormEntity(paramsList);
            post.setEntity(entity);
            }
            response=httpClient.execute(post);
            //获取响应状态码
            int status = response.getStatusLine().getStatusCode();
//            System.out.println("response status code is " + status);
            //获取网页源码
            HttpEntity enitty = response.getEntity();
            //将内容转码
            info = new String (EntityUtils.toString(enitty).getBytes("iso-8859-1"),"utf-8");
//            System.out.println(info);
            response.close();
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return info;
    }
    public static String doGet(String url) throws URISyntaxException {
        return doGet(url,null);
    }
    public static String doPost(String url){
        return doPost(url,null);
    }
    public static String doPostJson(String url, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return resultString;
    }
}

