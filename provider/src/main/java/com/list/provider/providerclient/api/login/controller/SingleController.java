package com.list.provider.providerclient.api.login.controller;

import com.list.provider.providerclient.api.login.service.LoginService;
import com.list.provider.providerclient.entity.LoginUser;
import com.list.provider.providerclient.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@RequestMapping("/provider")
public class SingleController {
    @Value("${server.port}")
    private String port;
    @Value("${spring.application.name}")
    private String providerName;
    @Autowired
    private LoginService loginService;
    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/discovery")
    @ResponseBody
    public String discoveryServers() {
        System.out.println("访问到provider啦");
        return "provider返回的信息: " + providerName + ":" + port;
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(LoginUser user, String url, HttpServletRequest request, HttpServletResponse response) {
        String token = loginService.login(user, request);
        Cookie cookie = new Cookie("accessToken", token);
        cookie.setMaxAge(60 * 3);
        //设置域
        //cookie.setDomain("stli.com");
        //设置访问路径
        cookie.setPath("/");
        response.addCookie(cookie);
        //重定向到原先访问的页面
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/loginPage")
    public String loginPage(LoginUser user, HttpServletRequest request) {
        return "loginPage";
    }
}
