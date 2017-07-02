import com.xdShop.search.mapper.ItemMapper;
import com.xdShop.search.service.ItemService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by pro on 17/1/3.
 */
public class TestItemService {

    @Test
    public void test(){
        ApplicationContext context= new ClassPathXmlApplicationContext("spring/applicationContext-*");

        ItemMapper mapper= (ItemMapper) context.getBean(ItemMapper.class);

        ItemService service=context.getBean(ItemService.class);
        System.out.println(service);
        System.out.println(mapper);
//        List<Item> lists=mapper.getItemList();
//        System.out.println(lists.size());
//        System.out.println(mapper.selectById(536563).getTitle());
    }
}
