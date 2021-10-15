package com.yektaanil.linklendin.service.link;

import com.yektaanil.linklendin.dto.link.LinkDTO;
import com.yektaanil.linklendin.entity.Link;
import com.yektaanil.linklendin.repository.LinkRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * @author : Yekta Anil AKSOY
 * @since : 15.10.2021
 **/
@Service
public class LinkServiceImpl implements LinkService {

    private ModelMapper modelMapper;
    private LinkRepository linkRepository;

    public LinkServiceImpl(ModelMapper modelMapper, LinkRepository linkRepository) {
        this.modelMapper = modelMapper;
        this.linkRepository = linkRepository;
    }

    @Override
    public LinkDTO save(LinkDTO linkDTO) {
        return modelMapper.map(linkRepository.save(modelMapper.map(linkDTO, Link.class)),
                LinkDTO.class);
    }
}
