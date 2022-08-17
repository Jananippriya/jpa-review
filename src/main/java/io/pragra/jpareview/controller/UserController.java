package io.pragra.jpareview.controller;

import io.pragra.jpareview.dto.GitHubUser;
import io.pragra.jpareview.exceptions.BadLoginNameException;
import io.pragra.jpareview.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }
    @GetMapping("/user/github/{login}")
    public String getGitUser(@PathVariable("login") String login, Model model) throws BadLoginNameException {
         GitHubUser gitHubUser = this.service.getGitUser(login);
         model.addAttribute("gitHubUser",gitHubUser);
         model.addAttribute("title", "Welcome to GITHub " + login);
         return "githubuser";
    }
}
