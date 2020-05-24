package com.geminifeng.community.mapper;

import com.geminifeng.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: SunBaoFeng
 * @Description: UserMapper
 * @Date: Created in 2020/5/24 23:15
 */
@Mapper
public interface UserMapper {
    @Insert(" insert into USER(account_id, name, token, gmt_create, gmt_modified) VALUES ( #{accountId},#{name},#{token},#{gmtCreate},#{gmtModified} ) ")
    Integer saveUser(User user);

    @Select("select * from USER where ID = #{id}")
    User getUserById(String id);
}
