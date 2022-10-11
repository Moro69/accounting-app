package com.accountingg.controller.goapp;

import com.accountingg.entity.User;
import com.accountingg.entity.goapp.Post;
import com.accountingg.entity.goapp.PostType;
import com.accountingg.repository.goapp.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/secured/admin/posts")
@Validated
public class PostController {

    private final PostRepository repository;

    public PostController(PostRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public Post create(@RequestBody Post post,
                       @ApiIgnore @AuthenticationPrincipal User requester) {
        post.setAuthor(requester);

        return repository.save(post);
    }

    @GetMapping
    public Page<Post> find(@RequestParam @NotNull PostType postType,
                           @RequestParam(required = false) Long countryId,
                           @RequestParam(required = false) Long cityId,
                           @RequestParam(required = false) Long tagId,
                           @PageableDefault(size = 20, sort = "creationDate", direction = Sort.Direction.DESC)
                                   Pageable pageable) {

        if (countryId != null) {
            if (tagId != null) {
                return repository.findAllByTypeAndCountryIdAndTagsId(postType, countryId, tagId, pageable);
            }
            return repository.findAllByTypeAndCountryId(postType, countryId, pageable);
        } else if (cityId != null) {
            if (tagId != null) {
                return repository.findAllByTypeAndCityIdAndTagsId(postType, cityId, tagId, pageable);
            }
            return repository.findAllByTypeAndCityId(postType, cityId, pageable);
        }

        if (tagId != null) {
            return repository.findAllByTypeAndTagsId(postType, tagId, pageable);
        }

        return repository.findAllByType(postType, pageable);
    }
}
