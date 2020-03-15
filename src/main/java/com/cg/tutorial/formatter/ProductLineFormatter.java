package com.cg.tutorial.formatter;

import com.cg.tutorial.model.ProductLine;
import com.cg.tutorial.service.ProductLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class ProductLineFormatter implements Formatter<ProductLine> {

    @Autowired
    private ProductLineService productLineService;

    @Override
    public ProductLine parse(String s, Locale locale) throws ParseException {
        return productLineService.findById(Long.parseLong(s));
    }

    @Override
    public String print(ProductLine productLine, Locale locale) {
        return "[" + productLine.getId() + ", " +productLine.getName() + "]";
    }
}
