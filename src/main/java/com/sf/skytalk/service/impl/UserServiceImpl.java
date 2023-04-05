package com.sf.skytalk.service.impl;

import com.sf.skytalk.mapper.UserMapper;
import com.sf.skytalk.model.User;
import com.sf.skytalk.model.UserExample;
import com.sf.skytalk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;



    @Override
    public User getByAccountId(String accountId) {
        if (accountId == null) return null;
        UserExample example = new UserExample();
        example.createCriteria().andAccountIdEqualTo(accountId);
        List<User> userList = userMapper.selectByExample(example);
        return CollectionUtils.isEmpty(userList) ? null : userList.get(0);
    }

    @Override
    public void createOrUpdate(User user) {
        User userDb = this.getByAccountId(user.getAccountId());
        if (userDb != null){
            user.setId(userDb.getId());
            user.setGmtModified(System.currentTimeMillis());
            userMapper.updateByPrimaryKeySelective(user);
        }else {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insertSelective(user);
        }
    }

    @Override
    public User getByToken(String token) {
        if (StringUtils.isEmpty(token)) return null;
        UserExample example = new UserExample();
        example.createCriteria().andTokenEqualTo(token);
        List<User> userList = userMapper.selectByExample(example);
        return CollectionUtils.isEmpty(userList) ? null : userList.get(0);
    }

}
