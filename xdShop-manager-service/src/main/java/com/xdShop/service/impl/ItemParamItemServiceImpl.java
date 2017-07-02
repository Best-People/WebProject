package com.xdShop.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xdShop.common.utils.JsonUtils;
import com.xdShop.mapper.TbItemParamItemMapper;
import com.xdShop.pojo.TbItemParamItem;
import com.xdShop.pojo.TbItemParamItemExample;
import com.xdShop.pojo.TbItemParamItemExample.Criteria;
import com.xdShop.service.ItemParamItemService;
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {
	@Autowired
	private TbItemParamItemMapper mapper;
	@Override
	public String getItemParamInfoById(int id) {
		//找到对应的商品规格信息
		TbItemParamItemExample example=new TbItemParamItemExample();
		Criteria c=example.createCriteria();
		c.andItemIdEqualTo((long)id);
		List<TbItemParamItem> list=mapper.selectByExampleWithBLOBs(example);
		TbItemParamItem ipi=null;
		String paramDate="";
		if(list!=null&&list.size()>0){
			ipi=list.get(0);
			paramDate=ipi.getParamData();
		}
		
		List<Map> paramList=JsonUtils.jsonToList(paramDate, Map.class);
		StringBuilder sb=new StringBuilder();
		sb.append("<table cellpadding='0' cellspacing='1' width='100%' border='1' class='Ptable'>\n");
		sb.append("	<tbody>\n");
		for(Map m1:paramList){
			sb.append("		<tr><th class='tdTitle' colspan='2'>"+m1.get("group")+"</th></tr>\n");
			sb.append("		<tr>\n");
			List<Map> list2 = (List<Map>) m1.get("params");
			for(Map m2:list2){
				sb.append("			<td class='tdTitle'>"+m2.get("k")+"</td>\n");
				sb.append("			<td>"+m2.get("v")+"</td>\n");
				sb.append("		</tr>\n");
			}
		}
		sb.append("	</tbody>\n");
		sb.append("</table>\n");
		
		return sb.toString();
	}
}
