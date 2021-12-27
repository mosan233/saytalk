package com.sf.skytalk.service;

import com.sf.skytalk.model.Question;
import com.sf.skytalk.model.User2;

public interface PublishService {

    boolean addQuestion(String title, String description, String tag, User2 user);
}
