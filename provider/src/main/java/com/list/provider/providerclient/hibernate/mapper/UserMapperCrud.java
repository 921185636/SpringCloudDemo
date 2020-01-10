package com.list.provider.providerclient.hibernate.mapper;

import com.list.provider.providerclient.hibernate.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserMapperCrud extends CrudRepository<User,Integer> {

}
