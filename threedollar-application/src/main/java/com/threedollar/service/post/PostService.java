package com.threedollar.service.post;

import com.threedollar.common.exception.NotFoundException;
import com.threedollar.domain.post.Post;
import com.threedollar.domain.post.PostGroup;
import com.threedollar.domain.post.repository.PostRepository;
import com.threedollar.service.post.request.PostAddRequest;
import com.threedollar.service.post.response.PostAndCursorResponse;
import com.threedollar.service.post.response.PostResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;


    @Transactional
    public Long addPost(PostAddRequest request,
                        @NotBlank String workspaceId,
                        String accountId) {

        Post post = postRepository.save(request.toEntity(workspaceId, accountId));
        return post.getId();

    }

    @Transactional
    public void deletePost(String workspaceId,
                           String accountId,
                           Long postId,
                           PostGroup postGroup,
                           String targetId) {

        Post post = validatePost(workspaceId, accountId, postId, postGroup, targetId);
        post.delete();

    }

    @Transactional(readOnly = true)
    public PostAndCursorResponse getPostsAndCursor(PostGroup postGroup,
                                                   String workspaceId,
                                                   String targetId,
                                                   Long cursor,
                                                   int size) {
        List<Post> posts = postRepository.findByPostGroupAndWorkspaceIdAndTargetIdAndCursorAndSize(postGroup, workspaceId, targetId, cursor, size + 1);

        if (posts.isEmpty() || posts.size() <= size) {
            return PostAndCursorResponse.noMore(posts);
        }
        return PostAndCursorResponse.hasNext(posts);

    }


    @Transactional(readOnly = true)
    public PostResponse getPostById(String workspaceId,
                                    String accountId,
                                    Long postId,
                                    PostGroup postGroup,
                                    String targetId) {
        Post post = validatePost(workspaceId, accountId, postId, postGroup, targetId);
        return PostResponse.of(post);
    }

    private Post validatePost(String workspaceId,
                              String accountId,
                              Long postId,
                              PostGroup postGroup,
                              String targetId) {
        Post post = postRepository.findByIdAndWorkspaceIdAndAccountIdAndGroupAndTargetId(postId, accountId, workspaceId, postGroup, targetId);
        if (post == null) {
            throw new NotFoundException(String.format("(%s)에 해당하는 postId 는 존재하지 않거나 잘못된 접근입니다", postId));
        }
        return post;

    }

}
