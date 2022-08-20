package io.pragra.jpareview.controller.test;

import io.pragra.jpareview.service.UserService;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class UserControllerTest {

    @BeforeClass
    public void setUp(){
        UserService userServiceMock = mock(UserService.class);

    }

    @Test
    public void testGetGitUser(){

    }
}
