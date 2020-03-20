package com.cg.tutorial.controller.api;

import com.cg.tutorial.model.Product;
import com.cg.tutorial.model.ProductLine;
import com.cg.tutorial.service.ProductLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductLineRestController {
    @Autowired
    private ProductLineService productLineService;

    @GetMapping("/productlines/")
    private ResponseEntity<List<ProductLine>> listProductLines(){
        List<ProductLine> products= productLineService.findAllRest();
        if (products.isEmpty()) {
            return new ResponseEntity<List<ProductLine>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<ProductLine>>(products, HttpStatus.OK);
    }

}
