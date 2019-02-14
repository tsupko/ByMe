package ru.innopolis.project.entity;

import org.springframework.stereotype.Component;

/**
 * POJO класс для таблицы user
 *
 * @author Kuzina Anastasia
 */
@Component
public class User {

    private int id;
    private String login;
    private String password;
    private String name;
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
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", roleId='" + roleId + '\'' +
                ", cityId='" + cityId + '\'' +
                ", isActual='" + isActual + '\'' +
                '}';
    }
}
