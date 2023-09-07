package com.product.serviceimpl;

import com.product.dop.ProductDop;
import com.product.model.Product;
import com.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDop productDop;

    @Override
    public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
        try {
            if (validateAddNewProduct(requestMap)) {
                productDop.save(getProductFormMap(requestMap, false));

                return new ResponseEntity<String>("{\"message\":\"" + "Product Added" + "\"}", HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("{\"message\":\"" + "Invalid Data" + "\"}", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"" + "Something went wrong" + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Product>> getAllProduct() {
        try {
            return new ResponseEntity<List<Product>>(productDop.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<List<Product>>(new ArrayList<Product>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateProduct(Map<String, String> requestMap) {
        try {
            if (validateAddNewProduct(requestMap)){
                Optional optional = productDop.findById(Integer.parseInt(requestMap.get("productId")));
                if (!optional.isEmpty()){
                    productDop.save(getProductFormMap(requestMap,true));
                    return new ResponseEntity<String>("{\"message\":\"" + "Product Updated" + "\"}", HttpStatus.OK);
                }else {
                    return new ResponseEntity<String>("{\"message\":\"" + "Product does ont exist" + "\"}", HttpStatus.BAD_REQUEST);
                }

            }else {
                return new ResponseEntity<String>("{\"message\":\"" + "Invalid Data" + "\"}", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"" + "Something went wrong" + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> deleteProduct(int productId) {
        try {
            if (productId >=0){
                productDop.deleteById(productId);
                return new ResponseEntity<String>("{\"message\":\"" + "Product Deleted" + "\"}", HttpStatus.OK);
            }else {
                return new ResponseEntity<String>("{\"message\":\"" + "Invalid ID" + "\"}", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"" + "Something went wrong" + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private boolean validateAddNewProduct(Map<String, String> requestMap) {

        if (requestMap.containsKey("name") && requestMap.containsKey("price") && requestMap.containsKey("description")) {
            return true;
        }
        return false;
    }

    private Product getProductFormMap(Map<String, String> requestMap, Boolean isAdd) {
        Product product = new Product();
        if (isAdd) {
            product.setId(Integer.parseInt(requestMap.get("productId")));
        }
        product.setName(requestMap.get("name"));
        product.setPrice(Float.parseFloat(requestMap.get("price")));
        product.setDescription(requestMap.get("description"));
        return product;
    }
}
