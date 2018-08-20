package com.SearchGoods.SearchGoods.service;

import com.SearchGoods.SearchGoods.dao.CarsDao;
import com.SearchGoods.SearchGoods.model.Cars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("CarsService")
public class CarsServiceImpl implements CarsService {

    @Autowired
    @Qualifier("CarsDao")
    private CarsDao dao;

    public Cars findById(int id) {
        return dao.findById(id);
    }

    public Cars findByName(String name) {
        Cars car = dao.findByName(name);
        return car;
    }

    public void saveCar(Cars car) {
        dao.save(car);
    }

    /*
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends.
     */
    public void updateCar(Cars car) {
        Cars entity = dao.findById(car.getId());
        if(entity!=null){
            entity.setName(car.getName());
            entity.setDescription(car.getDescription());
        }
    }


    public void deleteCarByName(String name) {
        dao.deleteByName(name);
    }

    public List<Cars> findAllCars() {
        return dao.findAllCars();
    }

    /*public boolean isUserSSOUnique(Integer id, String sso) {
        User user = findBySSO(sso);
        return ( user == null || ((id != null) && (user.getId() == id)));
    }*/

}
