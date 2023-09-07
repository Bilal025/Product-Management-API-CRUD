package com.product.dop;

import com.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDop extends JpaRepository<Product, Integer> {

}
