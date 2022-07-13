package com.yzk.mapper;

import com.yzk.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user")
    List<User> getAll();

    @Select("select count(*) from user where username=#{username}")
    Integer getCount(User user);

    @Select("select * from user where id=#{id}")
    User getById(Integer id);

    @Select("select * from user where phone=#{phone}")
    User getByPhone(String phone);

    @Select("select * from user where username=#{username} and password=#{password}")
    User getByUsernameAndPassword(User user);

    @Select("select * from user")
    List<User> getUserByPage();

    @Insert("insert into user values (#{id},#{username},#{password},#{email},#{phone},#{nickname})")
    @SelectKey(before = false, keyProperty = "id", statement = "SELECT LAST_INSERT_ID() AS id", resultType = Integer.class, statementType = StatementType.STATEMENT)
    Integer addUser(User user);

    @Update("update user set username=#{username},password=#{password},email=#{email},phone=#{phone},nickname=#{nickname}} where id=#{id}")
    Integer modifyUser(User user);

    @Delete("delete from user where id=#{id}")
    Integer deleteUser(Integer id);
}
