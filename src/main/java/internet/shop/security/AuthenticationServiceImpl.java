package internet.shop.security;

import internet.shop.exeptions.AuthenticationExeption;
import internet.shop.lib.Inject;
import internet.shop.lib.Service;
import internet.shop.model.User;
import internet.shop.service.UserService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationExeption {
        User userFromDB = userService.findByLogin(login)
                .orElseThrow(() ->
                        new AuthenticationExeption("Incorrect username or password"));
        if (userFromDB.getPassword().equals(password)) {
            return userFromDB;
        } else {
            throw new AuthenticationExeption("Incorrect username or password");
        }
    }
}
