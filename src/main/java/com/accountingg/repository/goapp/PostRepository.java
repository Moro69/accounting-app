package com.accountingg.repository.goapp;

import com.accountingg.entity.goapp.Post;
import com.accountingg.entity.goapp.PostType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByType(PostType postType, Pageable pageable);

    Page<Post> findAllByTypeAndTagsId(PostType postType, Long tagId, Pageable pageable);

    Page<Post> findAllByTypeAndCountryId(PostType postType, Long countryId, Pageable pageable);

    Page<Post> findAllByTypeAndCityId(PostType postType, Long cityId, Pageable pageable);

    Page<Post> findAllByTypeAndCountryIdAndTagsId(PostType postType, Long countryId, Long tagId, Pageable pageable);

    Page<Post> findAllByTypeAndCityIdAndTagsId(PostType postType, Long cityId, Long tagId, Pageable pageable);

}
