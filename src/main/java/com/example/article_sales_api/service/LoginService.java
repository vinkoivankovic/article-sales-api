package com.example.article_sales_api.service;

import com.example.article_sales_api.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Service
public class LoginService {

    Map<String, String> userMap = new HashMap<>(Map.of("user", "password", "admin", "admin"));

    public Optional<Integer> login(UserDTO userDTO) {
        String password = userMap.get(userDTO.username());

        if(password != null && password.equals(userDTO.password())){
            return Optional.of(1);
        }

        return Optional.empty();
    }
}