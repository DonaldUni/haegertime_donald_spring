package haegerConsulting.Haegertime_SpringBoot.controller;

import haegerConsulting.Haegertime_SpringBoot.exceptions.ElementNotFoundException;
import haegerConsulting.Haegertime_SpringBoot.facade.AdminFacade;
import haegerConsulting.Haegertime_SpringBoot.facade.EmployeeFacade;
import haegerConsulting.Haegertime_SpringBoot.model.User;
import haegerConsulting.Haegertime_SpringBoot.model.builder.UserBuilder;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.Power;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.Status;
import haegerConsulting.Haegertime_SpringBoot.repository.UserRepository;
import haegerConsulting.Haegertime_SpringBoot.services.UserService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
class AdminControllerIntegrationTestWithMockito {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AdminFacade adminFacade;

    @MockBean
    private UserRepository userRepository;

    private final String BASEURL = "http://localhost:8082/API/Haegertime/admins";


    //Post-Mehtoden
    @Test
    void createUser() throws Exception {

        //given
        var user = new UserBuilder().userName("Test").employeeNummer(17).lastname("Test").firstname("Sebastien").password("password0").email("asd@gmail.com")
                .power(Power.Employee).status(Status.actived).numberOfUsedHoliday(0).numberOfRestHoliday(30).numberOfSickDay(0).build();

        ObjectMapper mapper = new ObjectMapper();


//        User user1 = mapper.readValue(json_of_user, User.class);
//        System.out.println(user1);

        //when
        when(userRepository.existsById(user.getId())).thenReturn(false);
        user.setId(10L);
        String json_of_user = mapper.writeValueAsString(user);
        when(userRepository.save(new UserBuilder().id(10L).userName("Test").employeeNummer(17).lastname("Test").firstname("Sebastien").password("password0").email("asd@gmail.com").power(Power.Employee).status(Status.actived).numberOfUsedHoliday(0).numberOfRestHoliday(30).numberOfSickDay(0).build()))
                .thenReturn(user);

        //then

        mockMvc.perform(post(BASEURL + "/createUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json_of_user))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(json_of_user));
    }

    //Delete-Methode
    @Nested
    class deleteUserById{

        @Test
        void deleteUserById_happyPath() throws Exception {

            //given
            Long toDeleteId = 1L;
            String expectedString = "The element with the id = " + toDeleteId + " has been deleted";

            //when
            when(userRepository.existsById(toDeleteId)).thenReturn(true);

            //then
            mockMvc.perform(delete(BASEURL + "/deletes/user/{id}", toDeleteId))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().string(expectedString));
            verify(userRepository, times(1)).deleteById(toDeleteId);

        }

        @Test
        void deleteUserByIdReturnException() throws Exception {

            //given
            Long notExistingId = 1L;
            String expectedErrorMessage = "This element has been not found.";

            //when
            when(userRepository.existsById(notExistingId)).thenReturn(false);

            //then
            assertThrows(NestedServletException.class, ()->mockMvc.perform(delete(BASEURL + "/deletes/user/1")));

        }

    }

    @Nested
    class deleteUserByUsername{

        @Test
        void deleteUserByUsername_happyPath() throws Exception {

            //given
            String toDeleteUsername = "Username to delete";
            String expectedString = "The element with the id = " + toDeleteUsername + " has been deleted";

            //when
            when(userRepository.existsByUserName(toDeleteUsername)).thenReturn(true);

            //then
            mockMvc.perform(delete(BASEURL + "/deletes/user")
                    .param("username", toDeleteUsername))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().string(expectedString));
            verify(userRepository, times(1)).deleteByUserName(toDeleteUsername);

        }

        @Test
        void deleteUserByUsernameReturnException() throws Exception {

            //given
            String notExistingUsername = "Not existing username";

            //when
            when(userRepository.existsByUserName(notExistingUsername)).thenReturn(false);

            //then
            assertThrows(NestedServletException.class, ()->mockMvc.perform(delete(BASEURL + "/deletes/user")
                    .param("username", notExistingUsername)));
        }

    }


    @Test
    void deleteAllUser() throws Exception {

        //given
        String expectedString = "All user has been deleted.";

        //when


        //then
        mockMvc.perform(delete(BASEURL + "/deletes"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(expectedString));
        verify(userRepository, times(1)).deleteAll();

    }


}