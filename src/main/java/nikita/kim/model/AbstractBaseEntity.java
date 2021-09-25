/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.model;


import javax.persistence.*;
import org.springframework.util.Assert;



@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractBaseEntity {
    
    public static final int START_SEQ = 100000;
    
    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    protected Integer id;
    
    protected AbstractBaseEntity() {
    }

    protected AbstractBaseEntity(Integer id) {
        this.id = id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
    // doesn't work for hibernate lazy proxy
    public int id() {
        Assert.notNull(id, "Entity must has id");
        return id;
    }
    public boolean isNew() {
        return this.id == null;
    }
    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractBaseEntity that = (AbstractBaseEntity) o;
        return id != null && id.equals(that.id);
    }
    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }
    
    
    
}
