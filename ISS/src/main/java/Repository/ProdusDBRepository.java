package Repository;

import Domain.Agent;
import Domain.Produs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProdusDBRepository implements ProdusRepository{
    private JdbcUtils dbUtils;
    public ProdusDBRepository(Properties props) {
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public int size() {
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preparedStatement=con.prepareStatement("select count(*) as [SIZE] from products")){
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
    public void save(Produs entity) {
    Connection con = dbUtils.getConnection();
        try(PreparedStatement preparedStatement=con.prepareStatement("insert into products(pret,cantitate,nume,descriere) values(?,?,?,?)")){
            preparedStatement.setDouble(1,entity.getPret());
            preparedStatement.setInt(2,entity.getCantitate());
            preparedStatement.setString(3,entity.getNume());
            preparedStatement.setString(4,entity.getDescriere());
            preparedStatement.executeUpdate();
        }catch (SQLException ex){
            System.out.println("Error DB "+ex);
        }
    }

    @Override
    public void delete(Long aLong) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement("delete from products where id = ?")) {
            preparedStatement.setInt(1, Math.toIntExact(aLong));
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }

    @Override
    public void update(Long aLong, Produs entity) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement("update products set pret = ?, cantitate = ?,nume=?,descriere=? where id = ?")) {
            preparedStatement.setDouble(1,entity.getPret());
            preparedStatement.setInt(2,entity.getCantitate());
            preparedStatement.setString(3,entity.getNume());
            preparedStatement.setString(4,entity.getDescriere());
            preparedStatement.setInt(5, Math.toIntExact(aLong));
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }

    @Override
    public Produs findOne(Long aLong) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement("select * from products where id = ?")) {
            preparedStatement.setInt(1, Math.toIntExact(aLong));
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    double pret=resultSet.getDouble("pret");
                    int cantitate=resultSet.getInt("cantitate");
                    String nume = resultSet.getString("nume");
                    String descriere = resultSet.getString("descriere");
                    Produs user=new Produs(pret, nume,descriere,cantitate);
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
    public Iterable<Produs> findAll() {
        List<Produs> userList = new ArrayList<>();
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement("select * from products")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    double pret=resultSet.getDouble("pret");
                    int cantitate=resultSet.getInt("cantitate");
                    String nume = resultSet.getString("nume");
                    String descriere = resultSet.getString("descriere");
                    Produs user=new Produs(pret, nume,descriere,cantitate);
                    user.setId((long) id);
                    userList.add(user);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
        return userList;
    }
}
