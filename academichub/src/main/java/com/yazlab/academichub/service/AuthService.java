package com.yazlab.academichub.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yazlab.academichub.config.JwtProvider;
import com.yazlab.academichub.entities.Department;
import com.yazlab.academichub.entities.User;
import com.yazlab.academichub.entities.UserRole;
import com.yazlab.academichub.exception.AuthException;
import com.yazlab.academichub.repository.DepartmentRepository;
import com.yazlab.academichub.repository.UserRepository;
import com.yazlab.academichub.repository.UserRoleRepository;
import com.yazlab.academichub.request.OtherSignupRequest;
import com.yazlab.academichub.request.SignupRequest;
import com.yazlab.academichub.response.AuthResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;

    private final DepartmentRepository departmentRepository;

    private final UserRoleRepository userRoleRepository;

    public User createCandidate(SignupRequest req) {

        // User user = userRepository.findByEmail(req.getEmail());

        User newUser = new User();

        newUser.setEmail(req.getEmail());

        newUser.setLastname(req.getLastname());

        newUser.setMobileNo(req.getMobileNo());

        newUser.setName(req.getName());

        newUser.setTcNo(passwordEncoder.encode(req.getTcNo()));

        UserRole userRole = userRoleRepository.findUserRoleByUserRole(req.getUserRole());

        newUser.setUserRole(userRole);

        return userRepository.save(newUser);

        // yeni nesneler olusturulabilir buradan -> basvuru vs...

        // List<GrantedAuthority> authorities = new ArrayList<>();

        // authorities.add(new SimpleGrantedAuthority(USER_ROLE.CANDIDATE.toString()));

        // Authentication authentication = new
        // UsernamePasswordAuthenticationToken(req.getEmail(), null, authorities);

        // SecurityContextHolder.getContext().setAuthentication(authentication);

        // return jwtProvider.generateToken(authentication);

    }

    public AuthResponse signIn(SignupRequest req) throws AuthException {

        User user = userRepository.findByEmail(req.getEmail());

        if (user == null) {
            user = createCandidate(req);
            //
        }

        if (!passwordEncoder.matches(req.getTcNo(), user.getTcNo())) {
            throw new AuthException("Invalid Tc No!");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getUserRole().toString()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), null, authorities);

        String token = jwtProvider.generateToken(authentication);

        String message = "login successfully!";

        // String roleName = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();

        AuthResponse authResponse = new AuthResponse();

        authResponse.setJwt(token);

        authResponse.setMessage(message);

        UserRole userRole = userRoleRepository.findUserRoleByUserRole(req.getUserRole());

        authResponse.setRole(userRole);

        return authResponse;

    }

    public String createUser(OtherSignupRequest req, UserRole role) {

        User user = userRepository.findByEmail(req.getEmail());

        if (user == null) {
            user = new User();
            user.setEmail(req.getEmail());
            user.setLastname(req.getLastname());
            user.setMobileNo(req.getMobileNo());
            user.setName(req.getName());
            user.setTcNo(passwordEncoder.encode(req.getTcNo()));
            user.setPassword(passwordEncoder.encode(req.getPassword()));
            UserRole userRole = userRoleRepository.findUserRoleByUserRole(req.getRole());
            user.setUserRole(userRole);

            if (req.getDepartmentName() != null) {
                Department department = departmentRepository.findByDepartmentName(req.getDepartmentName());
                user.setDepartment(department);
                // System.out.println("************************************************************************");
                // System.out.println(req.getDepartmentName());
                // System.out.println("************************************************************************");

                // System.out.println(department.getDepartmentName());
                // System.out.println(department.getDepartmentId());
                // System.out.println(department.getFaculty().getFacultyName());

            }

            user = userRepository.save(user);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(role.toString()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(req.getEmail(), null, authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtProvider.generateToken(authentication);

        // yeni nesneler olusturulabilir buradan -> basvuru vs...

    }

    public AuthResponse userSignIn(OtherSignupRequest request) throws AuthException {

        User user = userRepository.findByEmail(request.getEmail());

        if (user == null) {
            throw new AuthException("User not found with email -> " + request.getEmail());
        }

        if (!passwordEncoder.matches(request.getTcNo(), user.getTcNo())) {
            throw new AuthException("Invalid Tc No!");
        }

        if (user.getPassword() != null && !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AuthException("Invalid password!");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getUserRole().toString()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), null, authorities);

        String token = jwtProvider.generateToken(authentication);

        String message = "login successfully!";

        // String roleName = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();

        AuthResponse authResponse = new AuthResponse();

        authResponse.setJwt(token);

        authResponse.setMessage(message);

        // UserRole userRole = userRoleRepository.findUserRoleByUserRole(request.getRole());


        authResponse.setRole(user.getUserRole());

        return authResponse;
    }

}
