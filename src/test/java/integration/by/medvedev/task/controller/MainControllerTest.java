package integration.by.medvedev.task.controller;

import by.medvedev.task.TaskApplication;
import by.medvedev.task.controller.MainController;
import by.medvedev.task.model.entity.User;
import by.medvedev.task.model.repository.UserRepository;
import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TaskApplication.class)
public class MainControllerTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private MainController mainController;


    @Test
    public void testAll(){
        when(repository.findAll()).thenReturn(ImmutableList.of());
        List<User> userList = mainController.getAllList();
        verify(repository).findAll();
    }


}