package com.jloroz.blogrestapi.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jloroz.blogrestapi.payload.PostDto;
import com.jloroz.blogrestapi.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest {

    @MockBean
    PostService postService;

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper;

    @BeforeEach
    void configuration(){
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testSavePost() throws Exception {

        PostDto postDto = new PostDto.PostDtoBuilder()
                .setId(1L)
                .setTitle("title1")
                .setDescription("description1")
                .setContent("content1")
                .build();

        when(postService.savePost(any())).thenReturn(postDto);

        mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.title").value("title1"))
                .andExpect(jsonPath("$.description").value("description1"))
                .andExpect(jsonPath("$.content").value("content1"))
                .andReturn();

    }
}
