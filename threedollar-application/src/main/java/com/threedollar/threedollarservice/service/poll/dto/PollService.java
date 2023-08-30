package com.threedollar.threedollarservice.service.poll.dto;

import com.threedollar.threedollardomain.domain.poll.repository.PollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PollService {

    private final PollRepository pollRepository;


}
