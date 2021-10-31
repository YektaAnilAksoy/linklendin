package com.yektaanil.linklendin.controller;

import com.yektaanil.linklendin.dto.link.LinkDTO;
import com.yektaanil.linklendin.service.link.LinkService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Yekta Anil AKSOY
 * @since : 21.10.2021
 **/
@RestController
@RequestMapping("/api/v1/link")
public class LinkController {

    private LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<LinkDTO> addLink(
            @RequestBody LinkDTO linkDTO) {
        return new ResponseEntity<>(linkService.addLink(linkDTO),
                HttpStatus.OK);
    }

    @GetMapping(value = "{userId}/list")
    public ResponseEntity<List<LinkDTO>> listUserLinks(
            @PathVariable Long userId) {
        return new ResponseEntity<>(linkService.listUserLinks(userId),
                HttpStatus.OK);
    }
}
