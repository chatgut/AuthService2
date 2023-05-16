package com.example.authservice;

import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    private String secret = "432646294A404E635266546A576E5A7234753778214125442A472D4B6150645367566B58703273357638792F423F4528482B4D6251655468576D5A7133743677";
    private String refreshSecret = "566D597133743677397A244226452948404D635166546A576E5A7234753778214125442A462D4A614E645267556B58703273357638792F423F4528482B4B6250";
    private int expiration = 60;

    public String getSecret() {
        return secret;
    }

    public int getExpiration() {
        return expiration;
    }

    public String getRefreshSecret() {
        return refreshSecret;
    }
}
