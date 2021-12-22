package com.sf.skytalk.mapper;

import com.sf.skytalk.model.User2;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into user2(account_id,name,token,gmt_create) values(#{accuntId},#{name},#{token},#{gmtCreate})")
    void insertUser(User2 user);

    @Select({"select * from user2 where token = #{token}"})
    User2 selectByToken(String token);
}
