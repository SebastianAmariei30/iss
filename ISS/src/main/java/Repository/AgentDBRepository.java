package Repository;

import Domain.Agent;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class AgentDBRepository implements AgentRepository{
    @Override
    public int size(){
        try (Session session = HibernateUtils.getSessionFactoryAgent().openSession()) {
            return session.createQuery("select count(*) from Agent", Long.class).getSingleResult().intValue();
        }
    }

    @Override
    public void save(Agent entity) {
        HibernateUtils.getSessionFactoryAgent().inTransaction(session -> session.persist(entity));
    }

    @Override
    public void delete(Long id) {
        HibernateUtils.getSessionFactoryAgent().inTransaction(session -> {
            Agent a=session.createQuery("from Agent where id=?1",Agent.class).
                    setParameter(1,id).uniqueResult();
            if (a!=null) {
                session.remove(a);
                session.flush();
            }
        });
    }
    @Override
    public Agent findOne(Long id) {
        try (Session session = HibernateUtils.getSessionFactoryAgent().openSession()) {
            return session.createSelectionQuery("from Agent where id=:idM ", Agent.class)
                    .setParameter("idM", id)
                    .getSingleResultOrNull();
        }
    }
    @Override
    public void update(Long id, Agent entity) {
        HibernateUtils.getSessionFactoryAgent().inTransaction(session -> {
            if (!Objects.isNull(session.find(Agent.class, id))) {
                session.merge(entity);
                session.flush();
            }
        });
    }
    @Override
    public Iterable<Agent> findAll() {
        try( Session session=HibernateUtils.getSessionFactoryAgent().openSession()) {
            return session.createQuery("from Agent ", Agent.class).getResultList();
        }
    }
    @Override
    public Agent findUserByUnPs(String u, String p){
        try (Session session = HibernateUtils.getSessionFactoryAgent().openSession()) {
            return session.createSelectionQuery("from Agent where username=:un and password=:ps", Agent.class)
                    .setParameter("un", u)
                    .setParameter("ps", p)
                    .getSingleResultOrNull();
        }
    }
    @Override
    public Agent findUserByUn(String u){
        try (Session session = HibernateUtils.getSessionFactoryAgent().openSession()) {
            return session.createSelectionQuery("from Agent where username=:un", Agent.class)
                    .setParameter("un", u)
                    .getSingleResultOrNull();
        }
    }
}
