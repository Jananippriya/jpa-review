package io.pragra.jpareview.service;

import io.pragra.jpareview.dto.GitHubUser;
import io.pragra.jpareview.entity.User;
import io.pragra.jpareview.exceptions.BadLoginNameException;
import io.pragra.jpareview.repo.UserRepo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service

public class UserService {
    private final UserRepo userRepo;
    private final RestTemplate template;

    public UserService(UserRepo userRepo, RestTemplate template) {
        this.userRepo = userRepo;
        this.template = template;
    }

    public User createUser(User user) {
       return this.userRepo.save(user);
    }

    public User updateUser(User user) {
        return this.userRepo.save(user);
    }

    public List<User> getAll() {
        return this.userRepo.findAll();
    }

    public Optional<User> getById(long id) {
        return this.userRepo.findById(id);
    }
    public void deleteById(long id) {
         this.userRepo.deleteById(id);
    }

    public GitHubUser getGitUser(String login) throws BadLoginNameException {
        if(null ==login || login.isEmpty()){
            throw new BadLoginNameException("Login is empty");
        }
        return template.getForObject("https://api.github.com/users/{login}",GitHubUser.class, Collections.singletonMap("login",login));
    }

    public Map<String,String> doPost(User user) throws URISyntaxException {
        HttpEntity<User> request = new HttpEntity<>(user);
        ResponseEntity<Map> exchange = template.exchange(new URI("http://localhost:4000/demo"), HttpMethod.POST,request,Map.class);
        return exchange.getBody();
    }
}
