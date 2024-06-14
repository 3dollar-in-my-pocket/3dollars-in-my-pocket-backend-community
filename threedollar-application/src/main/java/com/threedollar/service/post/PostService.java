package com.threedollar.service.post;

import com.threedollar.domain.post.repository.PostRepository;
import com.threedollar.service.post.request.PostAddRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void addPost(PostAddRequest request,
                        @NotBlank String workspaceId) {

        postRepository.save(request.toEntity(workspaceId));

    }


}
