import com.xdShop.common.utils.HttpClientUtil;
import org.junit.Test;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pro on 16/11/17.
 */
public class TestHttpGet {

    @Test
    public void getWithParams() throws URISyntaxException {
        Map<String,String> map=new HashMap<String, String>();
        map.put("name","小李");
        map.put("age","18");
        HttpClientUtil.doGet("http://localhost:8083/test/get",map);
        HttpClientUtil.doGet("http://localhost:8083/test/post",map);

    }

}
