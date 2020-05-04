package internet.shop.security;

import internet.shop.exeptions.AuthenticationExeption;
import internet.shop.model.User;

public interface AuthenticationService {
    User login(String login, String password) throws AuthenticationExeption;
}
