package com.metDaisy.clickerheroes.repository;

import com.metDaisy.clickerheroes.entity.Monster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonsterRepository extends JpaRepository<Monster, Integer> {

}
