package com.metDaisy.clickerheroes.entity;

import com.metDaisy.clickerheroes.entity.base.MutableEntity;
import com.metDaisy.clickerheroes.entity.common.ScientificNumber;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
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
