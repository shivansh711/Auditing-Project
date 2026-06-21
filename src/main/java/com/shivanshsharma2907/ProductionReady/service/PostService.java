package com.shivanshsharma2907.ProductionReady.service;

import com.shivanshsharma2907.ProductionReady.DTO.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface PostService {

         List<PostDTO> getAllPosts();
         PostDTO createNewPost(PostDTO inputPost);
         Optional<PostDTO> getPostByID(Long ID);
         PostDTO updatePost(PostDTO inputPost, Long ID);
         Boolean deleteByID(Long ID);

}
