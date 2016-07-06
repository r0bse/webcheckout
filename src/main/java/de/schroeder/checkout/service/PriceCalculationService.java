package de.schroeder.checkout.service;

import de.schroeder.checkout.jpa.domain.DiscountEntity;
import de.schroeder.checkout.jpa.domain.SkuEntity;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * @author schroeder
 * @date 06. Jul 2016
 */
@Service
public class PriceCalculationService {

    @Inject
    private DiscountService discountService;

    /**
     * calculate price for given products
     * when amount equals or is bigger than the defined treshold, apply the discount
     *
     * @param sku
     * @param amount
     * @return
     */
    public Double calculatePrice( SkuEntity sku,
                                  Integer amount ) {

        DiscountEntity discountEntity = discountService.getDiscountByAmount( sku, amount );

        return amount * sku.getDefaultCentPrice() * discountEntity.getDiscount();
    }
}
