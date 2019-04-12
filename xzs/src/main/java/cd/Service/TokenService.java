
package cd.Service;

import cd.IService.TokenIService;
import cd.dao.TokenDao;
import cd.entity.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class TokenService implements TokenIService {

    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

    @Autowired
    private TokenDao tokenDao;

    @Override
    public Token getTokeByUserId(int userId){
        Token token = null;
        try {
            token = tokenDao.selectTokenByUserId(userId);
        } catch (Exception e) {
            logger.error("【TokenService/查询token数据库错误】"+e.getMessage());
        }
        return token;
    }
}

