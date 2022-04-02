package com.swlc.spppoker100.modal;

import lombok.*;

/**
 * @author hp
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommonResponseDTO {
    private boolean success;
    private String msg;
    private Object body;
}
