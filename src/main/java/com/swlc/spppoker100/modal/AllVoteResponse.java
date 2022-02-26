package com.swlc.spppoker100.modal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author hp
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllVoteResponse {
    private String roomRef;
    private long candidateId;
    private List<VoteResponse> votes;

    @Override
    public String toString() {
        return "AllVoteResponse{" +
                "roomRef='" + roomRef + '\'' +
                ", candidateId=" + candidateId +
                ", votes=" + votes +
                '}';
    }
}
