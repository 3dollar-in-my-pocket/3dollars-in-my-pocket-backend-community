package com.threedollar.service.poll.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CursorResponse {

    private boolean hasNext;

    private Long cursor;

    @Builder
    public CursorResponse(boolean hasNext, Long cursor) {
        this.hasNext = hasNext;
        this.cursor = cursor;
    }

}
