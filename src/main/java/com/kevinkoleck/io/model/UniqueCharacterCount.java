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
public class UniqueCharacterCount {
    private String character;
    private Integer count;
}
