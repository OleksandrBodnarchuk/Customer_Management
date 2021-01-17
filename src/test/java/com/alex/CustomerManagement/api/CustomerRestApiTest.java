package com.alex.CustomerManagement.api;

import java.util.UUID;

import com.alex.CustomerManagement.domain.CustomerFacade;
import com.alex.CustomerManagement.dto.CustomerDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;



@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {CustomerRestApi.class})
class CustomerRestApiTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerFacade facade;

    @Test
    void testFindAll() throws Exception {
        // given
        given(facade.findAll()).willReturn(asList(
                new CustomerDto(UUID.randomUUID(),
                        CustomerDto.Type.COMPANY,
                        "Test S.A.",
                        "9390202003"),
                new CustomerDto(UUID.randomUUID(),
                        CustomerDto.Type.PERSON,
                        "Jan Nowak",
                        "83939939")
        ));

        // when
        mvc.perform(get("/api/v1/customers"))
                // then
                .andExpect(jsonPath("$.length()", equalTo(2)))
                .andExpect(jsonPath("$.[0].type", equalTo("COMPANY")))
                .andDo(print());
    }
}
