package com.yektaanil.linklendin.service.link;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.yektaanil.linklendin.config.ModelMapperConfig;
import com.yektaanil.linklendin.dto.link.LinkDTO;
import com.yektaanil.linklendin.entity.Link;
import com.yektaanil.linklendin.repository.LinkRepository;
import com.yektaanil.linklendin.utility.EntityTestUtility;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : Yekta Anil AKSOY
 * @since : 20.10.2021
 **/
@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        classes = ModelMapperConfig.class
)
class LinkServiceTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private LinkRepository linkRepository;

    @InjectMocks
    private LinkService linkService = new LinkServiceImpl(modelMapper, linkRepository);

    @Test
    void save_success() {
        Link link = EntityTestUtility.getLink();
        LinkDTO linkDTO = EntityTestUtility.getLinkDTO();
        when(modelMapper.map(any(LinkDTO.class), any())).thenReturn(
                link);
        when(modelMapper.map(any(Link.class), any())).thenReturn(
                linkDTO);
        when(linkRepository.save(any(Link.class))).thenReturn(link);

        LinkDTO savedDTO = linkService.save(linkDTO);

        assertEquals(linkDTO.getId(), savedDTO.getId());
        assertEquals(linkDTO.getUserId(), savedDTO.getUserId());
        assertEquals(linkDTO.getTitle(), savedDTO.getTitle());
        assertEquals(linkDTO.getUrl(), savedDTO.getUrl());
    }

    @Test
    void retrieveAllUserLinks_success() {
        Link link = EntityTestUtility.getLink();
        LinkDTO linkDTO = EntityTestUtility.getLinkDTO();
        Link link2 = new Link();
        link2.setId(2L);
        link2.setUser(link.getUser());
        List<Link> linkList = new ArrayList<>(List.of(link, link2));
        when(modelMapper.map(any(Link.class), any())).thenReturn(
                linkDTO);
        when(linkRepository.findAllByUserId(anyLong())).thenReturn(linkList);

        List<LinkDTO> serviceResp = linkService.retrieveAllUserLinks(1L);

        assertFalse(serviceResp.isEmpty());
    }
}
