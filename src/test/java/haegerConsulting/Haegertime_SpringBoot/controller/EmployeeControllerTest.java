package haegerConsulting.Haegertime_SpringBoot.controller;

import haegerConsulting.Haegertime_SpringBoot.model.User;
import haegerConsulting.Haegertime_SpringBoot.model.builder.UserBuilder;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.Power;
import haegerConsulting.Haegertime_SpringBoot.repository.RequestOfHolidaysRepository;
import haegerConsulting.Haegertime_SpringBoot.repository.UserRepository;
import haegerConsulting.Haegertime_SpringBoot.repository.WorktimeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
//@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    EmployeeController employeeController;



    @Autowired
    private UserRepository userRepository;
//
//    @Mock
//    private WorktimeRepository worktimeRepository;
//
//    @Mock
//    private RequestOfHolidaysRepository requestOfHolidaysRepository;

    @Nested
    class getAll{

        @Test
        @DisplayName("getAll")
        public void getAll_happyPath() throws Exception {

            //given
            var user = new UserBuilder().id(1L).employeeNummer(5).userName("Sebas").lastname("Schwarz").firstname("Sebastien").password("password0").email("sebastien@gmail.com")
                    .numberOfUsedHoliday(0).numberOfRestHoliday(30).numberOfSickDay(0).build();
            var user1 = new UserBuilder().id(2L).employeeNummer(6).userName("bara").lastname("Weiß").firstname("Barbara").password("password1").email("weiß_barbara@gmail.com")
                    .power(Power.Bookkeeper).numberOfUsedHoliday(10).numberOfRestHoliday(20).numberOfSickDay(2).build();
            var user2 = new UserBuilder().id(3L).employeeNummer(7).userName("JansK").lastname("Krüger").firstname("Jans").password("password2").email("jans_kruger@gmail.com")
                    .power(Power.Administrator).numberOfUsedHoliday(15).numberOfRestHoliday(15).numberOfSickDay(0).build();

            List<User> users = new ArrayList<>();
            users.add(user);
            users.add(user1);
            users.add(user2);
//            Iterable<User> users1 = List.of(user, user1, user2);

            //when
            when(userRepository.findAll()).thenReturn(users);
            RequestBuilder request = MockMvcRequestBuilders.get("http://localhost:8082/h2/API/Haegertime/users");
            MvcResult result = mockMvc.perform(request).andReturn();

            //then
            //assertEquals(, result.getResponse().getContentAsString());

            System.out.println(users);


        }


    }

    @Test
    void test(){

        assertTrue(true);
    }

}