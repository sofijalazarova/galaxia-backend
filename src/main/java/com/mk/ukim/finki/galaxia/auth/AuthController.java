package com.mk.ukim.finki.galaxia.auth;


import com.mk.ukim.finki.galaxia.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {

        //AuthenticationResponse response = this.authenticationService.register(request);
        return ResponseEntity.ok(this.authenticationService.register(request));
    }

    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){

        AuthenticationResponse response = this.authenticationService.authenticate(request);

        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response){

        System.out.println(response.getStatus());
        return ResponseEntity.ok("Ok");
    }

    @GetMapping("current-user")
    public ResponseEntity<User> getCurrentUser(HttpServletRequest request){
        System.out.println(request);

        return ResponseEntity.ok().build();
    }
}
