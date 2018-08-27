package com.SearchGoods.service;

import com.SearchGoods.model.Categories;
import com.SearchGoods.model.Goods;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Goods create(Goods goods) {
        for (Categories cats : goods.getCategories()) {
            cats.setGoods((Set<Goods>) goods);
        }
        entityManager.persist(goods);
        return goods;
    }

    @Override
    public List<Goods> searchGoods(List<SearchCriteria> params) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Goods> query = builder.createQuery(Goods.class);
        Root r = query.from(Goods.class);

        Predicate predicate = builder.conjunction();

        for (SearchCriteria param : params) {
            if (param.getOperation().equalsIgnoreCase(">")) {
                predicate = builder.and(predicate,
                        builder.greaterThanOrEqualTo(r.get(param.getKey()),
                                param.getValue().toString()));
            } else if (param.getOperation().equalsIgnoreCase("<")) {
                predicate = builder.and(predicate,
                        builder.lessThanOrEqualTo(r.get(param.getKey()),
                                param.getValue().toString()));
            } else if (param.getOperation().equalsIgnoreCase(":")) {
                if (r.get(param.getKey()).getJavaType() == String.class) {
                    predicate = builder.and(predicate,
                            builder.like(r.get(param.getKey()),
                                    "%" + param.getValue() + "%"));
                } else {
                    predicate = builder.and(predicate,
                            builder.equal(r.get(param.getKey()), param.getValue()));
                }
            }
        }
        query.where(predicate);

        List<Goods> result = entityManager.createQuery(query).getResultList();
        return result;
    }

    public void save(Goods entity) {
        entityManager.persist(entity);
    }

}
