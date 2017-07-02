package com.xdShop.portal.service;

import com.xdShop.pojo.TbUser;

/**
 * Created by pro on 17/2/22.
 */
public interface UserService {

    TbUser getUserByToken(String token);


}
