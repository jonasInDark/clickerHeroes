package com.metDaisy.clickerheroes.entity;

import com.metDaisy.clickerheroes.entity.base.MutableEntity;
import jakarta.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class User extends MutableEntity {

  @Column(name = "name", nullable = false, length = 15)
  private String name;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "stage", nullable = false)
  private Integer stage = 1;

  @OneToMany(mappedBy = "user", orphanRemoval = true)
  private Set<HiredHero> hiredHeroes = new LinkedHashSet<>();

  @Builder
  public User(String name, String password, Integer stage) {
    this.name = name;
    this.password = password;
    this.stage = stage;
  }
}
