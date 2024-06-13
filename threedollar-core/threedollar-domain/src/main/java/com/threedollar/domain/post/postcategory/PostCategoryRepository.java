package com.threedollar.domain.post.postcategory;

import com.threedollar.domain.post.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCategoryRepository extends JpaRepository<PostCategory, Long> {
}
