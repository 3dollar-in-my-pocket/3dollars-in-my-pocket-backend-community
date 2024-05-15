package com.threedollar.domain.apikey;

import com.threedollar.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ApiKey extends BaseEntity {

    @Column(nullable = false, length = 50)
    private String workspaceId;

    @Column(nullable = false, length = 100)
    private String apiKey;

    @Column(length = 200)
    private String description;

    @Column(nullable = false, columnDefinition = "VARCHAR(30)")
    @Enumerated(EnumType.STRING)
    private ApiKeyStatus status;

    @Builder
    private ApiKey(String workspaceId, String apiKey, String description, ApiKeyStatus status) {
        this.workspaceId = workspaceId;
        this.apiKey = apiKey;
        this.description = description;
        this.status = status;
    }


}
