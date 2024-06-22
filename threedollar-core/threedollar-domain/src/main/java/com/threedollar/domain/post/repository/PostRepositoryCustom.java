package com.threedollar.domain.post.repository;

import com.threedollar.domain.post.Post;
import com.threedollar.domain.post.PostGroup;
import jakarta.annotation.Nullable;

import java.util.List;

public interface PostRepositoryCustom {

    Post findByIdAndWorkspaceIdAndAccountIdAndGroup(Long postId,
                                            String accountId,
                                            String workspaceId,
                                            PostGroup group);

    List<Post> findByPostGroupAndAccountIdAndWorkspaceIdAndCursorAndSize(PostGroup postGroup,
                                                                         String workspaceId,
                                                                         String accountId,
                                                                         @Nullable Long cursor,
                                                                         int size);
}
