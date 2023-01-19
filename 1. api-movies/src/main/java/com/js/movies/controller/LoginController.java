package com.js.movies.controller;

import com.js.movies.config.TokenLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private TokenLogin tokens;

    @GetMapping("login")
    public String login(@RequestHeader("Authorization") String auth) {
        return tokens.addToken(auth);
    }

    @GetMapping("valida")
    public String valida(String token) {
        return "Es valido?  " + tokens.exist(token);
    }

    @GetMapping("logout")
    public String logout(@RequestHeader(name = "token", defaultValue = "NOTOKEN") String token) {
        String salida = "";
        if (tokens.exist(token)) {
            tokens.removeToken(token);
            salida = "Sesion inválida";
        } else {
            salida = "Token no existe";
        }
        return salida;
    }
}