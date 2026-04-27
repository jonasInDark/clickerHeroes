package com.metDaisy.clickerheroes.service;

import com.metDaisy.clickerheroes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ClickerHeroes {

  private final UserRepository userRepository;

}
