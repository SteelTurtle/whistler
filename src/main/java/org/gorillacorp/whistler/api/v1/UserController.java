package org.gorillacorp.whistler.api.v1;

import lombok.AllArgsConstructor;
import org.gorillacorp.whistler.api.dto.UserDto;
import org.gorillacorp.whistler.api.dto.UserMapper;
import org.gorillacorp.whistler.domain.model.User;
import org.gorillacorp.whistler.domain.service.UserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{username}")
    public Mono<UserDto> getUserByUserName(@PathVariable("username") final String username) {
        return userService.findByUsername(username).map(this::convertEntityToDto);
    }

    @PostMapping
    public Mono<UserDto> createNewUser(@RequestBody final UserDto userDto) {
        return userService.saveUser(new User(userDto.getUserName()))
                .flatMap(whistler ->
                        userService.findByUsername(whistler.getUserName()).map(this::convertEntityToDto));
    }

    protected UserDto convertEntityToDto(User user) {
        return UserMapper.instance.userToUserDto(user);
    }

    // unused for this implementation
    protected User convertDtoToEntity(UserDto dto) {
        return UserMapper.instance.userDtoToUser(dto);
    }
}
