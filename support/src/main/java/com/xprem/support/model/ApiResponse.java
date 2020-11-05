package com.xprem.support.model;

import com.xprem.support.enums.ErrorType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public class ApiResponse<T> {

    private Boolean success;
    private ApiError apiError;
    private T data;

    public ApiResponse() {
        this.success = null;
        this.apiError = null;
        this.data = null;
    }

    public ApiResponse(T data) {
        this.success = true;
        this.data = data;
        this.apiError = null;
    }

    public ApiResponse(BindingResult bindingResult) {
        ApiError apiError = new ApiError();
        apiError.setTitle("Doğrulama hatası");
        apiError.setErrorType(ErrorType.VALIDATION_ERROR);
        List<ValidationError> validationErrors = new ArrayList<ValidationError>();
        for (ObjectError err : bindingResult.getAllErrors()) {
            ValidationError validationError = new ValidationError();
            String field = err instanceof FieldError ? ((FieldError) err).getField() : err.getObjectName();
            FieldError fieldError = ((FieldError) err);

            String errorMessage = "";

            if (null != fieldError.getDefaultMessage()) {
                errorMessage = fieldError.getDefaultMessage();
            } else {
                errorMessage = fieldError.getCode();
            }

            validationError.setField(field);
            validationError.setMessage(errorMessage);
            validationErrors.add(validationError);
        }

        apiError.setMessage(validationErrors);
        this.success = false;
        this.apiError = apiError;
        this.data = null;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public ApiError getApiError() {
        return apiError;
    }

    public void setApiError(ApiError apiError) {
        this.apiError = apiError;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
