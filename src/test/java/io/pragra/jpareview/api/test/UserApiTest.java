package io.pragra.jpareview.api.test;

import io.pragra.jpareview.api.UserApi;
import io.pragra.jpareview.dto.GitHubUser;
import io.pragra.jpareview.entity.Review;
import io.pragra.jpareview.entity.User;
import io.pragra.jpareview.exceptions.BadLoginNameException;
import io.pragra.jpareview.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserApiTest {

    private UserApi userApi;
    private UserService userService;
    private Review review;
    private User user;
    private GitHubUser gitHubUser;

    @Before
    public void setValues(){
        userService = mock(UserService.class);
        userApi = new UserApi(userService);

        review = mock(Review.class);
        review.setReviewDate(Date.from(Instant.now()));
        review.setReviewText("Good");
        review.setStar(4);

        user = mock(User.class);
        user.setId(1L);
        user.setFirstName("Janani");
        user.setLastName("Janu");
        user.setReviews(Arrays.asList(review));

        gitHubUser = mock(GitHubUser.class);
        gitHubUser.setId(104527792L);
        gitHubUser.setAvatarUrl("https://avatars.githubusercontent.com/u/104527792?v=4");
        gitHubUser.setCreated_at(Instant.now());
        gitHubUser.setLocation("Canada");
        gitHubUser.setLogin("Jananippriya");
        gitHubUser.setName("JJ");


    }

    @Test
    public void testCreateUser(){
        when(userService.createUser(user)).thenReturn(user);
        assertEquals(userService.createUser(user),user);
    }

    @Test
    public void testGetAllUsers(){
        when((userService.getAll())).thenReturn(Arrays.asList(user));
        assertEquals(userService.getAll(),Arrays.asList(user));
    }

    @Test
    public void testGetUser(){
        when(userService.getById(1L)).thenReturn(Optional.ofNullable(user));
        assertEquals(userService.getById(1L),Optional.of(user));
    }

    @Test
    public void testGetGitHubUser() throws BadLoginNameException {
        when(userService.getGitUser("Jananippriya")).thenReturn(gitHubUser);
        assertEquals(userService.getGitUser("Jananippriya"),gitHubUser);
    }
    @Test(expected = BadLoginNameException.class)
    public void testGetGitHubUserNeg() throws BadLoginNameException {
        when(userService.getGitUser("Janani")).thenThrow(BadLoginNameException.class);
        userService.getGitUser("Janani");
    }
}
