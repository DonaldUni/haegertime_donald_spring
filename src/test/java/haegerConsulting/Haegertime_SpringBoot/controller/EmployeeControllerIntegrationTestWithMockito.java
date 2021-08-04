package haegerConsulting.Haegertime_SpringBoot.controller;

import haegerConsulting.Haegertime_SpringBoot.facade.EmployeeFacade;
import haegerConsulting.Haegertime_SpringBoot.model.Project;
import haegerConsulting.Haegertime_SpringBoot.model.RequestOfHoliday;
import haegerConsulting.Haegertime_SpringBoot.model.User;
import haegerConsulting.Haegertime_SpringBoot.model.Worktime;
import haegerConsulting.Haegertime_SpringBoot.model.builder.UserBuilder;
import haegerConsulting.Haegertime_SpringBoot.model.builder.WorktimeBuilder;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.Power;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.Status;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.WorktimeType;
import haegerConsulting.Haegertime_SpringBoot.repository.RequestOfHolidaysRepository;
import haegerConsulting.Haegertime_SpringBoot.repository.UserRepository;
import haegerConsulting.Haegertime_SpringBoot.repository.WorktimeRepository;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.NestedServletException;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
class EmployeeControllerIntegrationTestWithMockito {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeFacade employeeFacade;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private WorktimeRepository worktimeRepository;

    @MockBean
    RequestOfHolidaysRepository requestOfHolidaysRepository;


    private final String BASEURL = "http://localhost:8082/API/Haegertime/users";

    //Get-Methoden
    @Nested
    class getUser{

        @Test
        public void getUser_happyPath() throws Exception {

            //given
            var user = new UserBuilder().id(1L).employeeNummer(5).userName("Sebas").lastname("Schwarz").firstname("Sebastien").password("password0").email("sebastien@gmail.com")
                    .numberOfUsedHoliday(0).numberOfRestHoliday(30).numberOfSickDay(0).build();
            var optionalUser = Optional.of(user);

            //when
            when(userRepository.findById(1L)).thenReturn(optionalUser);

            //then
            ObjectMapper mapper = new ObjectMapper();
            String expectedJson = mapper.writeValueAsString(user);
            //System.out.println(json);
            mockMvc.perform(get(BASEURL + "/get/1"))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().string(expectedJson));

        }

        @Test
        public void getUser_ReturnExceptionFromClient() throws Exception {

            //given
            var user = new UserBuilder().id(1L).employeeNummer(5).userName("Sebas").lastname("Schwarz").firstname("Sebastien").password("password0").email("sebastien@gmail.com")
                    .numberOfUsedHoliday(0).numberOfRestHoliday(30).numberOfSickDay(0).build();
            var optionalUser = Optional.of(user);

            //when
            when(userRepository.findById(1L)).thenReturn(optionalUser);

            //then
            mockMvc.perform(get(BASEURL + "/get/abc"))
                    .andExpect(status().is4xxClientError());
        }

        @Test
        public void getUser_ReturnExceptionFromServer() throws Exception {

            //given
            var user = new UserBuilder().id(1L).employeeNummer(5).userName("Sebas").lastname("Schwarz").firstname("Sebastien").password("password0").email("sebastien@gmail.com")
                    .numberOfUsedHoliday(0).numberOfRestHoliday(30).numberOfSickDay(0).build();
            var optionalUser = Optional.of(user);

            //when
            when(userRepository.findById(1L)).thenReturn(optionalUser);

            //then
            assertThrows(NestedServletException.class, ()-> mockMvc.perform(get(BASEURL + "/get/15")));
            //mockMvc.perform(get(BASEURL + "/get/15")).andExpect(status().is5xxServerError());
        }

    }

    @Nested
    class getAllUser{

        @Test
        public void getAllUser_happyPath() throws Exception {

            //given
            var user = new UserBuilder().id(1L).employeeNummer(5).userName("Sebas").lastname("Schwarz").firstname("Sebastien").password("password0").email("sebastien@gmail.com")
                    .numberOfUsedHoliday(0).numberOfRestHoliday(30).numberOfSickDay(0).build();
            var user1 = new UserBuilder().id(2L).employeeNummer(6).userName("bara").lastname("Weiss").firstname("Barbara").password("password1").email("weiss_barbara@gmail.com")
                    .power(Power.Bookkeeper).numberOfUsedHoliday(10).numberOfRestHoliday(20).numberOfSickDay(2).build();
            var user2 = new UserBuilder().id(3L).employeeNummer(7).userName("JansK").lastname("Kruger").firstname("Jans").password("password2").email("jans_kruger@gmail.com")
                    .power(Power.Administrator).numberOfUsedHoliday(15).numberOfRestHoliday(15).numberOfSickDay(0).build();

            List<User> users = new ArrayList<>();
            users.add(user);
            users.add(user1);
            users.add(user2);

            //when
            when(userRepository.findAll()).thenReturn(users);

            //then
            ObjectMapper mapper = new ObjectMapper();
            String expectedJson = mapper.writeValueAsString(users);
            mockMvc.perform(get(BASEURL))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().string(expectedJson));

        }

        @Test
        public void getAll_ReturnExceptionFromClient() throws Exception {

            //given
            var user = new UserBuilder().id(1L).employeeNummer(5).userName("Sebas").lastname("Schwarz").firstname("Sebastien").password("password0").email("sebastien@gmail.com")
                    .numberOfUsedHoliday(0).numberOfRestHoliday(30).numberOfSickDay(0).build();
            var user1 = new UserBuilder().id(2L).employeeNummer(6).userName("bara").lastname("Weiss").firstname("Barbara").password("password1").email("weiss_barbara@gmail.com")
                    .power(Power.Bookkeeper).numberOfUsedHoliday(10).numberOfRestHoliday(20).numberOfSickDay(2).build();
            var user2 = new UserBuilder().id(3L).employeeNummer(7).userName("JansK").lastname("Kruger").firstname("Jans").password("password2").email("jans_kruger@gmail.com")
                    .power(Power.Administrator).numberOfUsedHoliday(15).numberOfRestHoliday(15).numberOfSickDay(0).build();

            List<User> users = new ArrayList<>();
            users.add(user);
            users.add(user1);
            users.add(user2);

            //when
            when(userRepository.findAll()).thenReturn(users);

            //then
            mockMvc.perform(get(BASEURL + "abc"))
                    .andExpect(status().is4xxClientError());
        }

    }

    @Nested
    class getAllMyWorktime{

        @Test
        public void getAllMyWorktime_happyPath() throws Exception {

            //given
            var user = new UserBuilder().id(1L).employeeNummer(5).userName("Sebas").lastname("Schwarz").firstname("Sebastien").password("password0").email("sebastien@gmail.com")
                    .numberOfUsedHoliday(0).numberOfRestHoliday(30).numberOfSickDay(0).build();
            var project = new Project("Test1", "Beschreibung von Test1.");
            var project1 = new Project("Test2", "Beschreibung von Test2.");
            var worktime1 = new WorktimeBuilder().project(project).user(user).workhour(10).overtime(10).undertime(0).period("24.07.2021-31.07.2021").build();
            var worktime2 = new WorktimeBuilder().project(project1).user(user).workhour(20).overtime(20).undertime(10).period("24.07.2021-31.07.2021").build();

            ArrayList<Worktime> worktimes = new ArrayList<>();
            worktimes.add(worktime1);
            worktimes.add(worktime2);
            Iterable<Worktime> worktimeIterable = worktimes.stream().toList();


            //when
            when(worktimeRepository.findAllByUserId(1L)).thenReturn(worktimeIterable);

            //then
            ObjectMapper mapper = new ObjectMapper();
            String expectedJson = mapper.writeValueAsString(worktimeIterable);
            mockMvc.perform(get(BASEURL + "/getAllMyWorktime/1"))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().string(expectedJson));

        }

        @Test
        public void getAll_ReturnExceptionFromClient() throws Exception {

            //given
            Iterable<Worktime> worktimes = new ArrayList<>();
            //when
            when(worktimeRepository.findAllByUserId(1L)).thenReturn(worktimes);

            //then
            mockMvc.perform(get(BASEURL + "/getAllMyWorktime/" + "abc"))
                    .andExpect(status().is4xxClientError());
        }

    }

    //Put-Methoden
    @Test
    public void finaliseAllMyUnfinalWorktime() throws Exception {

        //given
        var user = new UserBuilder().id(1L).employeeNummer(5).userName("Sebas").lastname("Schwarz").firstname("Sebastien").password("password0").email("sebastien@gmail.com")
                .numberOfUsedHoliday(0).numberOfRestHoliday(30).numberOfSickDay(0).build();
        var project = new Project("Test1", "Beschreibung von Test1.");
        var project1 = new Project("Test2", "Beschreibung von Test2.");
        var worktime1 = new WorktimeBuilder().project(project).user(user).workhour(10).overtime(10).type(WorktimeType.Unfinal).undertime(0).period("24.07.2021-31.07.2021").build();
        var worktime2 = new WorktimeBuilder().project(project1).user(user).workhour(20).overtime(20).type(WorktimeType.Unfinal).undertime(10).period("24.07.2021-31.07.2021").build();

        ArrayList<Worktime> worktimes = new ArrayList<>();
        worktimes.add(worktime1);
        worktimes.add(worktime2);
        Iterable<Worktime> worktimeIterable = worktimes.stream().toList();


        //when
        when(worktimeRepository.findAllByUserIdAndWorktimeType(1L, WorktimeType.Unfinal)).thenReturn(worktimeIterable);
        worktimeIterable.forEach(worktime -> { worktime.setWorktimeType(WorktimeType.Final); });
        when(worktimeRepository.findAllByUserIdAndWorktimeType(1L, WorktimeType.Final)).thenReturn(worktimeIterable);

        //then
        ObjectMapper mapper = new ObjectMapper();
        String expectedJson = mapper.writeValueAsString(worktimeIterable);

        mockMvc.perform(put(BASEURL + "/finaliseAllMyUnfinalWorktime/{userId}", 1L))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(expectedJson));
    }

    @Test
    public void updateMyAccountData() throws Exception {

        //given
        var savedUser = new UserBuilder().id(1L).employeeNummer(5).userName("Sebas").lastname("Schwarz").firstname("Sebastien")
                .password("password0").email("sebastien@gmail.com")
                .numberOfUsedHoliday(0).numberOfRestHoliday(30).numberOfSickDay(0).build();

        var optionalUser = Optional.of(savedUser);

        var updatedUser = savedUser;
        updatedUser.setPassword("passwordUpdate");

        //when
        when(userRepository.findById(1L)).thenReturn(optionalUser);
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);

        //then
        ObjectMapper mapper = new ObjectMapper();
        String expectedString = "My Account { \n" +
                "Id = " + updatedUser.getId() +"\n"+
                "password = " + updatedUser.getPassword() +"\n";

        mockMvc.perform(put(BASEURL + "/updateMyAccountData")   // oder mit "?userId=1&oldPassword=password0&newPassword=passwordUpdated"
                .param("userId", "1")
                .param("oldPassword","password0" )
                .param("newPassword", "passwordUpdate"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(expectedString));

    }

    @Test
    public void updateUnfinalWorktime() throws Exception {

        //given
        var user = new UserBuilder().id(1L).employeeNummer(5).userName("Sebas").lastname("Schwarz").firstname("Sebastien").password("password0").email("sebastien@gmail.com")
                .numberOfUsedHoliday(0).numberOfRestHoliday(30).numberOfSickDay(0).build();
        var project = new Project("Test1", "Beschreibung von Test1.");

        var worktime = new WorktimeBuilder().id(1L).project(project).user(user).workhour(10).overtime(10).type(WorktimeType.Unfinal).undertime(0).period("24.07.2021-31.07.2021").build();
        var optionalWorktime = Optional.of(worktime);

        //when
        when(worktimeRepository.findById(worktime.getId())).thenReturn(optionalWorktime);
        Worktime updatedWorktime = worktime;
        updatedWorktime.setWorkhour(100);
        when(worktimeRepository.save(any())).thenReturn(updatedWorktime);

        //then
        ObjectMapper mapper = new ObjectMapper();
        String json_of_updatedWorktime = mapper.writeValueAsString(updatedWorktime);
        mockMvc.perform(put(BASEURL + "/updateUnfinalWorktime")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_of_updatedWorktime))
                .andExpect(status().is2xxSuccessful())
                //.andDo(print());
                .andExpect(content().string(json_of_updatedWorktime));
    }



    //Post-Methoden
    @Test
    void createRequestOfHoliday() throws Exception {

        //given
        var mapper = new ObjectMapper();

        var user = new UserBuilder().id(10L).userName("Test").employeeNummer(17).lastname("Test").firstname("Sebastien").password("password0").email("asd@gmail.com")
                .power(Power.Employee).status(Status.actived).numberOfUsedHoliday(0).numberOfRestHoliday(30).numberOfSickDay(0).build();

        RequestOfHoliday requestOfHoliday = new RequestOfHoliday(user, 10, Instant.now().plus(Duration.ofDays(10)), Instant.now().plus(Duration.ofDays(20)));


        var json_of_request = mapper.writeValueAsString(requestOfHoliday);

//        String json_of_request1 = "{\n" +
//                "        \n" +
//                "        \"user\": {\n" +
//                "            \"id\": 1,\n" +
//                "            \"lastname\": \"Schwarz\",\n" +
//                "            \"firstname\": \"Sebastien\",\n" +
//                "            \"employeeNummer\": 5,\n" +
//                "            \"userName\": \"Sebas\",\n" +
//                "            \"password\": \"password0\",\n" +
//                "            \"email\": \"sebastien@gmail.com\",\n" +
//                "            \"power\": \"Employee\",\n" +
//                "            \"status\": \"actived\",\n" +
//                "            \"numberOfUsedHoliday\": 0.0,\n" +
//                "            \"numberOfRestHoliday\": 30.0,\n" +
//                "            \"numberOfSickDay\": 0.0,\n" +
//                "            \"numberOfHoliday\": 30.0\n" +
//                "        },\n" +
//                "        \"numberOfRequestedDay\": 10,\n" +
//                "        \"startDate\": \"2021-08-14T12:59:10.979732Z\",\n" +
//                "        \"finishDate\": \"2021-08-24T12:59:10.979732Z\",\n" +
//                "        \"status\": \"Pending\",\n" +
//                "        \"time\": \"2021-08-04T12:59:10.979732Z\"\n" +
//                "    }";
//        RequestOfHoliday requestOfHolidayFromJson = mapper.readValue(json_of_request1, RequestOfHoliday.class);


        //when
        //when(requestOfHolidaysRepository.existsById(requestOfHoliday.getId())).thenReturn(false);
        requestOfHoliday.setId(1L);
        when(requestOfHolidaysRepository.save(any())).thenReturn(requestOfHoliday);

        //then
        mockMvc.perform(post("http://localhost:8082/API/Haegertime/users/RequestOFHoliday")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json_of_request))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(mapper.writeValueAsString(requestOfHoliday)));


    }

    @Test void createUnfinalWorktime() throws Exception{

        //given
        var user = new UserBuilder().id(1L).employeeNummer(5).userName("Sebas").lastname("Schwarz").firstname("Sebastien").password("password0").email("sebastien@gmail.com")
                .numberOfUsedHoliday(0).numberOfRestHoliday(30).numberOfSickDay(0).build();
        var project = new Project("Test1", "Beschreibung von Test1.");
        Worktime worktime = new WorktimeBuilder().project(project).user(user).workhour(10).overtime(10).type(WorktimeType.Unfinal).undertime(0).period("24.07.2021-31.07.2021").build();
        Worktime worktime1 = worktime;
        worktime1.setId(1L);
        ObjectMapper mapper = new ObjectMapper();
        String json_of_worktime = mapper.writeValueAsString(worktime);
        String json_of_worktime1 = mapper.writeValueAsString(worktime1);

        //when
        when(worktimeRepository.save(any())).thenReturn(worktime1);

        //then
        mockMvc.perform(post(BASEURL + "/UnfinalWorktime")
                        . contentType(MediaType.APPLICATION_JSON)
                        .content(json_of_worktime))
                .andExpect(content().string(json_of_worktime1));
        verify(worktimeRepository, times(1)).save(any());
    }






}