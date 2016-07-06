package de.schroeder.checkout.web.controller;

import de.schroeder.checkout.jpa.domain.SkuEntity;
import de.schroeder.checkout.service.CheckOutService;
import de.schroeder.checkout.service.SkuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.Collection;

/**
 * @author schroeder
 * @date 06. Jul 2016
 */
@Controller
class CheckoutController {

    @Inject
    private CheckOutService checkOutService;

    @RequestMapping( value = "/checkout/scan",
                     method = RequestMethod.POST )
    public void scan( @RequestBody( required = false ) SkuEntity sku ) {

        checkOutService.scan( sku );
    }
}