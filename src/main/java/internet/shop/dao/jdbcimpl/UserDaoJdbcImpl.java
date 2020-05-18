package internet.shop.dao.jdbcimpl;

import internet.shop.dao.UserDao;
import internet.shop.exeptions.DataProcessingException;
import internet.shop.lib.Dao;
import internet.shop.model.Role;
import internet.shop.model.User;
import internet.shop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Dao
public class UserDaoJdbcImpl implements UserDao {
    private static final int ID_COLUMN = 1;

    @Override
    public User update(User newUser) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "UPDATE users SET user_login = ?,"
                    + " user_password = ?,"
                    + " user_first_name = ?,"
                    + " user_last_name = ?,"
                    + " user_age = ?,"
                    + " user_email = ?"
                    + " WHERE (`user_id` = ?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, newUser.getLogin());
            statement.setString(2, newUser.getPassword());
            statement.setString(3, newUser.getFirstName());
            statement.setString(4, newUser.getLastName());
            statement.setInt(5, newUser.getAge());
            statement.setString(6, newUser.getEmail());
            statement.executeUpdate();
            return newUser;
        } catch (SQLException ex) {
            throw new DataProcessingException("Cant UPDATE product IN mySQL", ex);
        }
    }

    @Override
    public Optional<User> findByLogin(String login) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM users WHERE user_login=?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return Optional.of(getUserFromResultSet(resultSet));
        } catch (SQLException ex) {
            throw new DataProcessingException("Cant SELECT user with id:"
                    + login + " ALL FROM mySQL", ex);
        }
    }

    @Override
    public User create(User user) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "INSERT INTO internet_shop.users "
                    + "(user_login, user_password, "
                    + "user_first_name, user_last_name, user_age, user_email, salt) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setInt(5, user.getAge());
            statement.setString(6, user.getEmail());
            statement.setBytes(7, user.getSalt());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                Long userId = resultSet.getLong(ID_COLUMN);
                user.setUserId(userId);
            }
            query = "INSERT INTO users_roles"
                    + "(role_id, user_id)"
                    + "VALUES ((SELECT role_id FROM roles WHERE role_name=?), ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, "USER");
            statement.setLong(2, user.getUserId());
            statement.executeUpdate();
            return user;
        } catch (SQLException ex) {
            throw new DataProcessingException("Cant INSERT product IN mySQL", ex);
        }
    }

    @Override
    public Optional<User> get(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM users WHERE user_id=?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return Optional.of(getUserFromResultSet(resultSet));
        } catch (SQLException ex) {
            throw new DataProcessingException("Cant SELECT user with id:"
                    + id + " ALL FROM mySQL", ex);
        }
    }

    @Override
    public List<User> getAll() {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM users";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            List<User> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(getUserFromResultSet(resultSet));
            }
            return result;
        } catch (SQLException ex) {
            throw new DataProcessingException("Cant SELECT ALL FROM mySQL", ex);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM users_roles "
                    + "WHERE user_id=?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
            query = "DELETE FROM users "
                    + "WHERE user_id=?;";
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new DataProcessingException("Cant DELETE user IN mySQL", ex);
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {

        Long userId = resultSet.getLong("user_id");
        String userLogin = resultSet.getString("user_login");
        String userPass = resultSet.getString("user_password");
        String userFName = resultSet.getString("user_first_name");
        String userLName = resultSet.getString("user_last_name");
        String userEmail = resultSet.getString("user_email");
        byte[] salt = resultSet.getBytes("salt");
        int userAge = resultSet.getInt("user_age");
        User user = new User(userFName, userLName, userAge, userLogin, userEmail, userPass, salt);
        user.setUserId(userId);
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM users_roles "
                    + "JOIN roles r ON users_roles.role_id = r.role_id "
                    + "WHERE user_id=?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet resultSet1 = statement.executeQuery();
            resultSet.next();
            return insertRolesInToUserFromResultSet(resultSet1, user);
        }
    }

    private User insertRolesInToUserFromResultSet(ResultSet resultSet, User user)
            throws SQLException {
        Set<Role> roles = new HashSet<>();
        while (resultSet.next()) {
            String roleName = resultSet.getString("role_name");
            Long roleId = resultSet.getLong("role_id");
            Role role = Role.of(roleName);
            role.setRoleId(roleId);
            roles.add(role);
        }
        user.setRole(roles);
        return user;
    }
}
