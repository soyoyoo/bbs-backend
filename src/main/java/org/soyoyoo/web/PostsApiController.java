package org.soyoyoo.web;
import lombok.RequiredArgsConstructor;
import org.soyoyoo.service.posts.PostsService;
import org.soyoyoo.web.dto.PostsListResponseDto;
import org.soyoyoo.web.dto.PostsResponseDto;
import org.soyoyoo.web.dto.PostsSaveRequestsDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @GetMapping("/api/v1/posts")
    public List<PostsListResponseDto> findAll() {
        return postsService.findAllDesc();
    }

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestsDto requestDTO) {
        return postsService.save(requestDTO);
    }
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsSaveRequestsDto requestDTO) {
        return postsService.update(id, requestDTO);
    }
    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

}
