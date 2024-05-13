package com.decagon.reward_your_teacher.controllers;

import com.decagon.reward_your_teacher.domain.entities.TeacherEntity;
import com.decagon.reward_your_teacher.infrastructure.configuration.security.AuthUserService;
import com.decagon.reward_your_teacher.usecase.payload.request.StudentRegistrationRequest;
import com.decagon.reward_your_teacher.usecase.payload.request.TeacherRegistrationRequest;
import com.decagon.reward_your_teacher.usecase.payload.response.RegistrationResponse;
import com.decagon.reward_your_teacher.usecase.services.RegisterService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class RegisterControllerTest {
@Autowired
private MockMvc mockMvc;
@MockBean
private AuthUserService authUserService;
@MockBean
private RegisterService registerService;
static TeacherRegistrationRequest teacherRegistrationRequest;

private static MockMultipartFile file;
private static TeacherEntity teacher;
   private static StudentRegistrationRequest studentRegistrationRequest;
   private static RegistrationResponse response;

    @Test
    void registerStudent() throws Exception {
        Mockito.when(registerService.registerStudent(studentRegistrationRequest))
                .thenReturn(new RegistrationResponse<>("Iyke", "k@gmail.com", 1));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/register/student")
                .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "        \"name\" : \"Iyke\",\n" +
                        "        \"email\" : \"k@gmail.com\",\n" +
                        "        \"password\" : \"1234\",\n" +
                        "        \"phoneNumber\":\"12344323\",\n" +
                        "        \"schoolName\" : \"Pearlville School\"\n" +
                        "        }")).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    // TODO: registerTeacher test
}