package com.list.provider.providerclient.api.login.service.impl;

import com.list.provider.providerclient.api.login.service.LoginService;
import com.list.provider.providerclient.entity.LoginUser;
import com.list.provider.providerclient.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String login(LoginUser user, HttpServletRequest request) {
        if (isTokenExists(request.getCookies())) {

        } else {
            createKey(user);
        }

        return "false";
    }

    private boolean isTokenExists(Cookie[] cookies) {
        String token = "";
        for (Cookie cookie : cookies) {
            if ("loginToken".equals(cookie.getName())) {
                token = cookie.getValue();
            }
        }
        if (redisUtil.exists(token)) {
            return true;
        }
        return false;
    }

    private void createKey(LoginUser user) {
        //用做演示而简化，实际应改为从mysql查询对应的用户
        if ("stli".equals(user.getUsername()) && "123".equals(user.getPassword())) {
            //生成缓存key
            String key = user.getUsername() + System.currentTimeMillis();
            redisUtil.set(key, user, (long) 60 * 3);
        }
    }
}
