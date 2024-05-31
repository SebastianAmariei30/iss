package Domain;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table( name = "agents" )
public class Agent extends Domain.Entity<Long>{
    private String username;
    private String password;
    private String dataNasterii;
    public Agent(){
        id=0L;
        username=password="default";
        dataNasterii="";
    }
    public Agent(String username, String password, String dataNasterii) {
        this.username = username;
        this.password = password;
        this.dataNasterii = dataNasterii;
    }
    @Id
    @GeneratedValue(generator="increment")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "birthdate")
    public String getDataNasterii() {
        return dataNasterii;
    }

    public void setDataNasterii(String dataNasterii) {
        this.dataNasterii = dataNasterii;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Agent agent = (Agent) o;
        return Objects.equals(username, agent.username) && Objects.equals(password, agent.password) && Objects.equals(dataNasterii, agent.dataNasterii);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password, dataNasterii);
    }
}
