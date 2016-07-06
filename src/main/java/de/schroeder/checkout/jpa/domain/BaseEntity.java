package de.schroeder.checkout.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Base Entity for all entities, handles id generation with one sequencetable for all entities
 *
 * @author schroeder
 * @date 22. Jan 2016
 */
@MappedSuperclass
public abstract class BaseEntity<TYPE extends BaseEntity<TYPE>> implements Serializable {


    private static final long serialVersionUID = -1L;

    @Id
    @Column(name="id", updatable=false)
    @GeneratedValue//(strategy = GenerationType.SEQUENCE)
    protected Integer id;

    /**
     * generalised creation of id definition
     * all ids are in one table to avoid some hibernateproblems with sequences
     *
     * @return
     */
    public Integer getId(){
       return id;
    }

    /**
     * don't allow setting the id manually
     *
     * @param id
     */
    protected void setId(Integer id) {
        this.id = id;
    }

}
