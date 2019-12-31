package com.list.provider.providerclient.api.login.service;

import com.list.provider.providerclient.entity.LoginUser;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {
    String login(LoginUser user, HttpServletRequest request);
}
