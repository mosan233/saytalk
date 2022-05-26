package com.sf.skytalk.mapper;

import com.sf.skytalk.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(id,title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tag) " +
            "values(#{id},#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tag})")
    int create(Question question);

    //分页查询所有
    @Select("select * from question limit #{start},#{size}")
    List<Question> list(@Param("start") int start, @Param("size") int size);

    @Select("select count(1) from question")
    int count();

    //根据userId分页查询
    @Select("select * from question where creator = #{userId} limit #{start},#{size}")
    List<Question> listByUserId(@Param("userId") Integer userId, @Param("start") int start, @Param("size") int size);

    @Select("select count(1) from question where creator = #{userId}")
    int countByUserId(@Param("userId") Integer userId);

    //根据注解查询
    @Select("select * from question where id = #{id}")
    Question selectByPrimary(@Param("id") Integer id);

    //根据主键更新
    @Update("update question set title = #{title},description = #{description},tag = #{tag},gmt_modified = #{gmtModified} where id = #{id}")
    int updateByPrimary(Question question);
}
