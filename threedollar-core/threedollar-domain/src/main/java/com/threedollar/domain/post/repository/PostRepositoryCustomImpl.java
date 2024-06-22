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
    public Post findByIdAndWorkspaceIdAndAccountIdAndGroup(Long postId, String accountId, String workspaceId, PostGroup postGroup) {
        return jpaQueryFactory.selectFrom(post)
            .where(
                post.workspaceId.eq(workspaceId),
                post.accountId.eq(accountId),
                post.id.eq(postId),
                post.postGroup.eq(postGroup),
                post.status.eq(PostStatus.ACTIVE)
            )
            .fetchOne();
    }

    @Override
    public List<Post> findByPostGroupAndAccountIdAndWorkspaceIdAndCursorAndSize(PostGroup postGroup, String workspaceId, String accountId, Long cursor, int size) {
        List<Long> postIds = jpaQueryFactory.select(post.id)
            .from(post)
            .where(existsCursor(cursor),
                post.workspaceId.eq(workspaceId),
                post.accountId.eq(accountId),
                post.postGroup.eq(postGroup),
                post.status.eq(PostStatus.ACTIVE))
            .orderBy(post.id.desc())
            .limit(size)
            .fetch();

        return jpaQueryFactory.selectFrom(post)
            .leftJoin(post.postSection, postSection).fetchJoin()
            .where(post.id.in(postIds))
            .orderBy(post.id.desc())
            .fetch();
    }

    private BooleanExpression existsCursor(Long cursor) {
        if (cursor == null) {
            return null;
        }
        return post.id.lt(cursor);
    }

}
