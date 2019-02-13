package ru.inno.project.entity;

/**
 * POJO класс для таблицы user
 *
 * @author Kuzina Anastasia
 */
public class User {

    private int id;
    private String login;
    private String password;
    private String name;
    private String email;
    private String phone_number;
    private int role_id;
    private int city_id;
    private boolean is_actual;


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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public boolean is_actual() {
        return is_actual;
    }

    public void setActual(boolean is_actual) {
        this.is_actual = is_actual;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", role_id='" + role_id + '\'' +
                ", city_id='" + city_id + '\'' +
                ", is_actual='" + is_actual + '\'' +
                '}';
    }
}
