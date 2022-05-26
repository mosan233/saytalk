package com.sf.skytalk.service.impl;

import com.sf.skytalk.mapper.UserMapper;
import com.sf.skytalk.model.User2;
import com.sf.skytalk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public void createOrUpdate(User2 user) {
        User2 user2 = userMapper.selectByAccountId(user.getAccountId());
        if (user2 != null){
            user.setGmtModified(System.currentTimeMillis());
            userMapper.updateByAccountId(user);
        }else {
            user.setGmtCreate(System.currentTimeMillis());
            userMapper.insertUser(user);
        }
    }
}
