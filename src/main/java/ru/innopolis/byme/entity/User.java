package ru.innopolis.byme.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * POJO класс для таблицы user
 *
 * @author Kuzina Anastasia
 */
@Component
@Getter
@Setter
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
