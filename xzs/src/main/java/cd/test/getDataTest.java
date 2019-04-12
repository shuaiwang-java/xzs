package cd.test;

import cd.utils.URLUtils;
import org.junit.Test;

public class getDataTest {

    @Test
    public void getData(){
        String url = URLUtils.getUrl("http://61.161.173.204/WebService1.asmx/start", null);

        System.out.println(url);
    }


}
