package com.SearchGoods.SearchGoods.dao;


import com.SearchGoods.SearchGoods.model.Cars;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface CarsDao {

    Cars findById(int id);

    Cars findByName(String name);

    void save(Cars car);

    void deleteByName(String name);

    List<Cars> findAllCars();

}
