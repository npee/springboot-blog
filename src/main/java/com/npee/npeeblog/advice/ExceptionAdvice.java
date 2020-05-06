package com.npee.npeeblog.advice;

import com.npee.npeeblog.advice.exception.CustomUserExistsException;
import com.npee.npeeblog.model.config.CommonResult;
import com.npee.npeeblog.model.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private final ResponseService responseService;
    private final MessageSource messageSource;

    @ExceptionHandler(CustomUserExistsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult duplicatedEmailException(HttpServletRequest request, CustomUserExistsException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("userExists.code")), getMessage("userExists.message"));
    }

    private String getMessage(String code) {
        return getMessage(code, null);
    }

    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
