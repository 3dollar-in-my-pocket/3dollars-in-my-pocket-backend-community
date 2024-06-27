package com.threedollar.service.post;

import com.threedollar.IntegrationTest;
import com.threedollar.domain.post.Post;
import com.threedollar.domain.post.PostGroup;
import com.threedollar.domain.post.PostStatus;
import com.threedollar.domain.post.SectionType;
import com.threedollar.domain.post.repository.PostRepository;
import com.threedollar.service.post.request.PostAddRequest;
import com.threedollar.service.post.request.PostSectionRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostServiceTest extends IntegrationTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @AfterEach
    void clean_up() {
        postRepository.deleteAll();
    }

    @Test
    void 사장님이_소식을_작성한다() {
        // given
        String workspaceId = "three-user";
        PostAddRequest request = newRequest();
        String accountId = "user22";

        // when
        postService.addPost(request, workspaceId, accountId);

        // then
        Post post = postRepository.findAll().get(0);
        assertThat(postRepository.findAll()).hasSize(1);
        assertPost(post, request.getPostGroup(), request.getTitle(), request.getContent(), accountId, workspaceId, request.getTargetId());
    }

    @Test
    void 사장님이_소식을_지운다() {
        // given
        String workspaceId = "three-dollar-dev";
        String accountId = "user";
        Post post = postRepository.save(newRequest().toEntity(workspaceId, accountId));

        // when
        postService.deletePost(workspaceId, accountId, post.getId(), post.getPostGroup(), post.getTargetId());

        // then
        List<Post> posts = postRepository.findAll();
        assertThat(posts).hasSize(1);
        assertThat(posts.get(0).getStatus()).isEqualTo(PostStatus.DELETED);

    }

    private void assertPost(Post post, PostGroup postGroup, String title, String content, String accountId, String workspaceId, String targetId) {

        assertThat(post.getPostGroup()).isEqualTo(postGroup);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
        assertThat(post.getAccountId()).isEqualTo(accountId);
        assertThat(post.getWorkspaceId()).isEqualTo(workspaceId);
        assertThat(post.getTargetId()).isEqualTo(targetId);

    }

    private PostAddRequest newRequest() {

        PostGroup postGroup = PostGroup.NEWS_POST;
        String targetId = "33";
        String title = "은평구 핫도그 아저씨";
        String content = "콘텐트";
        SectionType sectionType = SectionType.IMAGE;
        double ratio = 23.333;
        String url = "image.jpg";

        PostSectionRequest postSectionRequest = PostSectionRequest.builder()
            .sectionType(sectionType)
            .ratio(ratio)
            .url(url)
            .build();

        return PostAddRequest.builder()
            .postGroup(postGroup)
            .title(title)
            .content(content)
            .targetId(targetId)
            .sections(List.of(postSectionRequest))
            .build();
    }

}
