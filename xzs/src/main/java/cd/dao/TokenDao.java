package cd.dao;

import cd.entity.Token;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TokenDao {

    @Options(useGeneratedKeys=true,keyProperty="tokenId",keyColumn = "tokenId")
    @Insert("insert into token(userId,token,buildTime) values(#{userId},#{token},#{buildTime})")
    void addToken(Token token);

    @Update("update token set token = #{token},buildTime = #{buildTime} where userId = #{userId}")
    void updataToken(Token token);

    @Select("select * from token where userId = #{userId}")
    Token selectTokenByUserId(int userId)throws Exception;

}
