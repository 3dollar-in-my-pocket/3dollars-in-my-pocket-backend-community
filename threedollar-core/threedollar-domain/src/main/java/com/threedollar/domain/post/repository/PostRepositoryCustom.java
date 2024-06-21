package com.threedollar.domain.post.repository;

import com.threedollar.domain.post.Post;
import jakarta.annotation.Nullable;

import java.util.List;

public interface PostRepositoryCustom {

    Post findByIdAndWorkspaceIdAndAccountIdAndTargetId(Long postId,
                                                       String accountId,
                                                       String workspaceId,
                                                       String targetId);

    List<Post> findByAccountIdAndWorkspaceIdAndCursorAndSize(String workspaceId,
                                                             String accountId,
                                                             @Nullable Long cursor,
                                                             int size);
}
