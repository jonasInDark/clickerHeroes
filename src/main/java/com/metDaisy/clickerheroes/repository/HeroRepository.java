package com.metDaisy.clickerheroes.repository;

import com.metDaisy.clickerheroes.entity.Hero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroRepository extends JpaRepository<Hero, Integer> {}
