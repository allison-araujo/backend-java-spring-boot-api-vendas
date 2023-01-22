package com.io.github.allison;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class JwtService {

    @Value("${security.jwt.expire}")
    private String expire;

    @Value("${security.jwt.key-assign}")
    private String keyAssign;



}
