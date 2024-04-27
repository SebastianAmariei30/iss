package Domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Agent extends Entity<Long>{
    private String username;
    private String password;
    private LocalDate dataNasterii;

    public Agent(String username, String password, LocalDate dataNasterii) {
        this.username = username;
        this.password = password;
        this.dataNasterii = dataNasterii;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDataNasterii() {
        return dataNasterii;
    }

    public void setDataNasterii(LocalDate dataNasterii) {
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
