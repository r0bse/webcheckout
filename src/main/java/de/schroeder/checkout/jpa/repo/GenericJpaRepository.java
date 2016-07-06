package de.schroeder.checkout.jpa.repo;

import de.schroeder.checkout.jpa.domain.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * generic interface to access jpa Repositories
 *
 * @param <T> type of entity
 *
 * @author schroeder
 * @date 06. Jul 2016
 */
@NoRepositoryBean
public interface GenericJpaRepository<T extends BaseEntity<T>> extends JpaRepository<T, Long> {

}
