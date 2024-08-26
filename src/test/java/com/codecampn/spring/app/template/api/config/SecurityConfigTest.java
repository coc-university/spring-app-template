package com.codecampn.spring.app.template.api.config;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = {
        SecurityConfigTest.TestController.class,
        SecurityConfig.class
})
class SecurityConfigTest {

    @Nested
    class CheckBean {

        @Autowired
        ApplicationContext context;

        @Test
        void findSecurityConfigBeanInContext() {
            assertThat(context.containsBean("filterChain")).isTrue();
            assertThat(context.getBean(SecurityFilterChain.class)).isNotNull();
        }
    }

    @Nested
    class CheckProtection {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @WithMockUser("test")
        void securityConfigAllowsAccessForLoggedInUserToSecuredEndpoint() throws Exception {
            mockMvc
                    .perform(get("/v1/wildcard"))
                    .andExpect(status().isOk());
        }

        @Test
        // no mock user
        void securityConfigBlocksUnknownUserForSecuredEndpoint() throws Exception {
            mockMvc
                    .perform(get("/v1/wildcard"))
                    .andExpect(status().isUnauthorized());
        }
    }

    @RestController
    static class TestController {

        // use TestController to avoid coupling to a real endpoint

        @GetMapping("/v1/wildcard")
        public ResponseEntity<String> businessEndpoint() {
            return ResponseEntity.ok().build();
        }
    }

}