package com.SearchGoods.SearchGoods.service;

import com.SearchGoods.SearchGoods.dao.CarsDao;
import com.SearchGoods.SearchGoods.model.Cars;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarsService {

    Cars findById(int id);

    void saveCar(Cars car);

    void updateCar(Cars car);

    void deleteCarByName(String name);

    List<Cars> findAllCars();

    /*boolean isUserSSOUnique(Integer id, String sso);*/

}
