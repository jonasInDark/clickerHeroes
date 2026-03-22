package com.dbrvkf.clickerheroes.entity;

import com.dbrvkf.clickerheroes.entity.base.UpdatableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "monsters")
public class Monster extends UpdatableEntity {

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ColumnDefault("1.000")
    @Column(name = "hp_mantissa", nullable = false)
    private Double hpMantissa;

    @ColumnDefault("1")
    @Column(name = "hp_exponent", nullable = false)
    private Integer hpExponent;

    @ColumnDefault("false")
    @Column(name = "is_boss", nullable = false)
    private Boolean isBoss;

}
