package com.xdShop.portal.service.impl;

import com.xdShop.common.pojo.GoodsResult;
import com.xdShop.common.utils.HttpClientUtil;
import com.xdShop.pojo.TbUser;
import com.xdShop.portal.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;

/**
 * Created by pro on 17/2/22.
 */
@Service
public class UserServiceImpl  implements UserService{

    @Value("${SSO_BASE_URL}")
    private String  SSO_BASE_URL;

    @Value("${SSO_USER_TOKEN}")
    private String  SSO_USER_TOKEN;

    @Value("${SSO_PAGE_LOGIN}")
    private String  SSO_PAGE_LOGIN;

    @Override
    public TbUser getUserByToken(String token) {
        try{
            String  userJson= HttpClientUtil.doGet(SSO_BASE_URL+SSO_USER_TOKEN+token);
            GoodsResult result=GoodsResult.formatToPojo(userJson,TbUser.class);
            if(result.getStatus()==200){
                TbUser user= (TbUser) result.getData();
                return user;
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getSSO_USER_TOKEN() {
        return SSO_USER_TOKEN;
    }

    public String getSSO_PAGE_LOGIN() {
        return SSO_PAGE_LOGIN;
    }

    public String getSSO_BASE_URL() {
        return SSO_BASE_URL;

    }
}
