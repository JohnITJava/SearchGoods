package com.SearchGoods.SearchGoods;

import com.SearchGoods.SearchGoods.configuration.AppConfig;
import com.SearchGoods.SearchGoods.configuration.AppInitializer;
import com.SearchGoods.SearchGoods.configuration.JpaConfiguration;
import com.SearchGoods.SearchGoods.controller.AppController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;


@SpringBootApplication
@ComponentScan(basePackages = {"com.SearchGoods.SearchGoods"})
@EntityScan("com.SearchGoods.SearchGoods.model")
@EnableJpaRepositories(basePackages = "com.SearchGoods.SearchGoods.dao")
@Import(RepositoryRestMvcConfiguration.class)

public class SearchGoodsApplication{

	public static void main(String[] args) {
		//SpringApplication.run(SearchGoodsApplication.class, args);
		SpringApplication.run(new Class<?>[] {SearchGoodsApplication.class, JpaConfiguration.class, AppConfig.class, AppInitializer.class,  AppController.class}, args);
	}
}
