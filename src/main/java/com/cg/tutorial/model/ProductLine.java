package com.cg.tutorial.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "productlines")
@Where(clause = "deleted=0")
public class ProductLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private String textDescription;
    private String htmlDescription;
    private String image;

    public CommonsMultipartFile[] getFileImage() {
        return fileImage;
    }

    public void setFileImage(CommonsMultipartFile[] fileImage) {
        this.fileImage = fileImage;
    }

    @Transient
    @JsonIgnore
    private CommonsMultipartFile[] fileImage;

    public ProductLine() {
    }

    @JsonIgnore
    @OneToMany(mappedBy = "productLine")
    private  Set<Product> products;

    public Set<Product> getProducts() {
        return products;
    }

    public short getDeleted() {
        return deleted;
    }

    public void setDeleted(short deleted) {
        this.deleted = deleted;
    }

    private short deleted;

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTextDescription() {
        return textDescription;
    }

    public void setTextDescription(String textDescription) {
        this.textDescription = textDescription;
    }

    public String getHtmlDescription() {
        return htmlDescription;
    }

    public void setHtmlDescription(String htmlDescription) {
        this.htmlDescription = htmlDescription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
