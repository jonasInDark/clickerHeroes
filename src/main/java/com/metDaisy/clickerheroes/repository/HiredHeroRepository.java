package com.metDaisy.clickerheroes.repository;

import com.metDaisy.clickerheroes.entity.HiredHero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HiredHeroRepository extends JpaRepository<HiredHero, Integer> {}
