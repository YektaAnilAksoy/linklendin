package com.yektaanil.linklendin.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.yektaanil.linklendin.dto.link.LinkDTO;
import com.yektaanil.linklendin.service.link.LinkService;
import com.yektaanil.linklendin.utility.EntityTestUtility;
import com.yektaanil.linklendin.utility.TestUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author : Yekta Anil AKSOY
 * @since : 3.11.2021
 **/

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class LinkControllerTest {

    private static final String URI = "/api/v1/link";
    private static final String addURI = URI + "/add";
    private static final String loginURI = URI + "/1/list";

    @MockBean
    private LinkService linkService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void add_success() throws Exception {
        //Setup mocked service
        LinkDTO mockedLinkDTO = EntityTestUtility.getLinkDTO();
        when(linkService.addLink(any(LinkDTO.class))).thenReturn(mockedLinkDTO);

        // Execute the POST request
        mockMvc.perform(post(addURI)
                        .content(TestUtil.asJsonString(mockedLinkDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Validate headers
                //.andExpect(header().string(HttpHeaders.LOCATION, signupURI))
                // Validate the returned fields
                .andExpect(jsonPath("$.id", is(mockedLinkDTO.getId().intValue())))
                .andExpect(jsonPath("$.url", is(mockedLinkDTO.getUrl())))
                .andExpect(jsonPath("$.title", is(mockedLinkDTO.getTitle())))
                .andExpect(jsonPath("$.userId", is(mockedLinkDTO.getUserId().intValue())));
    }

    @Test
    void list_success() throws Exception {
        //Setup mocked service
        LinkDTO mockedLinkDTO = EntityTestUtility.getLinkDTO();
        LinkDTO mockedLinkDTO2 = EntityTestUtility.getLinkDTO2();
        List<LinkDTO> linkDTOList = new ArrayList<>(Arrays.asList(mockedLinkDTO, mockedLinkDTO2));
        when(linkService.listUserLinks(any(Long.class))).thenReturn(linkDTOList);

        // Execute the GET request
        mockMvc.perform(get(loginURI))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(linkDTOList.size())));
    }
}
