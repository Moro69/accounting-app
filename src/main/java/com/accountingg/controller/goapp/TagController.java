package com.accountingg.controller.goapp;

import com.accountingg.entity.goapp.Tag;
import com.accountingg.repository.goapp.TagRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/secured/admin/tags")
public class TagController {

    private final TagRepository repository;

    public TagController(TagRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public Tag create(@RequestBody Tag tag) {
        return repository.save(tag);
    }

    @GetMapping
    public List<Tag> findAll() {
        return repository.findAll();
    }
}
