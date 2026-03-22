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
@Table(name = "hired_heroes")
public class HiredHero extends UpdatableEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hero_id", nullable = false)
    private Hero hero;

    @ColumnDefault("1.000")
    @Column(name = "dps_mantissa", nullable = false)
    private Double dpsMantissa;

    @ColumnDefault("1")
    @Column(name = "dps_exponent", nullable = false)
    private Integer dpsExponent;

    @ColumnDefault("1.000")
    @Column(name = "price_mantissa", nullable = false)
    private Double priceMantissa;

    @ColumnDefault("1")
    @Column(name = "price_exponent", nullable = false)
    private Integer priceExponent;

    @Column(name = "level", nullable = false)
    private Integer level;

}
