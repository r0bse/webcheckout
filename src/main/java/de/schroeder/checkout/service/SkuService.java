package de.schroeder.checkout.service;

import de.schroeder.checkout.jpa.domain.SkuEntity;
import de.schroeder.checkout.jpa.repo.SkuJpaRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;

/**
 * @author schroeder
 * @date 06. Jul 2016
 */
@Service
public class SkuService {

    @Inject
    private SkuJpaRepository skuJpaRepository;

    /**
     * create and save a new entity
     *
     * @param productname
     * @param defaultPrice
     * @return
     */
    public SkuEntity createAndSaveSkuEntity( Character productname,
                                             Long defaultPrice ) {

        SkuEntity sku = new SkuEntity( productname, defaultPrice );
        return skuJpaRepository.save( sku );
    }

    public Collection<SkuEntity> findAll() {

        return skuJpaRepository.findAll();
    }

    public SkuEntity getByProductName( char c ) {

        return skuJpaRepository.getByProductName( c );
    }
}
