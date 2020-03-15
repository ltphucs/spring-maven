package com.cg.tutorial.controller.admin;

import com.cg.tutorial.model.ProductLine;
import com.cg.tutorial.service.ProductLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class ProductLineController extends AdminBaseController {
    private final  String ACTION = "add";
    private final  String TITLE = "Add new";
    private final  String TERM = "Product Line";

    @Autowired
    private ProductLineService productLineService;

    @GetMapping("/productline/")
    public ModelAndView index(){

        Iterable<ProductLine> productLines = productLineService.findAll();
        ModelAndView modelAndView = new ModelAndView("/admin/productline/index");
        modelAndView.addObject("productLines",productLines);

        modelAndView.addObject("title",TITLE);
        modelAndView.addObject("term",TERM);

        return modelAndView;
    }
    @GetMapping("/productline/add")
    public ModelAndView showAddUpdateForm(){
        ModelAndView modelAndView = new ModelAndView("/admin/productline/add");
        modelAndView.addObject("productLine",new ProductLine());
        modelAndView.addObject("action",ACTION);
        modelAndView.addObject("term",TERM);
        modelAndView.addObject("title",TITLE);

        modelAndView.addObject("alert",ALERT_INFO);

        return  modelAndView;
    }


    @PostMapping("/productline/add")
    public ModelAndView saveAddForm(@ModelAttribute("productLine") ProductLine productLine){
        productLineService.save(productLine);

        ModelAndView modelAndView = new ModelAndView("/admin/productline/add");
        modelAndView.addObject("productLine",new ProductLine());
        modelAndView.addObject("action",ACTION);
        modelAndView.addObject("term",TERM);
        modelAndView.addObject("title",TITLE);
        modelAndView.addObject("alert",ALERT_SUCCESS);

        modelAndView.addObject("message", ACTION_ADD_SUCCESS);

        return  modelAndView;

    }
}

