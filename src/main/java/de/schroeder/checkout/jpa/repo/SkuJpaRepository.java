package de.schroeder.checkout.jpa.repo;

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
public interface SkuJpaRepository extends GenericJpaRepository<SkuEntity> {

    @Query("select sku from SkuEntity as sku where sku.productName = :productname")
    SkuEntity getByProductName( @Param("productname") Character productname );
}
