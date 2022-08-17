package io.pragra.jpareview.api;

import io.pragra.jpareview.dto.GitHubUser;
import io.pragra.jpareview.entity.User;
import io.pragra.jpareview.exceptions.BadLoginNameException;
import io.pragra.jpareview.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserApi {
    private final UserService service;

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        return this.service.createUser(user);
    }

    @PutMapping("/user")
    public User update(@RequestBody User user) {
        return this.service.createUser(user);
    }

    @GetMapping("/user")
    public List<User> getALL() {
        return this.service.getAll();
    }
    @GetMapping("/user/{id}")
    public Optional<User> getOne(@PathVariable long id) {
        return this.service.getById(id);
    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable long id) {
        this.service.deleteById(id);
    }

   @GetMapping("/user/github/{login}")
    public GitHubUser getGitUser(@PathVariable("login") String login) throws BadLoginNameException {
        return this.service.getGitUser(login);
    }

    @PostMapping("/user/github")
    public Map<String, String> getGitUser( @RequestBody User user) throws URISyntaxException {
        return this.service.doPost(user);
    }


}
