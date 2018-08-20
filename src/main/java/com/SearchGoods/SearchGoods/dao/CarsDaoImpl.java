package com.SearchGoods.SearchGoods.dao;

import com.SearchGoods.SearchGoods.model.Cars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.Collection;
import java.util.List;


@Repository("CarsDao")
public class CarsDaoImpl extends AbstractDao<Integer, Cars> implements CarsDao {

    public Cars findById(int id) {
        Cars car = getByKey(id);
        /*if(car!=null){
            initializeCollection(car.getClass());
        }*/
        return car;
    }

    public Cars findByName(String name) {
        System.out.println("Name : "+ name);
        try{
            Cars car = (Cars) getEntityManager()
                    .createQuery("SELECT u FROM cars u WHERE u.name LIKE :name")
                    .setParameter("name", name)
                    .getSingleResult();

            /*if(car!=null){
                initializeCollection(car.getClass());
            }*/
            return car;
        }catch(NoResultException ex){
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Cars> findAllCars() {
        List<Cars> cars = getEntityManager()
                .createQuery("SELECT u FROM cars u ORDER BY u.name ASC")
                .getResultList();
        return cars;
    }

    public void save(Cars car) {
        persist(car);
    }

    public void deleteByName(String name) {
        Cars car = (Cars) getEntityManager()
                .createQuery("SELECT u FROM cars u WHERE u.name LIKE :name")
                .setParameter("name", name)
                .getSingleResult();
        delete(car);
    }
    //An alternative to Hibernate.initialize()
    protected void initializeCollection(Collection<?> collection) {
        if(collection == null) {
            return;
        }
        collection.iterator().hasNext();
    }

}
