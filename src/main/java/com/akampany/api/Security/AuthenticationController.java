package com.akampany.api.Security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akampany.api.Exceptions.UserAlreadyExistsException;
import com.akampany.api.User.AppUser;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
@CrossOrigin
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	@Autowired
  private AuthenticationService service;
  

	 @PostMapping("/register")
	    public ResponseEntity<RegisterResponse> register(@RequestBody AppUser userRequest) {
		 try {
			 
			 		 	
		       RegisterResponse response = service.register(userRequest);
		        return ResponseEntity.ok(response);
		    } catch (UserAlreadyExistsException e) {
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RegisterResponse(e.getMessage()));
		    } catch (IOException e) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RegisterResponse("Failed to register user."));
		    }
	    }
  

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }


}
