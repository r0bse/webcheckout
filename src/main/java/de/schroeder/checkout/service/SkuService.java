package de.schroeder.checkout.service;

import de.schroeder.checkout.jpa.domain.SkuEntity;
import de.schroeder.checkout.jpa.repo.SkuJpaRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

        return skuJpaRepository.save( new SkuEntity( productname, defaultPrice ) );
    }

    public Collection<SkuEntity> findAll() {
        List<SkuEntity> list = new ArrayList<SkuEntity>(  );
        list.add( new SkuEntity( 'A', 20L ) );
        return list;//skuJpaRepository.findAll();
    }

    public SkuEntity getByProductName( char c ) {

        return skuJpaRepository.getByProductName( c );
    }
}
