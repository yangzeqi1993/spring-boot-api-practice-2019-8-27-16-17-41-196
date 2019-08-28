package com.tw.apistackbase.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.Employee;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper ObjectMapper;

    @Test
    public void should_get_employees_list_when_get_url_employees() throws Exception {

        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/employees/")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$[0].name", CoreMatchers.is("小明"))
                ).andExpect(
                        MockMvcResultMatchers.jsonPath("$[1].id", CoreMatchers.is(2))
                ).andExpect(
                        MockMvcResultMatchers.jsonPath("$[5].salary", CoreMatchers.is(7000))
                );

    }

    @Test
    public void should_get_one_employee_name_when_get_url_companies_id_is_1() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/employees/1")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is("小明"))
                );
    }

    @Test
    public void should_create_one_employee_name_when_post_url_companies() throws Exception {
        Employee employee =new Employee();
        String postString = ObjectMapper.writeValueAsString(employee);
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/employees/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postString)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$[6].name", CoreMatchers.is("yang"))
                );
    }

    @Test
    public void should_update_one_employee_name_when_put_url_companies() throws Exception {
        Employee employee =new Employee();
        String postString = ObjectMapper.writeValueAsString(employee);
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postString)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is("yang"))
                );
    }




}
