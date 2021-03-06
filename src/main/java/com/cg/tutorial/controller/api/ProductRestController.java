package com.cg.tutorial.controller.api;

import com.cg.tutorial.model.Product;
import com.cg.tutorial.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductRestController
{
    @Autowired
    private ProductService productService;

    @GetMapping("/products/")
    private ResponseEntity<List<Product>> listProducts(){
        List<Product> products= productService.findAllRest();
        if (products.isEmpty()) {
            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable("id") long id) {
        Product product = productService.findById(id);
        if (product == null) {

            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @PostMapping(value = "/products/")
    public ResponseEntity<Product> createProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder) {
        try {
            productService.save(product);
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {

        Product currentProduct = productService.findById(id);

        if (currentProduct == null) {
            System.out.println("Product with id " + id + " not found");
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }

        currentProduct.setProductCode(product.getProductCode());

        currentProduct.setProductLine(product.getProductLine());
        try {
            productService.save(currentProduct);
        }catch (Exception ex){
            return new ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Product>(currentProduct, HttpStatus.OK);
    }
}
