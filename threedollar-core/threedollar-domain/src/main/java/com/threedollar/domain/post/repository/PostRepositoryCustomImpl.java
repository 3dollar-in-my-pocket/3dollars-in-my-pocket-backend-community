package com.threedollar.domain.post.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.threedollar.domain.post.Post;
import com.threedollar.domain.post.PostStatus;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.threedollar.domain.post.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Post findByIdAndWorkspaceIdAndAccountIdAndTargetId(Long postId, String accountId, String workspaceId, String targetId) {
        return jpaQueryFactory.selectFrom(post)
            .where(
                post.workspaceId.eq(workspaceId),
                post.accountId.eq(accountId),
                post.id.eq(postId),
                post.targetId.eq(targetId),
                post.status.eq(PostStatus.ACTIVE)
            )
            .fetchOne();
    }

    @Override
    public List<Post> findByAccountIdAndWorkspaceIdAndCursorAndSize(String workspaceId, String accountId, Long cursor, int size) {
        return jpaQueryFactory.selectFrom(post)
            .where(
                existsCursor(cursor),
                post.accountId.eq(accountId),
                post.workspaceId.eq(workspaceId),
                post.status.eq(PostStatus.ACTIVE)
            )
            .fetch();
    }

    private BooleanExpression existsCursor(Long cursor) {
        if (cursor == null) {
            return null;
        }
        return post.id.lt(cursor);
    }
}
