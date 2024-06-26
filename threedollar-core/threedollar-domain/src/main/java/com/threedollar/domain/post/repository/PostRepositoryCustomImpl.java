package com.threedollar.domain.post.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.threedollar.domain.post.Post;
import com.threedollar.domain.post.PostGroup;
import com.threedollar.domain.post.PostStatus;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.threedollar.domain.post.QPost.post;
import static com.threedollar.domain.post.postsection.QPostSection.postSection;

@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Post findByIdAndWorkspaceIdAndAccountIdAndGroupAndTargetId(Long postId, String accountId, String workspaceId, PostGroup postGroup, String targetId) {
        return jpaQueryFactory.selectFrom(post)
            .where(
                existsAccountId(accountId),
                post.workspaceId.eq(workspaceId),
                post.id.eq(postId),
                post.postGroup.eq(postGroup),
                post.targetId.eq(targetId),
                post.status.eq(PostStatus.ACTIVE)
            )
            .fetchOne();
    }

    @Override
    public List<Post> findByPostGroupAndWorkspaceIdAndTargetIdAndCursorAndSize(PostGroup postGroup, String workspaceId, String targetId, Long cursor, int size) {
        List<Long> postIds = jpaQueryFactory.select(post.id)
            .from(post)
            .where(
                existsCursor(cursor),
                post.workspaceId.eq(workspaceId),
                post.postGroup.eq(postGroup),
                post.targetId.eq(targetId),
                post.status.eq(PostStatus.ACTIVE)
            )
            .orderBy(post.id.desc())
            .limit(size)
            .fetch();

        return jpaQueryFactory.selectFrom(post)
            .where(post.id.in(postIds))
            .leftJoin(post.postSection, postSection).fetchJoin()
            .orderBy(post.id.desc())
            .fetch();
    }

    @Override
    public Long postCountByWorkspaceIdAndPostGroupAndTargetId(String workspaceId, PostGroup postGroup, String targetId) {
        return jpaQueryFactory.select(post.count())
            .from(post)
            .where(
                post.workspaceId.eq(workspaceId),
                post.postGroup.eq(postGroup),
                post.targetId.eq(targetId),
                post.status.eq(PostStatus.ACTIVE)
            ).fetchOne();
    }

    private BooleanExpression existsCursor(Long cursor) {
        if (cursor == null) {
            return null;
        }
        return post.id.lt(cursor);
    }

    private BooleanExpression existsAccountId(String accountId) {
        if (accountId == null) {
            return null;
        }
        return post.accountId.eq(accountId);
    }


}
