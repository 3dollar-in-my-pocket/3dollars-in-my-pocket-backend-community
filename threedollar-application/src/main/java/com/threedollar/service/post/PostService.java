package com.threedollar.service.post;

import com.threedollar.common.exception.NotFoundException;
import com.threedollar.domain.post.Post;
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
                        @NotBlank String workspaceId,
                        String accountId) {

        postRepository.save(request.toEntity(workspaceId, accountId));

    }

    @Transactional
    public void deletePost(String workspaceId,
                           String accountId,
                           Long postId) {

        Post post = postRepository.findByIdAndWorkspaceIdAndAccountId(postId, accountId, workspaceId);
        if (post == null) {
            throw new NotFoundException(String.format("(%s)에 해당하는 postId 는 존재하지 않거나 잘못된 접근입니다", postId));
        }

        post.delete();

    }

}
