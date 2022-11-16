package com.example.demo.security;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResposnse  implements Serializable{
        private static final long serialVersionUID=54536414L;
        private String token;
        private String role;


}