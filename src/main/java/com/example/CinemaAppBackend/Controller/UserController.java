package com.example.CinemaAppBackend.Controller;

import com.example.CinemaAppBackend.Dto.LoginDto;
import com.example.CinemaAppBackend.Dto.MovieDTO;
import com.example.CinemaAppBackend.Dto.SignUpDto;
import com.example.CinemaAppBackend.Entity.User;
import com.example.CinemaAppBackend.Repo.UserRepo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


//annotations
@RestController //for restfull api
@CrossOrigin //for connect with frontend
@RequestMapping("/api/user") //to add restfull api request

public class UserController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignUpDto signUpDto) {
        // add check for username exists in a DB
        if (userRepo.existsByUsername(signUpDto.getUsername())) {
            //return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body("Username is already taken!");
        }

        // add check for email exists in DB
        if (userRepo.existsByEmail(signUpDto.getEmail())) {
            //return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body("Email is already taken!");
        }

        if(signUpDto.getFirstName()==null || signUpDto.getLastName()==null ||
        signUpDto.getEmail()==null || signUpDto.getUsername()==null || signUpDto.getPassword()==null){
            return ResponseEntity.badRequest().body("Please fill all the required fields");
        }

    //create user object
        User user = new User();
        user.setFirstName(signUpDto.getFirstName());
        user.setLastName(signUpDto.getLastName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));



        userRepo.save(user);

        //return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/signin")
    public ResponseEntity<Map<String, Object>> authenticateUser(@RequestBody LoginDto loginDto) {
//        User temp = new User();
//        temp = userRepo.findByUsername(loginDto.getUsername());
        final User temp = userRepo.findByUsername(loginDto.getUsername());


        if(temp !=null){
            String pw = loginDto.getPassword();
            String encodedPw = temp.getPassword();
            Boolean isPwRight = passwordEncoder.matches(pw,encodedPw);
            if(isPwRight){
                Optional<User> user = userRepo.findByUsernameOrPassword(loginDto.getUsername(),encodedPw);
                if(user.isPresent()){
                    //return ResponseEntity.ok(Map.of("message", "Login Success", "user", temp));
                    return ResponseEntity.ok(
                            new HashMap<String, Object>() {{
                                put("message", "Login Success");
                                put("user", temp);
                            }}
                    );
                }else{
                    //return ResponseEntity.ok(Map.of("message", "Login Failed"));
                    return ResponseEntity.ok(
                            new HashMap<String, Object>() {{
                                put("message", "Login Failed");
                                //put("user", temp);
                            }}
                    );
                }
            }else{
                //return ResponseEntity.ok(Map.of("message", "Password Not Match"));
                return ResponseEntity.ok(
                        new HashMap<String, Object>() {{
                            put("message", "Password Not Match");
                            //put("user", temp);
                        }}
                );
            }
        }else{
            //return ResponseEntity.ok(Map.of("message", "Username Not exits"));
            return ResponseEntity.ok(
                    new HashMap<String, Object>() {{
                        put("message", "Username Not exits");
                        //put("user", temp);
                    }}
            );
        }





//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                loginDto.getUsernameOrEmail(), loginDto.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return new ResponseEntity<String>("User signed-in successfully!.",HttpStatus.OK);
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
//            );
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            // Generate a token or session management logic
//            String token = generateAuthTokenForUser(loginDto.getUsername());
//
//            return ResponseEntity.ok("User signed-in successfully! Token: " + token);
//        } catch (AuthenticationException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed.");
//        }

    }

    @PutMapping("/{id}") // update user profile
    public ResponseEntity<Map<String, Object>>  updateProfile(@PathVariable("id") Integer id,@RequestBody SignUpDto singUpDto){
        User user = new User();
        try {

            user.setFirstName(singUpDto.getFirstName());
            user.setLastName(singUpDto.getLastName());
            user.setUsername(singUpDto.getUsername());
            user.setEmail(singUpDto.getEmail());
            user.setPassword(passwordEncoder.encode(singUpDto.getPassword()));

            user.setUserId(id);

        }catch (Exception e){
            System.out.println(e);
        }

        userRepo.save(user);
        //return new ResponseEntity<String>("User is updated successfully!.", HttpStatus.OK);
        //return ResponseEntity.ok(Map.of("message", "User is updated successfully!.", "user", user));

        return ResponseEntity.ok(
                new HashMap<String, Object>() {{
                    put("message", "User is updated successfully!.");
                    put("user", user);
                }}
        );

    }

    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

//    @PostMapping("/logout")
//    public void performLogout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
//        // .. perform logout
//        this.logoutHandler.logout(request, response, authentication);
//        //return "redirect:/home";
//        SecurityContextHolder.getContext().setAuthentication(null);
//    }

    @GetMapping("/home")
    public String homePage() {
        return "Welcome";
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        // Invalidate session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("Logged out successfully");
    }


    @GetMapping("/{id}")//get user profile data
    public ResponseEntity<User> getProfile(@PathVariable("id") Integer id) {
        // Retrieve the user profile data from the database using the provided ID
        Optional<User> userOptional = userRepo.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
