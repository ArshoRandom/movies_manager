package models.user;

import util.digest.DigestUtils;

import java.util.Objects;
/**
 *
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 */
public class User {

    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;

    private User(Builder builder) {
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.password = builder.password;
        this.username = builder.username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(username, user.username) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, surname, name);
    }

    @Override
    public String toString() {
        return String.format("%s , %s , %s , %s , %s", this.username, this.password, this.name, this.surname, this.email);
    }

    public static class Builder {
        private String name;
        private String surname;
        private String username;
        private String email;
        private String password;

        public Builder setName(String name) {

            this.name = name;
            return this;


        }

        public Builder setSurname(String surname) {

            this.surname = surname;
            return this;

        }

        public Builder setUsername(String username) {

            this.username = username;
            return this;

        }

        public Builder setEmail(String email) {

            this.email = email;
            return this;

        }

        public Builder setPassword(String password) {
            this.password = DigestUtils.md5(password);
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

}
