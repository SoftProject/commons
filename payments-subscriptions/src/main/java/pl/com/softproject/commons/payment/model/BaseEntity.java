package pl.com.softproject.commons.payment.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Class BaseEntity
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
@MappedSuperclass
@SuppressWarnings({"PersistenceUnitPresent", "LocalCanBeFinal"})
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public BaseEntity() {

    }

    public BaseEntity(Long id) {

        this.id = id;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BaseEntity that = (BaseEntity) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);
    }

    @Override
    public int hashCode() {

        return id != null ? id.hashCode() : 0;
    }
}
