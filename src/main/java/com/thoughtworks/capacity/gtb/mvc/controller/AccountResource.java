package com.thoughtworks.capacity.gtb.mvc.controller;

import com.thoughtworks.capacity.gtb.mvc.model.Account;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AccountResource {
    private List<Account> userInf = new ArrayList<>();

    @PostMapping("/register")
    ResponseEntity<String> register(@Valid @RequestBody CreateAccountRequest createAccountRequest){
        Account registerAccount = createAccountRequest.toAccount();
        List<Account> checkList = userInf.stream()
                .filter(account -> account.getUsername().equals(registerAccount.getUsername()))
                .collect(Collectors.toList());
        if(checkList.size() == 0){
            registerAccount.setId(userInf.size() + 1);
            userInf.add(registerAccount);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/login")
    Object login(@RequestParam(name = "username") String username,
                         @RequestParam(name = "password") String password){
        List<Account> checkList = userInf.stream()
                .filter(account -> account.getUsername().equals(username) &&
                        account.getPassword().equals(password))
                .collect(Collectors.toList());
        if(checkList.size() > 0){
            return checkList.get(0);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
