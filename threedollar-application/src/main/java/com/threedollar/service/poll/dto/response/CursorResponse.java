package com.threedollar.service.poll.dto.response;

import com.threedollar.domain.poll.Poll;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CursorResponse {

    private boolean hasNext;

    private Long next;

    @Builder
    public CursorResponse(boolean hasNext, Long next) {
        this.hasNext = hasNext;
        this.next = next;
    }

}
