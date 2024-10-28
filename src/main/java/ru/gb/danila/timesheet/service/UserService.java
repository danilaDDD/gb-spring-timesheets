package ru.gb.danila.timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.danila.timesheet.model.Role;
import ru.gb.danila.timesheet.model.User;
import ru.gb.danila.timesheet.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> findUserByLogin(String login){
        return userRepository.findByLogin(login);
    }
}
