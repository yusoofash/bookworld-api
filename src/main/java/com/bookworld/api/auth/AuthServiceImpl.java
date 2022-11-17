package com.bookworld.api.auth;

import com.bookworld.api.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Override
    public AuthLoginResponseDto login(String email, String password) throws Exception {
        this.authenticate(email, password);

        String token = jwtTokenUtil.createToken(email);
        String refreshToken = jwtTokenUtil.createRefreshToken(email);

        AuthLoginResponseDto authLoginResponseDto = new AuthLoginResponseDto();
        authLoginResponseDto.setAccessToken(token);
        authLoginResponseDto.setRefreshToken(refreshToken);

        return authLoginResponseDto;
    }

    @Override
    public AuthLoginResponseDto refreshToken(String refreshToken) throws Exception {
        String email = null;

        try {
            email = jwtTokenUtil.getEmailFromToken(refreshToken);
        } catch (IllegalArgumentException e) {
            System.out.println("Unable to get JWT Refresh Token");
        } catch (ExpiredJwtException e) {
            System.out.println("JWT Refresh Token has expired");
        } catch (SignatureException e) {
            System.out.println("Invalid JWT Refresh signature");
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT Refresh token");
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT Refresh token is unsupported");
        }

        if (email == null) {
            throw new Exception("Refresh token was expired. Please make a new signin request");
        }

        UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(email);

        Boolean isValidRefreshToken = jwtTokenUtil.validateRefreshToken(refreshToken, userDetails);

        if (!isValidRefreshToken) {
            throw new Exception("Refresh token was expired. Please make a new signin request");
        }

        String token = jwtTokenUtil.createToken(email);

        AuthLoginResponseDto authLoginResponseDto = new AuthLoginResponseDto();
        authLoginResponseDto.setAccessToken(token);

        return authLoginResponseDto;
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
