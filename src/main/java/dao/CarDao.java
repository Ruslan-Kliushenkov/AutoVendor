package dao;

import enty.Car;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.HibernateUtil;

import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CarDao {

    @SneakyThrows
    public void createCar(Car car) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Object object = session.save(car);
            session.get(Car.class, (Serializable) object);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    @SneakyThrows
    public void redactCar(int id, String newTitle, int newPrice, String newGearType, int newFuelVolume) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();


            String hql = "UPDATE Car set title = :newTitle , price = :newPrice , gear_type = :newGearType , fuel_volume = :newFuelVolume" + "WHERE id = :carId";
            Query query = session.createQuery(hql);
            query.setParameter("title", newTitle);
            query.setParameter("newPrice", newPrice);
            query.setParameter("newGearType", newGearType);
            query.setParameter("newFuelVolume", newFuelVolume);
            query.setParameter("carId", id);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }



    public Car getCarById(int id) {

        Transaction transaction = null;
        Car car = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = " FROM Car C WHERE C.id = :carId";
            Query query = session.createQuery(hql);
            query.setParameter("carId", id);
            List results = query.getResultList();

            if (results != null && !results.isEmpty()) {
                car = (Car) results.get(0);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return car;
    }

    public Car getCarByName(String title) {

        Transaction transaction = null;
        Car car = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = " FROM Car C WHERE C.title = :titleCar";
            Query query = session.createQuery(hql);
            query.setParameter("titleCar", title);
            List results = query.getResultList();

            if (results != null && !results.isEmpty()) {
                car = (Car) results.get(0);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return car;
    }

    public void deleteCar(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Car car = session.get(Car.class, id);
            if (car != null) {
                String hql = "DELETE FROM Car " + "WHERE id = :carId";
                Query query = session.createQuery(hql);
                query.setParameter("carId", id);
                int result = query.executeUpdate();
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteCars() {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            if (!getCars().isEmpty()) {
                String hql = "DELETE FROM Car " + "WHERE id >= 0";
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Car> getCarsByRange(int start, int finish) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Car WHERE price >= start AND price <= finish ", Car.class).list();
        }
    }


    public List<Car> getCars() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Car", Car.class).list();
        }
    }

    public boolean checkExistById(int id){
        boolean result = false;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = " FROM Car C WHERE C.id = :carId";
            Query query = session.createQuery(hql);
            query.setParameter("carId", id);
            List results = query.getResultList();

            if (results != null && !results.isEmpty()) {
                result = true;
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }

    public boolean checkExistByName(String title){
        Transaction transaction = null;
        boolean answer = false;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = " FROM Car C WHERE C.title = :titleCar";
            Query query = session.createQuery(hql);
            query.setParameter("titleCar", title);
            List results = query.getResultList();

            if (results != null && !results.isEmpty()) {
                answer = true;
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return answer;
    }
}
