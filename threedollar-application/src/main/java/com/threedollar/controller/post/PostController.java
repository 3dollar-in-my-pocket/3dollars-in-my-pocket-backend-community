package com.threedollar.controller.post;

import com.threedollar.common.dto.response.ApiResponse;
import com.threedollar.config.interceptor.ApiKeyContext;
import com.threedollar.config.resolver.RequestApiKey;
import com.threedollar.service.post.PostFacadeService;
import com.threedollar.service.post.request.PostAddRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostFacadeService postFacadeService;

    @PostMapping("/v1/post")
    public ApiResponse<String> addPost(@RequestApiKey ApiKeyContext workspaceId,
                                       @RequestParam String accountId,
                                       @Valid @RequestBody PostAddRequest request) {

        postFacadeService.addPost(request, workspaceId.getWorkspaceId(), accountId);
        return ApiResponse.OK;

    }

    @DeleteMapping("/v1/post")
    public ApiResponse<String> deletePost(Long postId,
                                          String accountId,
                                          String workspaceId) {

        postFacadeService.deletePost(workspaceId, accountId, postId);
        return ApiResponse.OK;

    }

}
