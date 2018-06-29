package by.medvedev.task.model.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @Length(min = 4)
    private String username;

    @Column
    @Length(min = 4)
    private String mail;

    @Column
    private String pathToProfilePhoto;

    @Column
    @Length(min = 3)
    private String password;

    @Column
    private boolean online;

    @Column
    private long lastTimeSeen;

    private transient String submitPassword;

    public User() {
    }

    public User(@Length(min = 4) String username, @Length(min = 4) String mail, String pathToProfilePhoto, @Length(min = 3) String password, boolean online, long lastTimeSeen, String submitPassword) {
        this.username = username;
        this.mail = mail;
        this.pathToProfilePhoto = pathToProfilePhoto;
        this.password = password;
        this.online = online;
        this.lastTimeSeen = lastTimeSeen;
        this.submitPassword = submitPassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPathToProfilePhoto() {
        return pathToProfilePhoto;
    }

    public void setPathToProfilePhoto(String pathToProfilePhoto) {
        this.pathToProfilePhoto = pathToProfilePhoto;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getSubmitPassword() {
        return submitPassword;
    }

    public void setSubmitPassword(String submitPassword) {
        this.submitPassword = submitPassword;
    }

    public long getLastTimeSeen() {
        return lastTimeSeen;
    }

    public void setLastTimeSeen(long lastTimeSeen) {
        this.lastTimeSeen = lastTimeSeen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return online == user.online &&
                Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(mail, user.mail) &&
                Objects.equals(pathToProfilePhoto, user.pathToProfilePhoto) &&
                Objects.equals(password, user.password) &&
                Objects.equals(submitPassword, user.submitPassword) &&
                Objects.equals(lastTimeSeen, user.lastTimeSeen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, mail, pathToProfilePhoto, password, online, submitPassword, lastTimeSeen);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", mail='" + mail + '\'' +
                ", pathToProfilePhoto='" + pathToProfilePhoto + '\'' +
                ", online=" + online +
                ", lastTimeSeen =" + lastTimeSeen +
                '}';
    }
}
