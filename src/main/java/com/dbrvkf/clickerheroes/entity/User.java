package com.dbrvkf.clickerheroes.entity;

import com.dbrvkf.clickerheroes.entity.base.UpdatableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends UpdatableEntity {

    @Column(name = "name", nullable = false, length = 15)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @ColumnDefault("1")
    @Column(name = "stage", nullable = false)
    private Integer stage;

    @OneToOne(mappedBy = "user")
    private Gold gold;

    @OneToMany(mappedBy = "user")
    private Set<HiredHero> hiredHeroes = new LinkedHashSet<>();

    @OneToOne(mappedBy = "user")
    private Monster monster;


}
