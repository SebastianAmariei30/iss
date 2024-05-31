package Repository;

import Domain.Agent;
import Domain.Comanda;
import Domain.Produs;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactoryAgent(){
        if ((sessionFactory==null)||(sessionFactory.isClosed()))
            sessionFactory=createNewSessionFactoryAgent();
        return sessionFactory;
    }

    private static  SessionFactory createNewSessionFactoryAgent(){
        sessionFactory = new Configuration()
                .addAnnotatedClass(Agent.class)
                .addAnnotatedClass(Produs.class)
                .addAnnotatedClass(Comanda.class)
                .buildSessionFactory();
        return sessionFactory;
    }

    public static SessionFactory getSessionFactoryComanda(){
        if ((sessionFactory==null)||(sessionFactory.isClosed()))
            sessionFactory=createNewSessionFactoryComanda();
        return sessionFactory;
    }

    private static  SessionFactory createNewSessionFactoryComanda(){
        sessionFactory = new Configuration()
                .addAnnotatedClass(Comanda.class)
                .buildSessionFactory();
        return sessionFactory;
    }


    public static SessionFactory getSessionFactoryProdus(){
        if ((sessionFactory==null)||(sessionFactory.isClosed()))
            sessionFactory=createNewSessionFactoryProdus();
        return sessionFactory;
    }

    private static SessionFactory createNewSessionFactoryProdus(){
        sessionFactory = new Configuration()
                .addAnnotatedClass(Produs.class)
                .buildSessionFactory();
        return sessionFactory;
    }

    public static  void closeSessionFactory(){
        if (sessionFactory!=null)
            sessionFactory.close();
    }
}
