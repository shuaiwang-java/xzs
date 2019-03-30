package cd.IService;

import cd.entity.Token;

public interface TokenIService {

    Token getTokeByUserId(int userId) throws Exception;
}
