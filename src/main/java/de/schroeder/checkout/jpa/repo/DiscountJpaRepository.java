package de.schroeder.checkout.jpa.repo;

import de.schroeder.checkout.jpa.domain.DiscountEntity;
import de.schroeder.checkout.jpa.domain.SkuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author schroeder
 * @date 06. Jul 2016
 */
@Repository
public interface DiscountJpaRepository extends GenericJpaRepository<DiscountEntity> {

    @Query("SELECT discount from DiscountEntity as discount where sku = :sku and amount <= :amount order by amount desc")
    DiscountEntity getDiscountByAmount( @Param("sku") SkuEntity sku,
                              @Param("amount") Integer amount );
}
