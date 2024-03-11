package by.dzmitry.yarashevich.store;

import by.dzmitry.yarashevich.controllers.rest.UserRestController;
import by.dzmitry.yarashevich.dto.UserCreateEditDto;
import by.dzmitry.yarashevich.dto.UserReadDto;
import by.dzmitry.yarashevich.models.Role;
import by.dzmitry.yarashevich.services.UserServiceDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(MockitoJUnitRunner.class)
public class UserRestControllerTest {

    @Mock
    private UserServiceDto userService;

    @InjectMocks
    private UserRestController userController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testGetUser() throws Exception {
        int userId = 1;
        UserReadDto userDto = new UserReadDto(2L,"Jane", "Smith", "jane.smith@example.com", "password", Role.ADMIN);
        given(userService.findById(userId)).willReturn(Optional.of(userDto));

        mockMvc.perform(get("/api/users/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", is(userDto.getFirstname())))
                .andExpect(jsonPath("$.lastname", is(userDto.getLastname())))
                .andExpect(jsonPath("$.username", is(userDto.getUsername())))
                .andExpect(jsonPath("$.password", is(userDto.getPassword())))
                .andReturn();
    }

    @Test
    public void testGetUsers() throws Exception {
        List<UserReadDto> userList = List.of(
                new UserReadDto(1L,"John", "Doe", "john.doe@example.com", "password", Role.USER),
                new UserReadDto(2L,"Jane", "Smith", "jane.smith@example.com", "password", Role.ADMIN)
        );
        given(userService.findAll()).willReturn(userList);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(userList.size())))
                .andExpect(jsonPath("$[0].firstname", is(userList.get(0).getFirstname())))
                .andExpect(jsonPath("$[0].lastname", is(userList.get(0).getLastname())))
                .andExpect(jsonPath("$[0].username", is(userList.get(0).getUsername())))
                .andExpect(jsonPath("$[0].password", is(userList.get(0).getPassword())))
                .andReturn();
    }

    @Test
    public void testCreateUser() throws Exception {
        UserCreateEditDto userDto = new UserCreateEditDto("Jane", "Smith", "jane.smith@example.com", "password", Role.ADMIN);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userDto)))
                .andExpect(status().isOk())
                .andReturn();

        verify(userService).create(userDto);
    }

    @Test
    public void testUpdateUser() throws Exception {
        int userId = 1;
        UserCreateEditDto userDto = new UserCreateEditDto("Jane", "Smith", "jane.smith@example.com", "password", Role.ADMIN);

        mockMvc.perform(put("/api/users/update/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userDto)))
                .andExpect(status().isOk())
                .andReturn();

        verify(userService).update(userId, userDto);
    }

    @Test
    public void testDeleteUser() throws Exception {
        int userId = 1;

        mockMvc.perform(delete("/api/users/delete/{userId}", userId))
                .andExpect(status().isOk())
                .andReturn();

        verify(userService).delete(userId);
    }
    private String asJsonString(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}