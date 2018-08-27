package com.SearchGoods.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "goods")
@EntityListeners(AuditingEntityListener.class)
public class Goods{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private Long prod_id;

    @NotBlank
    private String name;

    @NotBlank
    private String descr;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "good_cat",
            joinColumns = { @JoinColumn(name = "prod_id")},
            inverseJoinColumns = { @JoinColumn(name = "cat_id") })
    @JsonManagedReference
    private Set<Categories> categories = new HashSet<>();

    public Goods() {
    }

    public Goods(String name, String descr, Set<Categories> cats) {
        this.name = name;
        this.descr = descr;
        //this.cats = cats;
    }

    public Long getProd_id() {
        return prod_id;
    }

    public void setProd_id(Long prod_id) {
        this.prod_id = prod_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Set<Categories> getCategories() {
        return categories;
    }

    public void setCategories(Set<Categories> categories) {
        this.categories = categories;
    }
}
