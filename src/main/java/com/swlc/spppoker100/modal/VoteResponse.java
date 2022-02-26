package com.swlc.spppoker100.modal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author hp
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoteResponse {
    private long voteId;
    private long userId;
    private String firstName;
    private String lastName;
    private String username;
    private Date voteDate;
    private String vote;

    @Override
    public String toString() {
        return "VoteResponse{" +
                "voteId=" + voteId +
                ", userId=" + userId +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", username=" + username +
                ", voteDate=" + voteDate +
                ", vote='" + vote + '\'' +
                '}';
    }
}
