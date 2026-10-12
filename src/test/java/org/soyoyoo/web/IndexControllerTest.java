package org.soyoyoo.web;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.security.config.http.MatcherType.mvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // MockMvc를 사용하기 위해 추가 필수
public class IndexControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private MockMvc mvc; // 💡 mvc 변수 선언 및 주입

    @Test
    @DisplayName("메인페이지 로딩")
    public void 메인페이지_로딩() {
        // whene
        String body = this.testRestTemplate.getForObject("/", String.class);

        // then
        assertThat(body).contains("스프링부트");
    }
    @Test
    public void 비로그인시_user_null_반환() throws Exception {
        // when & then
        mvc.perform(get("/api/v1/user"))
                .andExpect(status().isOk())
                .andExpect(content().string("")); // null 반환 시 빈 응답
    }

}
