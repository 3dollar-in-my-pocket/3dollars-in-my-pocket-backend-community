package com.threedollar.domain.post.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.threedollar.domain.post.Post;
import com.threedollar.domain.post.PostStatus;
import lombok.RequiredArgsConstructor;

import static com.threedollar.domain.post.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Post findByIdAndWorkspaceIdAndAccountId(Long postId, String accountId, String workspaceId) {
        return jpaQueryFactory.selectFrom(post)
            .where(
                post.workspaceId.eq(workspaceId),
                post.accountId.eq(accountId),
                post.id.eq(postId),
                post.status.eq(PostStatus.ACTIVE)
            )
            .fetchOne();
    }
}
