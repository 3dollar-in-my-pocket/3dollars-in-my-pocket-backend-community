package com.threedollar.domain.post.repository;

import com.threedollar.domain.post.Post;

public interface PostRepositoryCustom {

    Post findByIdAndWorkspaceIdAndAccountId(Long postId,
                                String accountId,
                                String workspaceId);
}
