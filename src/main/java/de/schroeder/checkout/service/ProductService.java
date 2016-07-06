package de.schroeder.checkout.service;

import de.schroeder.checkout.jpa.domain.SkuEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.HashMap;

/**
 * @author schroeder
 * @date 06. Jul 2016
 */
@Service
public class ProductService {

    @Inject
    private SkuService skuService;
    @Inject
    private DiscountService discountService;

    /**
     * when bean was created, fill database with initial values
     * (could be don also by liquibase, but this is more readable)
     */
    @PostConstruct
    @Transactional
    public void initialSetup(){

        SkuEntity skuA = skuService.createAndSaveSkuEntity( 'A', 40L);
        SkuEntity skuB = skuService.createAndSaveSkuEntity( 'B', 50L);
        SkuEntity skuC = skuService.createAndSaveSkuEntity( 'C', 25L);
        SkuEntity skuD = skuService.createAndSaveSkuEntity( 'D', 20L);

        discountService.createAndSave( calculateDiscount( 100.0, 120.0 ), 3, skuA );
        discountService.createAndSave( calculateDiscount( 80.0, 100.0 ), 2, skuB );
        discountService.createAndSave( 1.0, 1, skuC );
        discountService.createAndSave( 1.0, 1, skuD );
    }

    /**
     * calculate the discount
     *
     * @param prozentwert
     * @param grundwert
     * @return
     */
    private Double calculateDiscount( Double prozentwert,
                                      Double grundwert ) {

        Double result = prozentwert / grundwert;
        return result;
    }
}
