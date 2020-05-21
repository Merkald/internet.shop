package internet.shop.security;

import internet.shop.exeptions.AuthenticationExeption;
import internet.shop.lib.Inject;
import internet.shop.lib.Service;
import internet.shop.model.User;
import internet.shop.service.UserService;
import internet.shop.util.HashUtil;
import java.sql.SQLException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationExeption, SQLException {
        User userFromDB = userService.findByLogin(login)
                .orElseThrow(() ->
                        new AuthenticationExeption("Incorrect username or password"));
        if (userFromDB.getPassword().equals(HashUtil.hashPassword(password,userFromDB.getSalt()))) {
            return userFromDB;
        } else {
            throw new AuthenticationExeption("Incorrect username or password");
        }
    }
}
