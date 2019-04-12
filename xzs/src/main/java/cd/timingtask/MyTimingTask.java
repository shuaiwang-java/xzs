package cd.timingtask;

import cd.modbus.ModbusUtil;
import cd.utils.URLUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//@Component注解用于对那些比较中立的类进行注释；
//相对与在持久层、业务层和控制层分别采用 @Repository、@Service 和 @Controller 对分层中的类进行注释
@Component
@EnableScheduling   // 1.开启定时任务
@EnableAsync        // 2.开启多线程
public class MyTimingTask {
    private int i=0;
    //private static final Logger logger = LoggerFactory.getLogger(MyTimingTask.class);

    //@Async
    //@Scheduled(fixedDelay = 1000)  //间隔1秒
   /* public void first() throws InterruptedException {
        System.out.println("第一个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
        System.out.println();
        Thread.sleep(1000 * 10);
    }*/

   // @Async
   // @Scheduled(fixedDelay = 2000)
   /* public void second()throws InterruptedException {
        System.out.println("第二个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
        System.out.println();
    }*/


    @Async
    @Scheduled(fixedDelay = 5000)  //间隔1秒
    public void tcp() throws Exception {

        String url = URLUtils.getUrl("http://61.161.173.204/WebService1.asmx/start", null);

        System.out.println("第【"+(++i)+"】:--"+url);
    }




}
