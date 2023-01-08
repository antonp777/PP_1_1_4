package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;



public class Main {


        public static void main (String[] args){

                UserDao userDao = new UserDaoHibernateImpl();

                User user1 = new User ("Pety", "Sidorov", (byte) 25);
                User user2 = new User ("Natasha", "Petrova", (byte) 33);
                User user3 = new User ("Ivan", "Pupkin", (byte) 45);
                User user4 = new User ("Fedor", "Ivanov", (byte) 53);

                userDao.createUsersTable();

                userDao.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
                System.out.println("User с именем – " + user1.getName() + " добавлен в базу данных");

                userDao.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
                System.out.println("User с именем – " + user2.getName() + " добавлен в базу данных");

                userDao.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
                System.out.println("User с именем – " + user3.getName() + " добавлен в базу данных");

                userDao.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
                System.out.println("User с именем – " + user4.getName() + " добавлен в базу данных");


                List<User> usersList = userDao.getAllUsers();
                System.out.println(usersList);

                userDao.cleanUsersTable();

                userDao.dropUsersTable();

        }


}

