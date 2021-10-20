package com.yektaanil.linklendin.service.link;

import com.yektaanil.linklendin.dto.link.LinkDTO;
import com.yektaanil.linklendin.entity.Link;
import com.yektaanil.linklendin.repository.LinkRepository;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Yekta Anil AKSOY
 * @since : 15.10.2021
 **/
@Service
public class LinkServiceImpl implements LinkService {

    private ModelMapper modelMapper;
    private LinkRepository linkRepository;

    @Autowired
    public LinkServiceImpl(ModelMapper modelMapper, LinkRepository linkRepository) {
        this.modelMapper = modelMapper;
        this.linkRepository = linkRepository;
    }

    @Override
    public LinkDTO save(LinkDTO linkDTO) {
        return modelMapper.map(linkRepository.save(modelMapper.map(linkDTO, Link.class)),
                LinkDTO.class);
    }

    @Override
    public List<LinkDTO> retrieveAllUserLinks(Long userId) {
        List<LinkDTO> result = new ArrayList<>();
        linkRepository.findAllByUserId(userId)
                .forEach(x -> result.add(modelMapper.map(x, LinkDTO.class)));
        return result;
    }
}
