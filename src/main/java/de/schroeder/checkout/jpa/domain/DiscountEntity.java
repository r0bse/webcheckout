package de.schroeder.checkout.jpa.domain;

import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by robert on 04.07.16.
 */
@Entity
@Table( name = "discount" )
public class DiscountEntity extends BaseEntity<DiscountEntity>{

    @Column( name = "discount" )
    private Double discount;

    @Column( name = "amount" )
    private Integer amount;

    @ManyToOne
    @JoinColumn( name = "sku", nullable = false )
    private SkuEntity skuEntity;

    /**
     * JPA demands a default constructor, which we don't want to invoke manually
     */
    @Deprecated
    protected DiscountEntity(){}

    @PersistenceConstructor
    public DiscountEntity( Double discount, Integer amount, SkuEntity skuEntity ) {

        Objects.requireNonNull(discount);
        Objects.requireNonNull(amount);
        Objects.requireNonNull(skuEntity);

        if(discount > 1.0 || discount < 0){
            throw new IllegalArgumentException( "discount can't be bigger than 100% or negative" );
        }

        this.discount = discount;
        this.amount = amount;
        this.skuEntity = skuEntity;
    }

    public Double getDiscount() {
        return discount;
    }

    public Integer getAmount() {
        return amount;
    }

    public SkuEntity getSkuEntity() {
        return skuEntity;
    }

    public void setSkuEntity( SkuEntity skuEntity ) {
        this.skuEntity = skuEntity;
    }
}
