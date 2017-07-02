package com.xdShop.portal.service.impl;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.xdShop.common.pojo.GoodsResult;
import com.xdShop.common.utils.HttpClientUtil;
import com.xdShop.common.utils.JsonUtils;
import com.xdShop.pojo.TbItem;
import com.xdShop.pojo.TbItemDesc;
import com.xdShop.pojo.TbItemParamItem;
import com.xdShop.portal.POJO.ItemInfo;
import com.xdShop.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17/2/21.
 */
@Service
public class ItemserviceImpl implements ItemService{


    @Value("${REST_BASE_PATH}")
    private String REST_BASE_PATH;

    @Value("${ITEM_INFO_URL}")
    private String ITEM_INFO_URL;

    @Value("${ITEM_DESC_URL}")
    private String ITEM_DESC_URL;

    @Value("${ITEM_PARAM_URL}")
    private String ITEM_PARAM_URL;



    @Override
    public ItemInfo getItemById(long itemId) {
        try {
            String json= HttpClientUtil.doGet(REST_BASE_PATH+ITEM_INFO_URL+itemId);
            if(!StringUtils.isEmpty(json)){
                GoodsResult result= GoodsResult.formatToPojo(json,ItemInfo.class);
                if(result.getStatus()==200){
//                    System.out.println(dataJosn);
                    ItemInfo item= (ItemInfo) result.getData();

                    return item;
                }
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getItemDesc(long itemId) {
        try {
            String json= HttpClientUtil.doGet(REST_BASE_PATH+ITEM_DESC_URL+itemId);
            if(!StringUtils.isEmpty(json)){
                GoodsResult result= GoodsResult.formatToPojo(json,TbItemDesc.class);
                if(result.getStatus()==200){
                    TbItemDesc itemDesc= (TbItemDesc) result.getData();
                    return itemDesc.getItemDesc();
                }
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public String getItemParam(long itemId) {
        try {
            String json = HttpClientUtil.doGet(REST_BASE_PATH + ITEM_PARAM_URL + itemId);
            System.out.println("josn:"+json);
            //把json转换成java对象
            GoodsResult result = GoodsResult.formatToPojo(json, TbItemParamItem.class);
            if (result.getStatus() == 200) {
                TbItemParamItem itemParamItem = (TbItemParamItem) result.getData();
                String paramData = itemParamItem.getParamData();
                //生成html
                // 把规格参数json数据转换成java对象
                List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
                StringBuffer sb = new StringBuffer();
                sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
                sb.append("    <tbody>\n");
                for(Map m1:jsonList) {
                    sb.append("        <tr>\n");
                    sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
                    sb.append("        </tr>\n");
                    List<Map> list2 = (List<Map>) m1.get("params");
                    for(Map m2:list2) {
                        sb.append("        <tr>\n");
                        sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
                        sb.append("            <td>"+m2.get("v")+"</td>\n");
                        sb.append("        </tr>\n");
                    }
                }
                sb.append("    </tbody>\n");
                sb.append("</table>");
                //返回html片段
                return sb.toString();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }


}
