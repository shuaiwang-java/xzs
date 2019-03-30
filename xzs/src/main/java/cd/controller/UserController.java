package cd.controller;

import cd.IService.UserIService;
import cd.entity.User;
import cd.message.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserIService userIService;

    @GetMapping("/user")
    public Msg getUser(String name){

        System.out.println("【前端传来的name值】"+name);
        User user = null;
        try {
            user = userIService.getUserByName(name);
        } catch (Exception e) {
            logger.error("【/user --User控制层出现错误】"+e.getMessage());
        }
        return Msg.success().add("user",user);
    }

}
