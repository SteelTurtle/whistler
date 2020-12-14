package org.gorillacorp.whistler.api.v1;

import lombok.AllArgsConstructor;
import org.gorillacorp.whistler.api.dto.UserDto;
import org.gorillacorp.whistler.api.dto.UserMapper;
import org.gorillacorp.whistler.domain.model.User;
import org.gorillacorp.whistler.domain.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{username}")
    public Mono<UserDto> getUserByUserName(@PathVariable("username") String username) {
        return userService.findByUsername(username).map(this::convertEntityToDto);
    }

    protected UserDto convertEntityToDto(User user) {
        return UserMapper.instance.userToUserDto(user);
    }

    protected User convertDtoToEntity(UserDto dto) {
        return UserMapper.instance.userDtoToUser(dto);
    }
}
