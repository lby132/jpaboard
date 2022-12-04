package lby.project.jpaboard.controller;

import lby.project.jpaboard.exception.NotEnoughStockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IllegalStateException.class)
    public String existence(IllegalStateException ex, Model model){
        model.addAttribute("error", ex.getMessage());
        return "error/error";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleAll(Exception ex, Model model){
        model.addAttribute("error", "잘못된 입력입니다.");
        return "error/error";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NotEnoughStockException.class)
    public String overCnt(NotEnoughStockException ex, Model model){
        model.addAttribute("error", ex.getMessage());
        return "error/error";
    }
}
