package com.thoughtworks.capacity.gtb.mvc.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private int id;
    private String username;
    private String password;
    private String email;
}
