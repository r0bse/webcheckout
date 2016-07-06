package de.schroeder.checkout.service;

import de.schroeder.checkout.jpa.domain.SkuEntity;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author schroeder
 * @date 06. Jul 2016
 */
@Service
public class CheckOutService {

    @Inject
    private SkuService skuService;

    /**
     * scan given product and add it to checkout
     *
     * @param productName
     */
    public void scan( String productName ) {

        if ( productName.length() > 1 ) {
            throw new IllegalArgumentException( "Productname is only allowed to have a length of 1 !" );
        }

        SkuEntity sku = skuService.getByProductName( productName.charAt( 0 ) );
//        checkOutService.addToShoppingCart( sku );
    }
    public void scan( SkuEntity sku ) {

//        checkOutService.addToShoppingCart( sku );
    }

    /**
     * calculate the total price sum of all products in checkout
     *
     * @return
     */
    public int total() {

//        Collection<SkuEntity> set = checkOutService.getShoppingCartItems();
//
//        int result = set.stream()
//                .map( sku -> {
//                    Integer amount = countService.countAmount( productList, sku.getProductName() );
//                    return calculationService.calculatePrice( sku, amount );
//                } )
//                .mapToInt( i -> i.intValue() )
//                .sum();

//        return result;
        return 0;
    }

}
