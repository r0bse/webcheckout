package de.schroeder.checkout.jpa.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by robert on 04.07.16.
 */
@Entity
@Table( name = "sku" )
public class SkuEntity extends BaseEntity<SkuEntity>{

    @Column( name = "product_name" )
    private Character productName;

    @Column( name = "default_price" )
    private Long defaultCentPrice;

    @ManyToOne
    @JoinColumn(name="shopping_basket", nullable=false)
    private ShoppingBasketEntity shoppingBasketEntity;

    @OneToMany( cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.LAZY,
                mappedBy = "skuEntity" )
    private Set<DiscountEntity> discountEntities;

    /**
     * JPA demands a default constructor, which we don't want to invoke manually
     */
    @Deprecated
    protected SkuEntity(){}

    /**
     * @param productName the productname as Character
     * @param defaultCentPrice the price which is normally paid
     */
    public SkuEntity( Character productName,
                      Long defaultCentPrice) {

        this.productName = productName;
        this.defaultCentPrice = defaultCentPrice;
    }

    public Long getDefaultCentPrice() {
        return defaultCentPrice;
    }

    public Character getProductName() {
        return productName;
    }

    public Set<DiscountEntity> getDiscountEntities() {
        return discountEntities;
    }
}
