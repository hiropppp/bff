package com.example.bff.app;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginForm implements Serializable {

  //todo：private static final long serialVersionUID =

  @NotBlank
  private String userId;

  @NotBlank
  private String password;

}
