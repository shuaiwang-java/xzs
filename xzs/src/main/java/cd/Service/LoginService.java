package cd.Service;

import cd.IService.LoginIService;
import cd.dao.TokenDao;
import cd.dao.UserDao;
import cd.entity.Token;
import cd.entity.User;
import cd.message.Msg;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
@Transactional
public class LoginService implements LoginIService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private TokenDao tokenDao;

    //登录
    public Msg login(String name, String pwd, HttpServletRequest request) throws Exception {

        if("".equals(name) || "".equals(pwd)){
            return Msg.error("用户名/密码不能为空");
        }

        User user = userDao.selectUserByName(name);
        if(null == user){
            return Msg.error("当前用户不存在");
        }

        if(!pwd.equals(user.getPwd())){
            return Msg.error("密码错误");
        }

        //通过用户id查询token对象
        Token token = tokenDao.selectTokenByUserId(user.getId());
        //为生成token准备
        String tokenStr = "";
        Date date = new Date();
        int nowTime = (int)(date.getTime() / 1000);
        //生成token
        tokenStr = creatToken(user, date);

        if(null == token){ //第一次登录token为空情况下
            token = new Token();
            token.setToken(tokenStr);
            token.setBuildTime(nowTime);
            token.setTokenId(user.getId());
            tokenDao.addToken(token);
        }else {
            //跟新token
            tokenStr = creatToken(user,date);
            token.setToken(tokenStr);
            token.setBuildTime(nowTime);
            tokenDao.updataToken(token);
        }
        return Msg.setSuccess(true,tokenStr);
    }


    //生成token的方法
    private String creatToken(User user, Date date) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT") // 设置header
                .setHeaderParam("alg", "HS256").setIssuedAt(date) // 设置签发时间
                .setExpiration(new Date(date.getTime() + 1000 * 60 * 60 * 24 * 3))
                .claim("userId",String.valueOf(user.getId()) ) // 设置内容
                .setIssuer("lws")// 设置签发人
                .signWith(signatureAlgorithm, "dahao"); // 签名，需要算法和key
        String jwt = builder.compact();
        return jwt;
    }
}
