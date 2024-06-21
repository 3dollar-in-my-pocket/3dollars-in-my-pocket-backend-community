package com.threedollar.service.post.response;

import com.threedollar.domain.post.Post;
import com.threedollar.service.poll.dto.response.CursorResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class PostAndCursorResponse {

    private CursorResponse cursor;

    private List<PostResponse> posts;

    @Builder
    public PostAndCursorResponse(CursorResponse cursor, List<PostResponse> posts) {
        this.cursor = cursor;
        this.posts = posts;
    }

    public static PostAndCursorResponse hasNext(List<Post> posts) {
        return PostAndCursorResponse.builder()
            .cursor(CursorResponse.builder()
                .hasNext(true)
                .cursor(posts.get(posts.size() - 2).getId())
                .build())
            .posts(getPostResponse(posts.subList(0, posts.size() - 1)))
            .build();
    }

    public static PostAndCursorResponse noMore(List<Post> posts) {
        return PostAndCursorResponse.builder()
            .cursor(CursorResponse.builder()
                .hasNext(false)
                .cursor(null)
                .build())
            .posts(getPostResponse(posts))
            .build();
    }

    public static List<PostResponse> getPostResponse(List<Post> posts) {
        return posts.stream()
            .map(PostResponse::of)
            .collect(Collectors.toList());
    }
}
