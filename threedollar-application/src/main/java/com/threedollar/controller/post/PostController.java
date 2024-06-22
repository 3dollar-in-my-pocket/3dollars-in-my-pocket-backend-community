package com.threedollar.controller.post;

import com.threedollar.common.dto.response.ApiResponse;
import com.threedollar.config.interceptor.ApiKeyContext;
import com.threedollar.config.resolver.RequestApiKey;
import com.threedollar.domain.post.PostGroup;
import com.threedollar.service.post.PostFacadeService;
import com.threedollar.service.post.request.PostAddRequest;
import com.threedollar.service.post.request.PostAndCursorRequest;
import com.threedollar.service.post.response.PostAndCursorResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostFacadeService postFacadeService;

    @PostMapping("/v1/post")
    public ApiResponse<Long> addPost(@RequestApiKey ApiKeyContext workspaceId,
                                     @RequestParam String accountId,
                                     @Valid @RequestBody PostAddRequest request) {

        return ApiResponse.success(postFacadeService.addPost(request, workspaceId.getWorkspaceId(), accountId));

    }

    @DeleteMapping("/v1/post")
    public ApiResponse<String> deletePost(Long postId,
                                          String accountId,
                                          String workspaceId,
                                          PostGroup postGroup) {
        postFacadeService.deletePost(workspaceId, accountId, postId, postGroup);
        return ApiResponse.OK;
    }

    @GetMapping("/v1/posts")
    public ApiResponse<PostAndCursorResponse> getPosts(@Valid PostAndCursorRequest request) {
        return ApiResponse.success(postFacadeService.getPostAndCursor(request));
    }

}
