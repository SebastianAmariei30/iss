package Repository;

import Domain.Agent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class AgentDBRepository implements AgentRepository{
    private JdbcUtils dbUtils;

    public AgentDBRepository(Properties props) {
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public Agent findUserByUnPs(String username, String password) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement("select * from agents where username = ? and password = ?")) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String u = resultSet.getString("username");
                    String p = resultSet.getString("password");
                    LocalDate birthdate = LocalDate.parse(resultSet.getString("birthdate"));
                    Agent user=new Agent(u, p,birthdate);
                    user.setId((long)id);
                    return user;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
        return null;
    }
    @Override
    public Agent findUserByUn(String username) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement("select * from agents where username = ?")) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String u = resultSet.getString("username");
                    String p = resultSet.getString("password");
                    LocalDate birthdate = LocalDate.parse(resultSet.getString("birthdate"));
                    Agent user=new Agent(u, p,birthdate);
                    user.setId((long)id);
                    return user;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
        return null;
    }
    @Override
    public Agent findOne(Long aLong) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement("select * from agents where id = ?")) {
            preparedStatement.setInt(1, Math.toIntExact(aLong));
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    LocalDate birthdate = LocalDate.parse(resultSet.getString("birthdate"));
                    Agent user=new Agent(username, password,birthdate);
                    user.setId(aLong);
                    return user;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
        return null;
    }

    @Override
    public Iterable<Agent> findAll() {
        List<Agent> userList = new ArrayList<>();
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement("select * from agents")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    LocalDate birthdate = LocalDate.parse(resultSet.getString("birthdate"));
                    Agent user=new Agent(username, password,birthdate);
                    user.setId((long) id);
                    userList.add(user);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
        return userList;
    }

    @Override
    public int size() {
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preparedStatement=con.prepareStatement("select count(*) as [SIZE] from agents")){
            try(ResultSet resultSet=preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("SIZE");
                }
            }
        }catch (SQLException ex){
            System.out.println("Error DB "+ex);
        }
        return 0;
    }

    @Override
    public void save(Agent entity) {
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preparedStatement=con.prepareStatement("insert into agents(username,password,birthdate) values(?,?,?)")){
            preparedStatement.setString(1,entity.getUsername());
            preparedStatement.setString(2,entity.getPassword());
            preparedStatement.setString(3,entity.getDataNasterii().toString());
            preparedStatement.executeUpdate();
        }catch (SQLException ex){
            System.out.println("Error DB "+ex);
        }
    }

    @Override
    public void delete(Long aLong) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement("delete from agents where id = ?")) {
            preparedStatement.setInt(1, Math.toIntExact(aLong));
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }

    @Override
    public void update(Long aLong,Agent entity) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement("update agents set username = ?, password = ?,birthdate=? where id = ?")) {
            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getDataNasterii().toString());
            preparedStatement.setInt(4, Math.toIntExact(aLong));
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }
}
