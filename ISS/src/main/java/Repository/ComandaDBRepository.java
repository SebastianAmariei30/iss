package Repository;

import Domain.Agent;
import Domain.Comanda;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class ComandaDBRepository implements ComandaRepository{

    @Override
    public void save(Comanda entity){
        HibernateUtils.getSessionFactoryComanda().inTransaction(session -> session.persist(entity));
    }

    @Override
    public int size(){
        try (Session session = HibernateUtils.getSessionFactoryComanda().openSession()) {
            return session.createQuery("select count(*) from Comanda", Long.class).getSingleResult().intValue();
        }
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void update(Long aLong, Comanda entity) {

    }

    @Override
    public Comanda findOne(Long aLong) {
        return null;
    }

    @Override
    public Iterable<Comanda> findAll() {
        return null;
    }
}
