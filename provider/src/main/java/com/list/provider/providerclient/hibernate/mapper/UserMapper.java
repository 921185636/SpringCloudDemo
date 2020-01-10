package com.list.provider.providerclient.hibernate.mapper;

import com.list.provider.providerclient.hibernate.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserMapper extends JpaRepository<User, Integer> {
    @Query("from User where username = :username")
    List<User> findUserByName(@Param("username") String username);

    //使用默认的字段拼接形成的方法名，从而自动解析形成对应的方法
    List<User> findByOrderByUsername();

}
