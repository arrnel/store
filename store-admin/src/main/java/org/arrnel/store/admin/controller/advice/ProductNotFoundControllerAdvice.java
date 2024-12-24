package org.arrnel.store.admin.controller.advice;

import lombok.RequiredArgsConstructor;
import org.arrnel.store.admin.ex.ProductNotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Locale;

@ControllerAdvice
@RequiredArgsConstructor
@ParametersAreNonnullByDefault
public class ProductNotFoundControllerAdvice {

    private final MessageSource messageSource;

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleBindException(ProductNotFoundException exception, Locale locale) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                messageSource.getMessage(
                        "errors.404.title",
                        new Long[]{exception.getProductId()},
                        locale));

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(problemDetail);

    }
}
