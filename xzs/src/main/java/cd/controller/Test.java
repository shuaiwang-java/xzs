package cd.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Test {

    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    @GetMapping("/test")
    public void getTestData(String name){
        logger.info("【get方式获取到的数据】"+ name);
        System.out.println("【get方式获取到的数据】"+"【======"+name+"=======】");
    }

    @PostMapping("/test")
    public void postTestData(String name){
        logger.info("【POST方式获取到的数据】"+ name);
        System.out.println("【post方式获取到的数据】"+"【======"+name+"=======】");
    }

}
