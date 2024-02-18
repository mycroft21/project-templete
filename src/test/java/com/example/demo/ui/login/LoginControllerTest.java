package com.example.demo.ui.login;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.application.login.dto.LoginDto.Check;
import com.example.demo.application.login.dto.LoginDto.Join;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private WebApplicationContext wac;

	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac)
			.addFilters(new CharacterEncodingFilter("UTF-8", true))
			.build();
	}

	@Test
	@DisplayName("유저 가입 성공 Test")
	void userJoinSuccess() throws Exception {
		Join joinDto = new Join("유저", "test@email.com", "12345");

		mockMvc.perform(post("/api/v1/ably/auth/join")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(joinDto)))
			.andExpect(status().isOk());
	}

	@Test
	@DisplayName("유저 가입 실패 Test")
	void userJoinFail() throws Exception {
		Join join = new Join("유저", "test@email.com", "12345");

		mockMvc.perform(post("/api/v1/ably/auth/join")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(join)))
			.andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("유저 로그인 Test")
	void userLogin() throws Exception {
		Check check = new Check();
		check.setUserEmail("test@email.com");
		check.setUserPassword("12345");

		mockMvc.perform(post("/api/v1/ably/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(check)))
			.andExpect(status().isOk());
	}
}
