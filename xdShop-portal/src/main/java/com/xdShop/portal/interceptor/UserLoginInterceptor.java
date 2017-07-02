package com.xdShop.portal.interceptor;

import com.xdShop.common.utils.CookieUtils;
import com.xdShop.pojo.TbUser;
import com.xdShop.portal.service.UserService;
import com.xdShop.portal.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by pro on 17/2/22.
 */
public class UserLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //判断用户是否已经登录
        String token= CookieUtils.getCookieValue(request,"TT_TOKEN");
        //根据token调用单点登录系统的验证接口
        TbUser user=userService.getUserByToken(token);
        if(user==null){
            //用户未登录,将用户登录前的url作为参数传到登录页面作为回调
            response.sendRedirect(userService.getSSO_BASE_URL()+userService.getSSO_PAGE_LOGIN()+
                    "?redirect="+request.getRequestURL());
            return false;
        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
