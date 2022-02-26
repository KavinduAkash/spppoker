package com.swlc.spppoker100.service;

import com.swlc.spppoker100.modal.AllVoteResponse;
import com.swlc.spppoker100.modal.Vote;

/**
 * @author hp
 */
public interface VoteService {
    AllVoteResponse vote(Vote vote);
}
