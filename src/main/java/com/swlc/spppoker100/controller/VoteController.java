package com.swlc.spppoker100.controller;

import com.swlc.spppoker100.modal.AllVoteResponse;
import com.swlc.spppoker100.modal.CommonResponseDTO;
import com.swlc.spppoker100.modal.Vote;
import com.swlc.spppoker100.service.VoteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * @author hp
 */
@Log4j2
@Controller
public class VoteController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private VoteService voteService;

    @MessageMapping("/private-message")
    private ResponseEntity receivePrivateMessage(@Payload Vote vote) {
        AllVoteResponse result = voteService.vote(vote);
        simpMessagingTemplate.convertAndSendToUser(vote.getRoom_ref(), "/private", result); // /user/David/private

        log.info("result: {} " + result.toString());
        return new ResponseEntity<>(
                new CommonResponseDTO(true, "", result),
                HttpStatus.OK
        );
    }
}
