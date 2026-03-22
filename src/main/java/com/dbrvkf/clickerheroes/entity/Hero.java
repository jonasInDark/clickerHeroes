package com.dbrvkf.clickerheroes.entity;

import com.dbrvkf.clickerheroes.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Immutable;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "heroes")
@Immutable
public class Hero extends BaseEntity {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "details", nullable = false)
    private String details;

    @ColumnDefault("1.000")
    @Column(name = "base_dps_mantissa", nullable = false)
    private Double baseDpsMantissa;

    @ColumnDefault("1")
    @Column(name = "base_dps_exponent", nullable = false)
    private Integer baseDpsExponent;

    @ColumnDefault("1.000")
    @Column(name = "base_price_mantissa", nullable = false)
    private Double basePriceMantissa;

    @ColumnDefault("1")
    @Column(name = "base_price_exponent", nullable = false)
    private Integer basePriceExponent;

    @OneToMany(mappedBy = "heroes")
    private List<HeroSkill> heroSkills = new ArrayList<>();

}
