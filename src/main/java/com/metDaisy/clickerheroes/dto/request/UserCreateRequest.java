package com.metDaisy.clickerheroes.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record UserCreateRequest(@NotEmpty String name, @NotEmpty String password) {

}
