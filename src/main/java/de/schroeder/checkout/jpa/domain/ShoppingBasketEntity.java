package de.schroeder.checkout.jpa.domain;

import javax.persistence.*;
import java.util.List;

/**
 * @author schroeder
 * @date 06. Jul 2016
 */
@Entity
@Table( name = "shopping_basket" )
public class ShoppingBasketEntity extends BaseEntity<ShoppingBasketEntity> {

    /**
     * one directional m:n relation to items in basket
     */
    @ManyToMany
    @JoinTable( name = "basket_has_sku",
                joinColumns = @JoinColumn( name = "basket", referencedColumnName = "id" ),
                inverseJoinColumns = @JoinColumn( name = "sku", referencedColumnName = "id" ) )
    private List<SkuEntity> skuEntityList;

    /**
     * JPA demands a default constructor, which we don't want to invoke manually
     */
    @Deprecated
    protected ShoppingBasketEntity() {
    }

    public List<SkuEntity> getSkuEntityList() {
        return skuEntityList;
    }
}
