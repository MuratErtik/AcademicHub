package com.yazlab.academichub.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorDetail> sellerExceptionHandler(AuthException ae, WebRequest webRequest) {

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setError(ae.getMessage());
        errorDetail.setDetails(webRequest.getDescription(false));
        errorDetail.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(DepartmentException.class)
    public ResponseEntity<ErrorDetail> DepartmentExceptionHandler(DepartmentException de, WebRequest webRequest) {

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setError(de.getMessage());
        errorDetail.setDetails(webRequest.getDescription(false));
        errorDetail.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(AdminException.class)
    public ResponseEntity<ErrorDetail> AdminExceptionHandler(AdminException ae, WebRequest webRequest) {

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setError(ae.getMessage());
        errorDetail.setDetails(webRequest.getDescription(false));
        errorDetail.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(JobOfferException.class)
    public ResponseEntity<ErrorDetail> AdminExceptionHandler(JobOfferException joe, WebRequest webRequest) {

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setError(joe.getMessage());
        errorDetail.setDetails(webRequest.getDescription(false));
        errorDetail.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorDetail> AdminExceptionHandler(ApplicationException ae, WebRequest webRequest) {

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setError(ae.getMessage());
        errorDetail.setDetails(webRequest.getDescription(false));
        errorDetail.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);

    }
}
