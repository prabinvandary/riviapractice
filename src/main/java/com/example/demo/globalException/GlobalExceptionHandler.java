package com.example.demo.globalException;

import com.example.demo.pojo.ApiResponse;
        import org.hibernate.exception.ConstraintViolationException;
        import org.springframework.dao.DataIntegrityViolationException;
        import org.springframework.web.bind.MethodArgumentNotValidException;
        import org.springframework.web.bind.annotation.ExceptionHandler;
        import org.springframework.web.bind.annotation.RestControllerAdvice;

        import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResponse handleUniqueViolation(ConstraintViolationException ex) {
        if (ex.getConstraintName().contains("unique")) {
            ApiResponse apiResponse = new ApiResponse();
            // unique_user_name
            String unique = ex.getConstraintName().replace("unique_", "");
            return apiResponse.error(unique + " already exists", null);
        }
        return null;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ApiResponse handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ApiResponse apiResponse = new ApiResponse();
        return apiResponse.error(ex.getCause().getCause().getLocalizedMessage(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse handelMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ApiResponse apiResponse = new ApiResponse();
        return apiResponse.error(Objects.requireNonNull(ex.getCause().getLocalizedMessage()), null);
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse handleAllException(Exception ex) {
        ApiResponse apiResponse = new ApiResponse();
        return apiResponse.error(ex.getMessage(), null);

    }
}