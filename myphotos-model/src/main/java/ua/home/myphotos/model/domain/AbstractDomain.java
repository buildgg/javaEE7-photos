package ua.home.myphotos.model.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by vov on 13.07.2017.
 */
@MappedSuperclass
public class AbstractDomain implements Serializable {

    private Timestamp created;

    @Basic
    @Column(name = "created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Past
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
       throw new UnsupportedOperationException();
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
