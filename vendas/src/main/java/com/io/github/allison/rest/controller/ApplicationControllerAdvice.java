package com.io.github.allison.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.io.github.allison.exception.PedidoNotSearchException;
import com.io.github.allison.exception.RuleBusinessException;
import com.io.github.allison.rest.ApiErros;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RuleBusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleRuleBusinnesException(RuleBusinessException ex){

        String mensagemErro = ex.getMessage();
        return new ApiErros(mensagemErro);


    }

@ExceptionHandler(PedidoNotSearchException.class)
@ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErros handlePedidoNotSearchException(PedidoNotSearchException ex){
        return new ApiErros(ex.getMessage()); 


    }

@ExceptionHandler(MethodArgumentNotValidException.class)
@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleMethodNotValidException(MethodArgumentNotValidException ex){
      List<String> errors =  ex.getBindingResult().getAllErrors().stream().map(erro -> erro.getDefaultMessage()).collect(Collectors.toList());

      return new ApiErros(errors);

    }
    
}
