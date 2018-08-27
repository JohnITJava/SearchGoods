package com.SearchGoods.service;

import com.SearchGoods.model.Categories;
import com.SearchGoods.model.Goods;

import java.util.List;

public interface GoodsService {

    public Goods create(Goods goods);

    List<Goods> searchGoods(List<SearchCriteria> params);
}
