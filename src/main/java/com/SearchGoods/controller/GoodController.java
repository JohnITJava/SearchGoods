package com.SearchGoods.controller;

import com.SearchGoods.exception.ResourceNotFoundException;
import com.SearchGoods.model.Goods;
import com.SearchGoods.repository.GoodsRepository;
import com.SearchGoods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GoodController {

    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    GoodsService goodsService;

    @GetMapping("/goods")
    public List<Goods> getAllGoods() {
        return goodsRepository.findAll();
    }

    /*@PostMapping("/goods")
    public Goods createGood(@Valid @RequestBody Goods goods) {
        return goodsRepository.save(goods);
    }*/

    @PostMapping("/goods")
    public Goods createGood(@Valid @RequestBody Goods goods) {
        return goodsService.create(goods);
    }

    @GetMapping("/goods/{prod_id}")
    public Goods getGoodsById(@PathVariable(value = "prod_id") Long goodId) {
        return goodsRepository.findById(goodId)
                .orElseThrow(() -> new ResourceNotFoundException("Good", "prod_id", goodId));
    }

    @PutMapping("/goods/{prod_id}")
    public Goods updateGood(@PathVariable(value = "prod_id") Long goodId,
                           @Valid @RequestBody Goods goodDetails) {

        Goods goods = goodsRepository.findById(goodId)
                .orElseThrow(() -> new ResourceNotFoundException("Good", "prod_id", goodId));

        goods.setName(goodDetails.getName());
        goods.setDescr(goodDetails.getDescr());

        Goods updatedGood = goodsRepository.save(goods);
        return updatedGood;
    }

    @DeleteMapping("/goods/{prod_id}")
    public ResponseEntity<?> deleteGood(@PathVariable(value = "prod_id") Long goodId) {
        Goods goods = goodsRepository.findById(goodId)
                .orElseThrow(() -> new ResourceNotFoundException("Good", "prod_id", goodId));

        goodsRepository.delete(goods);

        return ResponseEntity.ok().build();
    }
}
