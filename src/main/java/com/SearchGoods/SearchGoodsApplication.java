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
public class SearchGoodsApplication implements CommandLineRunner {

	@Autowired
	private CategoriesRepository categoriesRepository;

	@Autowired
	private GoodsRepository goodsRepository;

	public static void main(String[] args) {
		SpringApplication.run(SearchGoodsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Cleanup the tables
		goodsRepository.deleteAllInBatch();
		categoriesRepository.deleteAllInBatch();


		Goods goods = new Goods("Java", "It's very common");

		// Create two tags
		Categories cat1 = new Categories("Book");
		Categories cat2 = new Categories("Program");


		// Add tag references in the post
		goods.getCategories().add(cat1);
		goods.getCategories().add(cat2);

		// Add post reference in the tags
		cat1.getGoods().add(goods);
		cat2.getGoods().add(goods);

		goodsRepository.save(goods);
	}
}