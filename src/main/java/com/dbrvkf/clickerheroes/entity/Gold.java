package com.dbrvkf.clickerheroes.entity;

import com.dbrvkf.clickerheroes.entity.base.MutableEntity;
import com.dbrvkf.clickerheroes.entity.common.ScientificNumber;
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
@Table(name = "gold")
public class Gold extends MutableEntity {

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Embedded
  private ScientificNumber gold;

  @Builder
  public Gold(User user, double goldMantissa, int goldExponent) {
    this.user = user;
    this.gold = new ScientificNumber(goldMantissa, goldExponent);
  }
}
