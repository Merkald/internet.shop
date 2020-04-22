package internet.shop.model;

import internet.shop.storage.Storage;

import java.util.Objects;
import java.util.Optional;

public class User {
    private Long userId;
    private String fName;
    private String lName;
    private int age;
    private String login;
    private String email;
    private Integer phone;
    private String password;

    public User(String fName, String lName
            , int age, String login, String email, String password) {
        userId = ++Storage.usersId;
        this.fName = fName;
        this.lName = lName;
        this.age = age;
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
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
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getAge() == user.getAge() &&
                getUserId().equals(user.getUserId()) &&
                getfName().equals(user.getfName()) &&
                getlName().equals(user.getlName()) &&
                getLogin().equals(user.getLogin()) &&
                getEmail().equals(user.getEmail()) &&
                Objects.equals(getPhone(), user.getPhone()) &&
                getPassword().equals(user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getfName(), getlName(), getAge(), getLogin(), getEmail(), getPhone(), getPassword());
    }
}
