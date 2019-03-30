package cd.IService;

import cd.message.Msg;

import javax.servlet.http.HttpServletRequest;

public interface LoginIService {

    Msg login(String user, String pwd, HttpServletRequest request)throws Exception;
}
