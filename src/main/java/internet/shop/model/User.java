package internet.shop.model;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class User {
    private Long userId;
    private String firstName;
    private String lastName;
    private int age;
    private String login;
    private String email;
    private Integer phone;
    private String password;
    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRole(Set<Role> roles) {
        this.roles = roles;
    }

    public User(String firstName, String lastName,
                int age, String login, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Optional<Integer> getPhone() {
        return Optional.ofNullable(phone);
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return getAge() == user.getAge()
                && getUserId().equals(user.getUserId())
                && getFirstName().equals(user.getFirstName())
                && getLastName().equals(user.getLastName())
                && getLogin().equals(user.getLogin())
                && getEmail().equals(user.getEmail())
                && Objects.equals(getPhone(), user.getPhone())
                && getPassword().equals(user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getFirstName(), getLastName(), getAge(),
                getLogin(), getEmail(), getPhone(), getPassword());
    }

    @Override
    public String toString() {
        return "User{"
                + "userId=" + userId
                + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + ", age=" + age
                + ", login='" + login + '\''
                + ", email='" + email + '\''
                + ", phone=" + phone
                + ", password='" + password + '\''
                + '}' + "\n";
    }
}
