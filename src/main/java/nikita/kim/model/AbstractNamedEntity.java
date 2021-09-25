/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;



@MappedSuperclass
public abstract class AbstractNamedEntity extends AbstractBaseEntity{
    
    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "nick", nullable = false)
    protected String name;
    
    protected AbstractNamedEntity() {
    }

    protected AbstractNamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    @Override
    public String toString() {
        return super.toString() + '(' + name + ')';
    }
    
}
