package com.sb2.demosb2security.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The Class GlobalExceptionHandler.
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  
  /**
   * Custom exception handle.
   *
   * @param ex the ex
   * @param request the request
   * @return the response entity
   */
  @ExceptionHandler(CustomException.class)
  public ResponseEntity<ErrorResponse> customExceptionHandle(CustomException ex, WebRequest request) {

    ErrorResponse response = new ErrorResponse();
    response.setStatusCode(ex.getStatus().value());
    response.setSuccessMessage(ex.getStatus().name());
    response.setErrorMessage(ex.getMessage());
    return new ResponseEntity<>(response, ex.getStatus());

  }
  
  /**
   * Access Denied Exception handle.
   *
   * @param edx the edx
   * @param request the request
   * @return the response entity
   */
  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<?> accessDeniedException(AccessDeniedException edx) {

    ErrorResponse response = new ErrorResponse();
    response.setStatusCode(HttpStatus.FORBIDDEN.value());
    response.setSuccessMessage("ACCESS_DENIED");
    response.setErrorMessage(edx.getMessage());
    return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
  }
  
  @ExceptionHandler({UsernameNotFoundException.class,BadCredentialsException.class})
  public ResponseEntity<?> authorizationException(Exception ex) {

    ErrorResponse response = new ErrorResponse();
    response.setStatusCode(HttpStatus.UNAUTHORIZED.value());
    response.setSuccessMessage("UserName or Password is Incorrect");
    response.setErrorMessage(ex.getMessage());
    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
  }
  
  @ExceptionHandler(InsufficientAuthenticationException.class)
  public ResponseEntity<?> insufficientAuthenticationException(Exception edx) {

    ErrorResponse response = new ErrorResponse();
    response.setStatusCode(HttpStatus.UNAUTHORIZED.value());
    response.setSuccessMessage("USERNAME AND PASSWORD IS MISSIG");
    response.setErrorMessage(edx.getMessage());
    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
  }
  
//  @ExceptionHandler(NoHandlerFoundException.class)
//  public ResponseEntity<?> noHandlerFoundException(NoHandlerFoundException edx) {
//
//    ErrorResponse response = new ErrorResponse();
//    response.setStatusCode(HttpStatus.NOT_FOUND.value());
//    response.setSuccessMessage("This API endpoint is not found");
//    response.setErrorMessage(edx.getMessage());
//    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//  }
}
