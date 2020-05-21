package internet.shop.security;

import internet.shop.exeptions.AuthenticationExeption;
import internet.shop.model.User;
import java.sql.SQLException;

public interface AuthenticationService {
    User login(String login, String password) throws AuthenticationExeption, SQLException;
}
