package com.product.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "product")
@XmlRootElement(name="product")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
// In here, the Product class implements the Serializable interface, making any User object serializable. The serialVersionUID is a unique identifier for serialized objects. If you modify the object class (for instance, add a new field), you must update this version ID to ensure proper deserialization.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    @Column(name = "product_id") //database table name
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Float price;

    @Column(name = "description")
    private String description;

    public Product(){

    }

    public Product(Integer id, String name, Float price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
























