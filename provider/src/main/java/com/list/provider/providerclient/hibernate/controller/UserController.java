package com.list.provider.providerclient.hibernate.controller;

import com.list.provider.providerclient.hibernate.entity.User;
import com.list.provider.providerclient.hibernate.mapper.UserMapper;
import com.list.provider.providerclient.hibernate.mapper.UserMapperCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserMapperCrud userMapperCrud;

    @RequestMapping("/addUsers")
    public String addUsers() {
        User user = null;
        List<User> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            user = new User();
            user.setId(i);
            user.setUsername("kkkkk" + i);
            user.setPassword("123456");
            list.add(user);
        }
//        userMapper.save(user);
        userMapper.saveAll(list);
        return "";
    }

    @RequestMapping("/findAllUser")
    public String findAllUser() {
        List<User> users = userMapper.findAll();
        for (User user : users) {
            System.out.println("username:" + user.getPassword() + " password：" + user.getPassword());
        }
        return "";
    }
    @RequestMapping("/findAllUserPage")
    public String findAllUserPage() {
        //分页，从零页开始，每页四条数据，根据id 反序排列
        Pageable pageRequest = PageRequest.of(0, 4, Sort.by(Sort.Order.desc("id")));
        Page<User> users = userMapper.findAll(pageRequest);

        for (User user : users) {
            System.out.println("username:" + user.getUsername() + " password：" + user.getPassword());
        }
        return "";
    }
    @RequestMapping("/findByOrderByUsername")
    public String findByOrderByUsername() {
        //分页，从零页开始，每页四条数据，根据id 反序排列
        List<User> users = userMapper.findByOrderByUsername();
        for (User user : users) {
            System.out.println("username:" + user.getPassword() + " password：" + user.getPassword());
        }
        return "";
    }
    @RequestMapping("/findUsersByName")
    public String findUsersByName() {
        List<User> users = userMapper.findUserByName("kkkkk99");
        for (User user : users) {
            System.out.println("username:" + user.getPassword() + " password：" + user.getPassword());
        }
        return "";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser() {
        userMapper.deleteById(1);
        return "";
    }

    @RequestMapping("/updateUser")
    public String updateUser() {
        Optional<User> user = userMapper.findById(1);
        User user1 = user.get();
        user1.setPassword("432435436547");
        userMapper.save(user1);
        return "";
    }
}
