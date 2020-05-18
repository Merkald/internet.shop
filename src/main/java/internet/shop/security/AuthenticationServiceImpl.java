package internet.shop.security;

import internet.shop.exeptions.AuthenticationExeption;
import internet.shop.lib.Inject;
import internet.shop.lib.Service;
import internet.shop.model.User;
import internet.shop.service.UserService;
import internet.shop.util.HashUtil;
import org.apache.log4j.Logger;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger LOGGER = Logger.getLogger(AuthenticationServiceImpl.class);
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationExeption {
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
