package pieritz.prince.CRMAPP.web;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pieritz.prince.CRMAPP.dto.RegisterRequest;
import pieritz.prince.CRMAPP.dto.UserResponse;
import pieritz.prince.CRMAPP.services.AuthenticationService;
import pieritz.prince.CRMAPP.services.UserDetailsServiceImplementation;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin(originPatterns = "http://localhost:4200")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserDetailsServiceImplementation userDetailsService;
    private final AuthenticationService authenticationService;

    @GetMapping
    public List<String> getAllUsernames() {
        logger.info("getAllUsernames request received.");
        List<String> usernames = userDetailsService.getAllUsernames();
        logger.info("getAllUsernames response: {}", usernames);
        return usernames;
    }

    @GetMapping("/admin/user/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        logger.info("getUser request received. User ID: {}", id);
        UserResponse response = userDetailsService.getUser(id);
        logger.info("getUser response: {}", response);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/admin/user/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody RegisterRequest updatedUser) {
        logger.info("updateUser request received. User ID: {}, Updated User: {}", id, updatedUser);
        ResponseEntity<UserResponse> response = ResponseEntity.ok(authenticationService.updateUser(updatedUser));
        logger.info("updateUser response: {}", response);
        return response;
    }

    @GetMapping("/admin/users")
    public ResponseEntity<List<UserResponse>> getUsers() {
        logger.info("getUsers request received.");
        List<UserResponse> userResponses = userDetailsService.getUsers();
        logger.info("getUsers response: {}", userResponses);
        return ResponseEntity.ok(userResponses);
    }
}
