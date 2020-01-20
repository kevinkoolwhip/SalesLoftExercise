package com.kevinkoleck.io.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;

/**
 * Created by kevinkoleck on 1/19/20.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("email_address")
    private String emailAddress;
    @SerializedName("title")
    private String title;
}
