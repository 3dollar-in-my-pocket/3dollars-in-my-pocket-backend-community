package com.threedollar.controller.post;

import com.threedollar.common.dto.response.ApiResponse;
import com.threedollar.config.interceptor.ApiKeyContext;
import com.threedollar.config.resolver.RequestApiKey;
import com.threedollar.domain.post.PostGroup;
import com.threedollar.service.post.PostFacadeService;
import com.threedollar.service.post.request.GetPostRequest;
import com.threedollar.service.post.request.PostAddRequest;
import com.threedollar.service.post.request.PostAndCursorRequest;
import com.threedollar.service.post.request.PostUpdateRequest;
import com.threedollar.service.post.response.PostAndCursorResponse;
import com.threedollar.service.post.response.PostResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
                                          @RequestApiKey ApiKeyContext workspaceId,
                                          @PathVariable PostGroup postGroup,
                                          @Valid GetPostRequest request) {
        postFacadeService.deletePost(workspaceId.getWorkspaceId(), request.getAccountId(), postId, postGroup, request.getTargetId());
        return ApiResponse.OK;
    }

    @GetMapping("/v1/post-group/{postGroup}")
    public ApiResponse<PostAndCursorResponse> getPosts(@Valid PostAndCursorRequest request,
                                                       @RequestApiKey ApiKeyContext workspaceId,
                                                       @PathVariable PostGroup postGroup) {
        return ApiResponse.success(postFacadeService.getPostAndCursor(request, workspaceId.getWorkspaceId(), postGroup));
    }

    @GetMapping("/v1/post-group/{postGroup}/post/{postId}")
    public ApiResponse<PostResponse> getPost(@RequestParam(required = false) String accountId,
                                             @RequestParam String targetId,
                                             @RequestApiKey ApiKeyContext workspaceId,
                                             @PathVariable PostGroup postGroup,
                                             @PathVariable Long postId) {
        return ApiResponse.success(postFacadeService.getPostById(workspaceId.getWorkspaceId(),
            accountId, postId, postGroup, targetId));

    }

    @GetMapping("/v1/post-group/{postGroup}/count")
    public ApiResponse<Long> getPostCount(@PathVariable PostGroup postGroup,
                                          @RequestApiKey ApiKeyContext workspaceId,
                                          @RequestParam String targetId) {
        return ApiResponse.success(postFacadeService.getPostCountByTargetId(workspaceId.getWorkspaceId(), postGroup, targetId));

    }

    @PatchMapping("/v1/post-group/{postGroup}/post/{postId}")
    public ApiResponse<String> updatePost(@Valid PostUpdateRequest request,
                                          @RequestApiKey ApiKeyContext workspaceId,
                                          @PathVariable PostGroup postGroup,
                                          @PathVariable Long postId,
                                          @RequestParam(required = false) String accountId,
                                          @RequestParam String targetId) {
        postFacadeService.updatePost(workspaceId.getWorkspaceId(), accountId, postGroup, postId, targetId, request);
        return ApiResponse.OK;

    }


}
