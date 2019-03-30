package cd.Service;

import cd.IService.UserIService;
import cd.dao.UserDao;
import cd.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService implements UserIService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByName(String name)throws Exception {
        return userDao.selectUserByName(name);
    }
}
