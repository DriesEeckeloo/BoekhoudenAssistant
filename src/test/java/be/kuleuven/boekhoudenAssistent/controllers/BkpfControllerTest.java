package be.kuleuven.boekhoudenAssistent.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTableWhere;
;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BkpfControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    private final MockMvc mockMvc;
    private static final String jsonData ="{\"GJAHR\": \"\",\"BUKRS\": \"\",\"BELNR1\": \"\",\"BELNR2\": \"\"}";


    public BkpfControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void getAllBkpf() throws Exception {
        mockMvc.perform(get("/bkpf"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.length()").value(countRowsInTable("BKPF")));
    }

    @Test
    void getAllBkpfWhere() throws Exception {
        mockMvc.perform(post("/bkpf/filter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonData))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.length()").value(countRowsInTable("BKPF")));
    }

    @Test
    void getAllBkpfWhereWithDetails() throws Exception {
        mockMvc.perform(post("/bkpf/filter/details")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonData))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.length()").value(countRowsInTable("BKPF")));
    }
}
