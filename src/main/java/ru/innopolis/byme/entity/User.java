package ru.innopolis.byme.entity;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * POJO класс для таблицы user
 *
 * @author Kuzina Anastasia
 */
@Component
public class User {

    private int id;

//    @Size(min = 5, max = 30, message = "Логин должен быть от 5 до 30 символов")
    private String login;

//    @Size(min = 5, max = 15, message = "Пароль должен быть от 5 до 15 символов")
    private String password;

    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

//    @Size(min = 5, max = 30, message = "Имя должно быть от 5 до 30 символов")
    private String name;

//    @Pattern(regexp = ".*@.*\\..+", message = "Некорректный адрес электронной почты")
    private String email;

    private String phoneNumber;
    private int roleId;
    private int cityId;
    private boolean isActual;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phone_number) {
        this.phoneNumber = phone_number;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int role_id) {
        this.roleId = role_id;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int city_id) {
        this.cityId = city_id;
    }

    public boolean isActual() {
        return isActual;
    }

    public void setActual(boolean is_actual) {
        this.isActual = is_actual;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", roleId='" + roleId + '\'' +
                ", cityId='" + cityId + '\'' +
                ", isActual='" + isActual + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                roleId == user.roleId &&
                cityId == user.cityId &&
                isActual == user.isActual &&
                login.equals(user.login) &&
                password.equals(user.password) &&
                name.equals(user.name) &&
                email.equals(user.email) &&
                phoneNumber.equals(user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, name, email, phoneNumber, roleId, cityId, isActual);
    }
}
