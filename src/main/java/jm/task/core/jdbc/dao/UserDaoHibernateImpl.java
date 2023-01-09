package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try  (Session session = Util.getSessionFactory().openSession()){
            session.beginTransaction();

            String sql = "CREATE TABLE IF NOT EXISTS users (id BIGINT not NULL PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255) not NULL, lastName VARCHAR(255) not NULL, age TINYINT not NULL)";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropUsersTable() {
        try  (Session session = Util.getSessionFactory().openSession()){
            session.beginTransaction();

            String sql = "DROP TABLE IF EXISTS users";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()){
            session.beginTransaction();

            User user = new User(name, lastName, age);
            session.save(user);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()){
            session.beginTransaction();

            User user = session.get(User.class, id);
            session.delete(user);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<User> getAllUsers() {
        List<User> usersList;
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();

            usersList = session.createQuery("from User").getResultList();

            session.getTransaction().commit();

        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
        return usersList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();

            session.createQuery("delete User").executeUpdate();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }
}
