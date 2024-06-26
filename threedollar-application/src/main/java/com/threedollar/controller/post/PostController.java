package com.threedollar.controller.post;

import com.threedollar.common.dto.response.ApiResponse;
import com.threedollar.config.interceptor.ApiKeyContext;
import com.threedollar.config.resolver.RequestApiKey;
import com.threedollar.domain.post.PostGroup;
import com.threedollar.service.post.PostFacadeService;
import com.threedollar.service.post.request.GetPostRequest;
import com.threedollar.service.post.request.PostAddRequest;
import com.threedollar.service.post.request.PostAndCursorRequest;
import com.threedollar.service.post.response.PostAndCursorResponse;
import com.threedollar.service.post.response.PostResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @DeleteMapping("/v1/post-group/{postGroup}/post/{postId}")
    public ApiResponse<String> deletePost(@PathVariable Long postId,
                                          @PathVariable PostGroup postGroup,
                                          @Valid GetPostRequest request) {
        postFacadeService.deletePost(request.getWorkspaceId(), request.getAccountId(), postId, postGroup, request.getTargetId());
        return ApiResponse.OK;
    }

    @GetMapping("/v1/post-group/{postGroup}")
    public ApiResponse<PostAndCursorResponse> getPosts(@Valid PostAndCursorRequest request,
                                                       @PathVariable PostGroup postGroup) {
        return ApiResponse.success(postFacadeService.getPostAndCursor(request, postGroup));
    }

    @GetMapping("/v1/post-group/{postGroup}/post/{postId}")
    public ApiResponse<PostResponse> getPost(@Valid GetPostRequest postRequest,
                                             @PathVariable PostGroup postGroup,
                                             @PathVariable Long postId) {
        return ApiResponse.success(postFacadeService.getPostById(postRequest.getWorkspaceId(),
            postRequest.getAccountId(), postId, postGroup, postRequest.getTargetId()));

    }

    @GetMapping("/v1/post-group/{postGroup}/count")
    public ApiResponse<Integer> getPostCount(@PathVariable PostGroup postGroup,
                                             @RequestParam String workspaceId,
                                             @RequestParam String targetId) {
        return ApiResponse.success(postFacadeService.getPostCountByTargetId(workspaceId, postGroup, targetId));

    }


}
