package com.swlc.spppoker100.modal;

import lombok.*;

/**
 * @author hp
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SavePoints {
    private String room_ref;
    private long candidate_id;
    private int vote;

    @Override
    public String toString() {
        return "SavePoints{" +
                "room_ref='" + room_ref + '\'' +
                ", candidate_id=" + candidate_id +
                ", vote=" + vote +
                '}';
    }
}
