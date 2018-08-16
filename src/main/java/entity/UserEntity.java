package entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Collection;

@Entity
@Table(name = "user", schema = "fitnessjava")
@NamedQueries({
        @NamedQuery(name = "UserEntity.findAll",
                query="SELECT u FROM UserEntity u"),
        @NamedQuery(name = "UserEntity.findByUsername",
                query="SELECT u FROM UserEntity u WHERE u.user = :user"),
        @NamedQuery(name = "UserEntity.findByUsernameAndPassword",
                query="SELECT u FROM UserEntity u WHERE u.user = :user and u.password = :password")
})
public class UserEntity {


    public String user;
    public String password;
    private UserprofileEntity owner;

    @Id
    @Column(name = "user", nullable = false, length = 45)
    public String getUser() {
        return user;
    }

    @OneToOne(targetEntity=UserprofileEntity.class, mappedBy = "username", optional=false,
            cascade = {CascadeType.PERSIST})
    public UserprofileEntity getOwner() { return owner; };

    public void setOwner(UserprofileEntity owner) { this.owner = owner; }

    public void setUser(String user) {
        this.user = user;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 200)
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

        UserEntity that = (UserEntity) o;

        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
