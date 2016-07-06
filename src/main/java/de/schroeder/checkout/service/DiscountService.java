package de.schroeder.checkout.service;

import de.schroeder.checkout.jpa.domain.DiscountEntity;
import de.schroeder.checkout.jpa.domain.SkuEntity;
import de.schroeder.checkout.jpa.repo.DiscountJpaRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * @author schroeder
 * @date 06. Jul 2016
 */
@Service
public class DiscountService {

    @Inject
    private DiscountJpaRepository discountJpaRepository;

    /**
     * create and save a DiscountEntity
     *
     * @param discount
     * @param amount
     * @param skuEntity
     * @return
     */
    public DiscountEntity createAndSave(Double discount,
                                        Integer amount,
                                        SkuEntity skuEntity){

        return discountJpaRepository.save( new DiscountEntity(discount, amount, skuEntity) );
    }

    /**
     * retrieve the discount by given amount
     * if no discount is found, use the latest one
     *
     * @param sku
     * @param amount
     * @return
     */
    public DiscountEntity getDiscountByAmount(SkuEntity sku, Integer amount){

        return discountJpaRepository.getDiscountByAmount(sku, amount);
    }
}
