package com.dbrvkf.clickerheroes.entity;

import com.dbrvkf.clickerheroes.entity.base.MutableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends MutableEntity {

    @Column(name = "name", nullable = false, length = 15)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "stage", nullable = false)
    private Integer stage;

}
