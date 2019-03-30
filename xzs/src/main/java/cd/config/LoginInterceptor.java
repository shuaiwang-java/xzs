package cd.config;

import cd.Service.TokenService;
import cd.entity.Token;
import cd.message.Msg;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;

/**
 * 拦截器   实现HandlerInterCeptor
 */

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    private static final Logger log = (Logger) LoggerFactory.getLogger(LoginInterceptor.class);

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)throws  Exception{
        log.info("----------------------------请求方法执行之前---------------------------------");
        //放行验证和登录接口
        if("/isUser".equals(httpServletRequest.getRequestURI()) || "/login".equals(httpServletRequest.getRequestURI())){
            return true;
        }

        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Method", "POST,GET");
        httpServletResponse.addHeader("Access-Control-Allow-Headers", "Authentication,Origin, X-Requested-With, content-type, Accept,token");

        final String headerToken = httpServletRequest.getHeader("token");

        //判断请求
        if(null == headerToken || headerToken.trim().equals("")){
            PrintWriter writer = httpServletResponse.getWriter();
            Msg msg = Msg.error("没有token,需要登录");
            String res = new ObjectMapper().writeValueAsString(msg);
            writer.write(res);
            writer.close();
            return false;
        }

        try{
            Claims claims = Jwts.parser().setSigningKey("dahao").parseClaimsJws(headerToken).getBody();
            String tokenUserId = (String) claims.get("userId");
            int itokenUserId = Integer.parseInt(tokenUserId);

            //根据客户token查找数据库
            Token myToken = tokenService.getTokeByUserId(itokenUserId);
           //Token myToken = new TokenService().getTokeByUserId(itokenUserId);
            //数据库没有Token记录
            if(null==myToken) {
                PrintWriter writer = httpServletResponse.getWriter();
                Msg msg = Msg.error("数据库没有此token");
                String res = new ObjectMapper().writeValueAsString(msg);
                writer.write(res);
                writer.close();
                return false;
            }
            if(!headerToken.equals(myToken.getToken())){
                PrintWriter writer = httpServletResponse.getWriter();
                Msg msg = Msg.error("token和数据库token不匹配");
                String res = new ObjectMapper().writeValueAsString(msg);
                writer.write(res);
                writer.close();
                return false;
            }
            //判断Token过期
            Date tokenDate=(Date)claims.getExpiration();
            int chaoshi=(int)(new Date().getTime()-tokenDate.getTime())/1000;
            if(chaoshi>60*60*24*3){
                PrintWriter writer = httpServletResponse.getWriter();
                Msg msg = Msg.error("token已过期，请重新登陆");
                String res = new ObjectMapper().writeValueAsString(msg);
                writer.write(res);
                writer.close();
                return false;
            }
        }catch (Exception e){
            PrintWriter writer = httpServletResponse.getWriter();
            log.error("【TokenInterceptor拦截类出现异常】"+e.getMessage());
            Msg msg = Msg.error("【TokenInterceptor拦截类出现异常】");
            String res = new ObjectMapper().writeValueAsString(msg);
            writer.write(res);
            writer.close();
            return false;
        }

        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)throws Exception{
        log.info("-----------------------请求方法执行完成后----------------------");
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,Object o)throws Exception{
        log.info("---------------------------------视图渲染之后的操作--------------------------------");
    }


}
