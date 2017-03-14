package com.pedroaugust8.common.models;

import java.util.Objects;
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotNull;


/**
 * @author Pedro Barros
 */
public class User {
    private String id;
    
    //@NotNull
    //@Min(6)
    private String username;
    
    //@NotNull
    //@Min(6)
    private String password;

    public String getid() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

    @Override
    public String toString() {
        return "User{id='" + id + "', username='" + username + "', password='" + password + "'}";
    }
}