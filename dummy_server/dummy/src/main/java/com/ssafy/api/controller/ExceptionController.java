package com.ssafy.api.controller;


import com.ssafy.error.exception.securityException.CAuthenticationEntryPointException;
import com.ssafy.error.exception.securityException.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/exception")
public class ExceptionController {

    @GetMapping("/entryPoint")
    public CommonResult entrypointException(){
        throw new CAuthenticationEntryPointException();
    }

    @GetMapping("/accessDenied")
    public CommonResult accessDeniedException(){
        throw new AccessDeniedException("");
    }
}
