package vmware.services.gateway.controller;

import com.fasterxml.jackson.core.io.JsonEOFException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vmware.security.JWTUtil;
import vmware.services.gateway.dto.AuthenticationRequest;
import vmware.services.gateway.dto.AuthenticationResponse;
import vmware.services.gateway.model.User;
import vmware.services.gateway.repository.UserRepository;
import vmware.services.gateway.service.jwt.UserDetailsServiceImpl;
import vmware.services.gateway.service.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class AuthenticationController {


    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    private JWTUtil jwtUtil;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserService userService;

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    @PostMapping({"/authenticate"})
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException, JsonEOFException, ServletException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password", e);
        }
        catch (DisabledException disabledException){
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "User Is Not Activated");
            return null;
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        User user = userRepo.findFirstByEmail(authenticationRequest.getUsername());


        final String jwt = jwtUtil.generateToken(String.valueOf(userDetails));

        return new AuthenticationResponse(jwt);

    }



}
