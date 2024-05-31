package Repository;

import Domain.Agent;
import Domain.Produs;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProdusDBRepository implements ProdusRepository{

    @Override
    public void save(Produs entity){
        HibernateUtils.getSessionFactoryProdus().inTransaction(session -> session.persist(entity));
    }

    @Override
    public int size(){
        try (Session session = HibernateUtils.getSessionFactoryProdus().openSession()) {
            return session.createQuery("select count(*) from Produs ", Long.class).getSingleResult().intValue();
        }
    }

    @Override
    public void delete(Long aLong) {
        HibernateUtils.getSessionFactoryProdus().inTransaction(session -> {
            Produs p=session.createQuery("from Produs where id=?1",Produs.class).
                    setParameter(1,aLong).uniqueResult();
            if (p!=null) {
                session.remove(p);
                session.flush();
            }
        });
    }

    @Override
    public void update(Long aLong, Produs entity) {
        HibernateUtils.getSessionFactoryProdus().inTransaction(session -> {
            if (session.find(Produs.class, aLong) != null) {
                session.merge(entity);
                session.flush();
            }
        });
    }

    @Override
    public Produs findOne(Long aLong) {
        try (Session session = HibernateUtils.getSessionFactoryProdus().openSession()) {
            return session.createSelectionQuery("from Produs where id=:idM ", Produs.class)
                    .setParameter("idM", aLong)
                    .getSingleResultOrNull();
        }
    }

    @Override
    public Iterable<Produs> findAll() {
        try( Session session=HibernateUtils.getSessionFactoryProdus().openSession()) {
            return session.createQuery("from Produs", Produs.class).getResultList();
        }
    }
}
