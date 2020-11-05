package com.xprem.support.exception;

import com.xprem.support.enums.ErrorType;
import com.xprem.support.model.ApiError;
import com.xprem.support.model.ApiResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Order(1)
@ControllerAdvice
@EnableWebMvc
public class GlobalRestErrorHandlerAdvice<T> {

    private ApiError getGenericApiError(String message) {
        ApiError apiError = new ApiError();
        apiError.setMessage(message);
        apiError.setErrorType(ErrorType.GENERIC_ERROR);
        apiError.setTitle("Hata Olustu");
        apiError.setHttpStatusCode(HttpStatus.BAD_REQUEST.toString());
        // logger.error(message);
        return apiError;
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<?> paymentRequired(RedirectAttributes redirectAttributes, MissingServletRequestParameterException e) {
        ApiResponse<Void> response = new ApiResponse();
        response.setSuccess(false);
        response.setApiError(getGenericApiError(e.getMessage()));
        //logger.error(e.getMessage());
        return new ResponseEntity<Object>(response, HttpStatus.PAYMENT_REQUIRED);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<?> genericException(RedirectAttributes redirectAttributes, MissingServletRequestParameterException e) {
        ApiResponse<Void> response = new ApiResponse();
        response.setSuccess(false);
        response.setApiError(getGenericApiError(e.getMessage()));
        //logger.error(e.getMessage());
        return new ResponseEntity<Object>(response, HttpStatus.PAYMENT_REQUIRED);
    }
}
