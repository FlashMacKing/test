package com.akampany.api.Security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Token {

  

  public String token;

  public boolean revoked;

  public boolean expired;

 
}
