package com.sf.skytalk.service;

import com.sf.skytalk.model.User;

public interface UserService {

    User getByAccountId(String accountId);

    void createOrUpdate(User user);

    User getByToken(String value);
}
