package com.threedollar.service.post;

import com.threedollar.domain.post.Post;
import com.threedollar.service.post.request.PostAddRequest;
import com.threedollar.service.post.request.PostAndCursorRequest;
import com.threedollar.service.post.response.PostAndCursorResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostFacadeService {

    private final PostService postService;

    public Long addPost(PostAddRequest request,
                        @NotBlank String workspaceId,
                        @NotBlank String accountId) {
        return postService.addPost(request, workspaceId, accountId);
    }


    public void deletePost(@NotBlank String workspaceId,
                           @NotBlank String accountId,
                           @NotNull Long postId,
                           @NotBlank String targetId) {

        postService.deletePost(workspaceId, accountId, postId, targetId);

    }

    public PostAndCursorResponse getPostAndCursor(@Valid PostAndCursorRequest request) {
        return postService.getPostsAndCursor(request.getWorkspaceId(), request.getAccountId(), request.getCursor(), request.getSize());
    }


}
