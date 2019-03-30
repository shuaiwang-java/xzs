package cd.IService;

import cd.entity.User;
import cd.message.Msg;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface LoginIService {

    Msg login(String user, String pwd, HttpServletRequest request)throws Exception;
}
