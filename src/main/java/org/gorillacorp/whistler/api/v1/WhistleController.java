package org.gorillacorp.whistler.api.v1;

import lombok.AllArgsConstructor;
import org.gorillacorp.whistler.domain.model.User;
import org.gorillacorp.whistler.domain.model.Whistle;
import org.gorillacorp.whistler.domain.service.UserService;
import org.gorillacorp.whistler.domain.service.WhistleService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/whistles")
@AllArgsConstructor
public class WhistleController {
    private final WhistleService whistleService;
    private final UserService userService;

    @PostMapping("/{username}")
    public Mono<Whistle> save(@PathVariable("username") String username,
                              @RequestBody Whistle whistle) {
        Mono<User> user = userService.findByUsername(username);
        return user.flatMap(whistler -> {
            whistle.setAuthor(whistler);
            return whistleService.saveWhistle(whistle);
        });
    }

    @GetMapping("/{username}")
    public Flux<Whistle> getAll(@PathVariable("username") String username) {
        return whistleService.findTaggedWhistles(username);
    }
}
