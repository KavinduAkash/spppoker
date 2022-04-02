package com.swlc.spppoker100.modal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author hp
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VoteProcess {
    private String room_ref;
    private boolean voting;

    @Override
    public String toString() {
        return "VoteProcess{" +
                "room_ref='" + room_ref + '\'' +
                ", voting=" + voting +
                '}';
    }
}
