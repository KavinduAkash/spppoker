package com.swlc.spppoker100.modal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author hp
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vote {
    private long voter_id;
    private String room_ref;
    private long candidate_id;
    private int vote;
}
