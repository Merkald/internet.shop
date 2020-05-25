package internet.shop.model;

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
    private byte[] salt;
    private Set<Role> roles;

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRole(Set<Role> roles) {
        this.roles = roles;
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
                && userId.equals(user.getUserId())
                && firstName.equals(user.getFirstName())
                && lastName.equals(user.getLastName())
                && age == user.getAge()
                && login.equals(user.getLogin())
                && email.equals(user.getEmail())
                && Objects.equals(phone, user.getPhone())
                && password.equals(user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, login,
                email, age, phone, password);
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
