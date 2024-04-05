package org.profitsoft.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.ToString;

/**
 * Author: Viacheslav Korbut
 * Date: 04.04.2024
 */
@ToString
@Data
public class Author {
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
}
