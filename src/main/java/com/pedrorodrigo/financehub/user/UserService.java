package com.pedrorodrigo.financehub.user;

import com.pedrorodrigo.financehub.user.dto.CreateUserRequest;
import com.pedrorodrigo.financehub.user.dto.UpdateUserRequest;
import com.pedrorodrigo.financehub.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException(
                    "User with email " + request.email() + " already exists.");
        }

        String passwordHash = passwordEncoder.encode(request.password());

        User user = new User(
                request.name(),
                request.email(),
                passwordHash
        );

        User savedUser = userRepository.save(user);
        return toUserResponse(savedUser);
    }

    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "User with id " + id + " not found."));
        return toUserResponse(user);
    }

    @Transactional(readOnly = true)
    public Page<UserResponse> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(this::toUserResponse);
    }

    @Transactional
    public UserResponse updateUser(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "User with id " + id + " not found."));

        if (userRepository.existsByEmailAndIdNot(request.email(), id)) {
            throw new IllegalArgumentException(
                    "User with email " + request.email() + " already exists.");
        }

        user.setName(request.name());
        user.setEmail(request.email());

        User updatedUser = userRepository.save(user);

        return toUserResponse(updatedUser);
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException(
                    "User with id " + id + " not found.");
        }

        userRepository.deleteById(id);
    }

    @Transactional
    public void deactivateUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "User with id " + id + " not found."
                ));

        user.setActive(false);
    }

    private UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
