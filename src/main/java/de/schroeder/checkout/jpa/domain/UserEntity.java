package de.schroeder.checkout.jpa.domain;

/**
 * entity to identify a user
 *
 * @author schroeder
 * @date 06. Jul 2016
 */
//@Entity
//@Table( name = "user" )
//public class UserEntity extends BaseEntity<UserEntity> {
//
//
//    @OneToOne( fetch = FetchType.EAGER )
//    @JoinColumn( name = "shopping_basket", nullable = false )
//    private ShoppingBasketEntity shoppingBasketEntity;
//
//    /**
//     * JPA demands a default constructor, which we don't want to invoke manually
//     */
//    @Deprecated
//    protected UserEntity(){}
//
//    @PersistenceConstructor
//    public UserEntity( ShoppingBasketEntity shoppingBasketEntity ){
//        this.shoppingBasketEntity = shoppingBasketEntity;
//    }
//
//    @Override
//    public Long getId() {
//        return id;
//    }
//
//    public ShoppingBasketEntity getShoppingBasketEntity() {
//        return shoppingBasketEntity;
//    }
//}
