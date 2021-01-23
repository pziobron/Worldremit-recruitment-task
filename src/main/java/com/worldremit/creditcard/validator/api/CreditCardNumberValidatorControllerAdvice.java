package com.worldremit.creditcard.validator.api;

import com.worldremit.creditcard.validator.service.exception.InvalidNumberException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class CreditCardNumberValidatorControllerAdvice {

  @ResponseBody
  @ExceptionHandler(InvalidNumberException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ValidationResponse> invalidNumberInput(InvalidNumberException ex) {
      ValidationResponse response = new ValidationResponse(false);
      response.setErrorMessage(ex.getMessage());
      return ResponseEntity.badRequest().body(response);
  }

}