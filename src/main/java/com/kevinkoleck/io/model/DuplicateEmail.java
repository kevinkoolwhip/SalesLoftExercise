package com.kevinkoleck.io.model;

import lombok.*;

/**
 * Created by kevinkoleck on 1/20/20.
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DuplicateEmail {
    private String email1;
    private String email2;
    private double similarity;
}
