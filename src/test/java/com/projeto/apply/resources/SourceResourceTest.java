package com.projeto.apply.resources;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SourceResourceTest {

    @Autowired
    private MockMvc mvc;

    @DisplayName("Retorna o link do projeto no Github")
    @Test
    void shouldReturnDefaultLinkGitHub() throws Exception {
        mvc.perform(get("/source")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("https://github.com/fernandopavan/project-test \n " +
                        "https://github.com/fernandopavan/project-test")));
    }

}