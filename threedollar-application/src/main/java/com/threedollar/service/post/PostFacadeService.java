package com.threedollar.service.post;

import com.threedollar.service.post.request.PostAddRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostFacadeService {

    private final PostService postService;

    public void addPost(PostAddRequest request,
                        @NotBlank String workspaceId,
                        String accountId) {
        postService.addPost(request, workspaceId, accountId);
    }


    public void deletePost(@NotBlank String workspaceId,
                           @NotBlank String accountId,
                           @NotNull Long postId) {

        postService.deletePost(workspaceId, accountId, postId);

    }


}
