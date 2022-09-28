package com.company.cryptoadvisor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@EqualsAndHashCode(exclude = {"id"}, callSuper = false)
@ToString(exclude = {"id"})
@NoArgsConstructor
@Getter
@Setter
@Table(name = "currency",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"time_stamp", "symbol"}))
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Currency implements Serializable  {
    private static final long serialVersionUID = -3512652658598880682L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    @JsonIgnore
    private long id;

    @Column(name ="time_stamp" )
    Timestamp timeStamp;

    String symbol;

    BigDecimal price;

    public Currency(Timestamp timeStamp, String symbol, BigDecimal price) {
        this.timeStamp = timeStamp;
        this.symbol = symbol;
        this.price = price;
    }
}
