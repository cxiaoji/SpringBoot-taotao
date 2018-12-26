package com.itxj.interceptor;

import com.itxj.Utils.CookiesUtil;
import com.itxj.Utils.RedisUtil;
import com.itxj.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.interceptor
 *  @文件名:   OrderInterceptor
 *  @创建者:   小吉
 *  @创建时间:  2018/12/20 14:38
 *  @描述：    订单拦截器
 */
@Component
public class OrderInterceptor implements HandlerInterceptor {

    @Autowired
    private  RedisTemplate<String,String> template;

    //controller运行前执行

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        String ticket = CookiesUtil.getTicketByCookies(request);
        User user = RedisUtil.GetUserByTicket(ticket, template);

        System.out.println("redisTemplate===" +template);

        //用户没有登录
        if(StringUtils.isEmpty(ticket)){

            //跳转登录页面
            String uri = "http://www.taotao.com/cart/cart.html";

            response.sendRedirect("/page/login.shtml?uri="+uri);

            //拦截
            return false;
        }


        if (user==null){

            //跳转登录页面
            String uri = "http://www.taotao.com/cart/cart.html";

            response.sendRedirect("/page/login.shtml?uri="+uri);

            //拦截
            return false;
        }
        //用户登录成功-----将登录信息存入域中，之后可以不再需要重复查询user信息
        request.setAttribute("user",user);
        return true;
    }

    //controller运行后，页面渲染前执行

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    //controller运行后-----页面渲染后----执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
