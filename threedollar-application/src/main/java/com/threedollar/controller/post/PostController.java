package com.threedollar.controller.post;

import com.threedollar.common.dto.response.ApiResponse;
import com.threedollar.config.interceptor.ApiKeyContext;
import com.threedollar.config.resolver.RequestApiKey;
import com.threedollar.service.post.PostService;
import com.threedollar.service.post.request.PostAddRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/v1/post")
    public ApiResponse<String> addPost(@RequestApiKey ApiKeyContext workspaceId,
                                       @Valid @RequestBody PostAddRequest request) {

        postService.addPost(request, workspaceId.getWorkspaceId());
        return ApiResponse.OK;

    }

}
