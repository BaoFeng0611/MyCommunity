package com.geminifeng.community.mapper;

import com.geminifeng.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: SunBaoFeng
 * @Description: QuestionMapper
 * @Date: Created in 2020/5/26 0:01
 */
@Mapper
public interface QuestionMapper {
    @Insert("insert into QUESTION (TITLE, DESCRIPTION, GMT_CREATE, GMT_MODIFIED, CREATOR, TAG) VALUES ( #{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag} )")
    Integer insert(Question question);
}
