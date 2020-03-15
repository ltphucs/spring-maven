package com.cg.tutorial.controller.admin;

import com.cg.tutorial.model.Product;
import com.cg.tutorial.model.ProductLine;
import com.cg.tutorial.service.ProductLineService;
import com.cg.tutorial.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
@RequestMapping("/admin")
public class ProductController extends AdminBaseController{

    private final  String TERM = "Product ";

    @Autowired
    private ProductLineService productLineService;

    @Autowired
    private ProductService productService;

    @GetMapping("/product/")
    public ModelAndView index(){

        Iterable<Product> products = productService.findAll();
        ModelAndView modelAndView = new ModelAndView("/admin/product/index");
        modelAndView.addObject("products",products);

        modelAndView.addObject("title",TITLE_ADD);
        modelAndView.addObject("term",TERM);

        return modelAndView;
    }


    @GetMapping(value = "/product/add",produces = "application/json;charset=UTF-8")
    public ModelAndView showAddForm(){
        Iterable<ProductLine> productLines = productLineService.findAll();

        ModelAndView modelAndView = new ModelAndView("/admin/product/add");
        modelAndView.addObject("product",new Product(new ProductLine()));
        modelAndView.addObject("productLines",productLines);
        modelAndView.addObject("action",ACTION_ADD);
        modelAndView.addObject("term",TERM);
        modelAndView.addObject("title",TITLE_ADD);

        return  modelAndView;
    }

    @PostMapping(value = "/product/add",produces = "application/json;charset=UTF-8")
    public ModelAndView saveProduct(@ModelAttribute("product") Product product){
        //
        Iterable<ProductLine> productLines = productLineService.findAll();
        //
        productService.save(product);

        ModelAndView modelAndView = new ModelAndView("/admin/product/add");
        modelAndView.addObject("product",new Product(new ProductLine()));
        modelAndView.addObject("productLines",productLines);
        modelAndView.addObject("action",ACTION_ADD);
        modelAndView.addObject("term",TERM);
        modelAndView.addObject("title",TITLE_ADD);

        modelAndView.addObject("alert",ALERT_SUCCESS);

        modelAndView.addObject("message", ACTION_ADD_SUCCESS);

        return  modelAndView;
    }

    @GetMapping(value = "/product/edit/{id}",produces = "application/json;charset=UTF-8")
    public ModelAndView showEditForm(@PathVariable Long id){
        Product product = productService.findById(id);
        if (product!=null){
            Iterable<ProductLine> productLines = productLineService.findAll();

            ModelAndView modelAndView = new ModelAndView("/admin/product/add");
            modelAndView.addObject("product",product);
            modelAndView.addObject("productLines",productLines);
            modelAndView.addObject("action",ACTION_EDIT);
            modelAndView.addObject("term",TERM);
            modelAndView.addObject("title",TITLE_EDIT);

            return  modelAndView;
        }else{
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping(value = "/product/edit",produces = "application/json;charset=UTF-8")
    public ModelAndView updateProduct(@ModelAttribute("product") Product product){
        //
        Iterable<ProductLine> productLines = productLineService.findAll();
        //
        productService.save(product);

        ModelAndView modelAndView = new ModelAndView("/admin/product/add");
        modelAndView.addObject("product",product);
        modelAndView.addObject("productLines",productLines);
        modelAndView.addObject("action",ACTION_EDIT);
        modelAndView.addObject("term",TERM);
        modelAndView.addObject("title",TITLE_EDIT);

        modelAndView.addObject("alert",ALERT_SUCCESS);
        modelAndView.addObject("message", ACTION_EDIT_SUCCESS);

        return  modelAndView;
    }
}