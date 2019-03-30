package cd.dao;

import cd.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

    @Select("select * from user where name = #{name}")
    User selectUserByName(String name)throws Exception;



}
