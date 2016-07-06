package de.schroeder.checkout.web.controller;

import de.schroeder.checkout.jpa.domain.SkuEntity;
import de.schroeder.checkout.service.CheckOutService;
import de.schroeder.checkout.service.ProductService;
import de.schroeder.checkout.service.SkuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

/**
 * @author schroeder
 * @date 06. Jul 2016
 */
@Controller
class IndexController {

    @Inject
    private SkuService skuService;

    /**
     * entry point, loads index.html
     *
     * @return
     */
    @RequestMapping( "/" )
    public String index() {
        return "index";
    }

    /**
     * used to expose reference data to web view
     * @return
     */
    @ModelAttribute("skus")
    public Collection<SkuEntity> skus() {
        return skuService.findAll();
    }
}