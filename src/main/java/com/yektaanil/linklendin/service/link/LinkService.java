package com.yektaanil.linklendin.service.link;

import com.yektaanil.linklendin.dto.link.LinkDTO;
import java.util.List;

/**
 * @author : Yekta Anil AKSOY
 * @since : 15.10.2021
 **/
public interface LinkService {

    LinkDTO save(LinkDTO linkDTO);

    List<LinkDTO> retrieveAllUserLinks(Long userId);
}
