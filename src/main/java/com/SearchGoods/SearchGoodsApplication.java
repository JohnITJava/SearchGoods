package com.SearchGoods;

import com.SearchGoods.model.Categories;
import com.SearchGoods.model.Goods;
import com.SearchGoods.repository.CategoriesRepository;
import com.SearchGoods.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SearchGoodsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchGoodsApplication.class, args);
	}
}