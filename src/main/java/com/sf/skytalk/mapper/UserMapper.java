package com.sf.skytalk.mapper;

import com.sf.skytalk.model.User2;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into user(account_id,name,token,gmt_create,avatar_url) values(#{accountId},#{name},#{token},#{gmtCreate},#{avatarUrl})")
    void insertUser(User2 user);

    @Select({"select * from user where token = #{token}"})
    User2 selectByToken(String token);

    @Select("select * from user where id = #{id}")
    User2 selectById(Integer id);
}
