package com.swlc.spppoker100.controller;

import com.swlc.spppoker100.modal.*;
import com.swlc.spppoker100.service.VoteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

//    @PostMapping("/private-message-just-connect")
//    private ResponseEntity receivePrivateMessage(@RequestParam("ref") String room_ref) {
//        AllVoteResponse result = voteService.justVote(room_ref);
////        simpMessagingTemplate.convertAndSendToUser(room_ref, "/private", result); // /user/David/private
//
//        log.info("result: {} " + result.toString());
//        return new ResponseEntity<>(
//                new CommonResponseDTO(true, "", result),
//                HttpStatus.OK
//        );
//    }

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

    @MessageMapping("/private-us")
    private ResponseEntity receiveUserStory(@Payload Vote vote) {
//        AllVoteResponse result = voteService.vote(vote);
        simpMessagingTemplate.convertAndSendToUser(vote.getRoom_ref(), "/private-ustory", vote.getCandidate_id()); // /user/David/private

//        log.info("result: {} " + result.toString());
        return new ResponseEntity<>(
                new CommonResponseDTO(true, "", "XXXXXXXXX"),
                HttpStatus.OK
        );
    }

    @MessageMapping("/private-points")
    private ResponseEntity savePoints(@Payload SavePoints vote) {
        boolean result = voteService.savePoints(vote);
        simpMessagingTemplate.convertAndSendToUser(vote.getRoom_ref(), "/private-save", result); // /user/David/private

//        log.info("result: {} " + result.toString());
        return new ResponseEntity<>(
                new CommonResponseDTO(true, "", "XXXXXXXXX"),
                HttpStatus.OK
        );
    }

    @MessageMapping("/private-vote")
    private ResponseEntity voteProcess(@Payload VoteProcess vote) {
        simpMessagingTemplate.convertAndSendToUser(vote.getRoom_ref(), "/private-voting", vote.isVoting()); // /user/David/private

//        log.info("result: {} " + result.toString());
        return new ResponseEntity<>(
                new CommonResponseDTO(true, "", "XXXXXXXXX"),
                HttpStatus.OK
        );
    }
}
