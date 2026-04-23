package com.metDaisy.clickerheroes.entity;

import com.metDaisy.clickerheroes.entity.base.MutableEntity;
import com.metDaisy.clickerheroes.entity.common.ScientificNumber;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "monsters")
public class Monster extends MutableEntity {

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Embedded
  private ScientificNumber hp;

  @Builder
  public Monster(User user, double hpMantissa, int hpExponent) {
    this.user = user;
    this.hp = new ScientificNumber(hpMantissa, hpExponent);
  }
}
