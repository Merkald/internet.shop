package internet.shop.dao.jdbcimpl;

import internet.shop.dao.UserDao;
import internet.shop.exeptions.DataProcessingException;
import internet.shop.lib.Dao;
import internet.shop.model.Role;
import internet.shop.model.User;
import internet.shop.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Dao
public class UserDaoJdbcImpl implements UserDao {
    private static final int ID_COLUMN = 1;

    @Override
    public User update(User newUser) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "UPDATE internet_shop.users SET user_login = ?,"
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
            String query = "SELECT * FROM internet_shop.users "
                    + "JOIN users_roles ON users.user_id = users_roles.user_id "
                    + "JOIN roles r on users_roles.role_id = r.role_id "
                    + "WHERE user_login=?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            return getUsersFromResultSet(resultSet).stream().findFirst();
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
                    + "user_first_name, user_last_name, user_age, user_email) "
                    + "VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setInt(5, user.getAge());
            statement.setString(6, user.getEmail());
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
            String query = "SELECT * FROM internet_shop.users "
                    + "JOIN users_roles ON users.user_id = users_roles.user_id "
                    + "JOIN roles r on users_roles.role_id = r.role_id "
                    + "WHERE users.user_id=?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            return getUsersFromResultSet(resultSet).stream().findFirst();
        } catch (SQLException ex) {
            throw new DataProcessingException("Cant SELECT user with id:"
                    + id + " ALL FROM mySQL", ex);
        }
    }

    @Override
    public List<User> getAll() {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM internet_shop.users "
                    + "JOIN users_roles ON users.user_id = users_roles.user_id "
                    + "JOIN roles r on users_roles.role_id = r.role_id;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            return getUsersFromResultSet(resultSet);
        } catch (SQLException ex) {
            throw new DataProcessingException("Cant SELECT ALL FROM mySQL", ex);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM internet_shop.users_roles "
                    + "WHERE user_id=?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
            query = "DELETE FROM internet_shop.users "
                    + "WHERE user_id=?;";
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new DataProcessingException("Cant DELETE user IN mySQL", ex);
        }
    }

    private List<User> getUsersFromResultSet(ResultSet resultSet) throws SQLException {
        List<User> result = new ArrayList<>();
        while (resultSet.next()) {
            Long userId = resultSet.getLong("user_id");
            String userLogin = resultSet.getString("user_login");
            String userPass = resultSet.getString("user_password");
            String userFName = resultSet.getString("user_first_name");
            String userLName = resultSet.getString("user_last_name");
            String userEmail = resultSet.getString("user_email");
            int userAge = resultSet.getInt("user_age");
            String roleName = resultSet.getString("role_name");
            Long roleId = resultSet.getLong("role_id");
            User user = new User(userFName, userLName, userAge, userLogin, userEmail, userPass);
            user.setUserId(userId);
            Role role = Role.of(roleName);
            role.setRoleId(roleId);
            Set<Role> roles = user.getRoles();
            roles.add(role);
            user.setRole(roles);
            result.add(user);
        }
        return result;
    }
}