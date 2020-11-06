package com.thoughtworks.capacity.gtb.mvc.controller;

import com.thoughtworks.capacity.gtb.mvc.model.Account;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CreateAccountRequest {

    @NotBlank
    @Pattern(regexp = "^([a-zA-Z]*[0-9_-]*$)", message = "only allow number, word, slip")
    @Size(max = 10, min = 3, message = "username length should be 3 to 10")
    private final String username;

    @NotBlank
    @Size(max = 12, min = 5, message = "password length should be 5 to 12")
    private final String password;

    @Email(message = "please input right email")
    private final String email;

    public CreateAccountRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Account toAccount(){
        Account account = new Account();
        account.setUsername(this.username);
        account.setPassword(this.password);
        account.setEmail(this.email);
        return account;
    }
}
