package com.sf.skytalk.mapper;

import com.sf.skytalk.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(id,title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tag) " +
            "values(#{id},#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tag})")
    int create(Question question);
}
