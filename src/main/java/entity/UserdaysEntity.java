package entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "userdays", schema = "fitnessjava")
public class UserdaysEntity {
    private int id;
    private Date userDate;
    private Byte done;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userDate", nullable = false)
    public Date getUserDate() {
        return userDate;
    }

    public void setUserDate(Date userDate) {
        this.userDate = userDate;
    }

    @Basic
    @Column(name = "done", nullable = true)
    public Byte getDone() {
        return done;
    }

    public void setDone(Byte done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserdaysEntity that = (UserdaysEntity) o;

        if (id != that.id) return false;
        if (userDate != null ? !userDate.equals(that.userDate) : that.userDate != null) return false;
        if (done != null ? !done.equals(that.done) : that.done != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userDate != null ? userDate.hashCode() : 0);
        result = 31 * result + (done != null ? done.hashCode() : 0);
        return result;
    }
}
