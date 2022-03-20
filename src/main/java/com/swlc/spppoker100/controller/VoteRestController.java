package com.swlc.spppoker100.controller;

import com.swlc.spppoker100.modal.AllVoteResponse;
import com.swlc.spppoker100.modal.CommonResponseDTO;
import com.swlc.spppoker100.service.VoteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author hp
 */
@Log4j2
@RestController
@CrossOrigin
@RequestMapping("v1/votex")
public class VoteRestController {
    @Autowired
    private VoteService voteService;

    @PostMapping("/private-message-just-connect")
    private ResponseEntity receivePrivateMessage(@RequestParam("ref") String room_ref) {
        AllVoteResponse result = voteService.justVote(room_ref);
//        simpMessagingTemplate.convertAndSendToUser(room_ref, "/private", result); // /user/David/private

        log.info("result: {} " + result.toString());
        return new ResponseEntity<>(
                new CommonResponseDTO(true, "", result),
                HttpStatus.OK
        );
    }

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
}
