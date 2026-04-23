package com.metDaisy.clickerheroes.service;

import com.metDaisy.clickerheroes.dto.request.UserCreateRequest;
import com.metDaisy.clickerheroes.dto.UserDto;
import com.metDaisy.clickerheroes.entity.User;
import com.metDaisy.clickerheroes.mapper.UserMapper;
import com.metDaisy.clickerheroes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  
  public UserDto create(UserCreateRequest request) {
    User user = userMapper.toEntity(request);
    userRepository.save(user);
    return userMapper.toDto(user);
  }

  public UserDto find(Integer id) {
    return userRepository.findById(id).map(userMapper::toDto).orElseThrow();
  }
}
