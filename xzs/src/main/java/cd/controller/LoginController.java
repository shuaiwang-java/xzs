package cd.controller;

import cd.IService.LoginIService;
import cd.IService.UserIService;
import cd.message.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginIService loginIService;

    @RequestMapping("/login")
    public Msg login(String user, String pwd, HttpServletRequest request){

        Msg msg=null;
        try {
            msg = loginIService.login(user, pwd,request);
        } catch (Exception e) {
            logger.error("【登录验证错误】"+e.getMessage());
        }
        return msg;
    }



}
