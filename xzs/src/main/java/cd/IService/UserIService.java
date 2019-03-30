package cd.IService;

import cd.entity.User;

public interface UserIService {

    User getUserByName(String name) throws Exception;
}
