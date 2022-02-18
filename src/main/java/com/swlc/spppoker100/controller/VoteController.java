package com.swlc.spppoker100.controller;

import com.swlc.spppoker100.modal.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * @author hp
 */
@Controller
public class VoteController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/private-message")
    private Vote receivePrivateMessage(@Payload Vote vote) {
        simpMessagingTemplate.convertAndSendToUser(vote.getRoom_ref(), "/private", vote); // /user/David/private
        return vote;
    }
}
