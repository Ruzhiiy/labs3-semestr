package eight.lab.service;

import java.util.List;

import org.springframework.stereotype.Service;

import eight.lab.dto.UserDto;
import eight.lab.entity.User;

@Service
public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
