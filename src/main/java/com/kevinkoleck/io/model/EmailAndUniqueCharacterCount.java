package com.kevinkoleck.io.model;

import lombok.*;

import java.util.List;

/**
 * Created by kevinkoleck on 1/20/20.
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmailAndUniqueCharacterCount {
    private String email;
    private List<UniqueCharacterCount> uniqueCharacterCount;
}
