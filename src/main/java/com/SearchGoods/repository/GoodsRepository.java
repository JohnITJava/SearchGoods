package com.SearchGoods.repository;

import com.SearchGoods.model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {

}
