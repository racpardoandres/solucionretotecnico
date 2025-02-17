package org.web.excepciones;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest; // Importa HttpServletRequest
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.HandlerMethod;
import org.web.interfaces.ExceptionService;

import java.util.List;
import java.util.stream.Collectors;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    private final HttpServletRequest request; // Inyecta HttpServletRequest

    private final ExceptionService exceptionService;

    public GlobalExceptionHandler(HttpServletRequest request, ExceptionService exceptionService) {
        this.request = request;
        this.exceptionService = exceptionService;
    }

    @ExceptionHandler(AccessDeniedException.class)  // *** AÑADE ESTE MÉTODO ***
    public ResponseEntity<ApiErrorResponse> handleAccessDeniedException(AccessDeniedException ex, HandlerMethod handlerMethod) {
        String requestUrl = this.request.getRequestURL().toString();
        String requestParams = this.request.getParameterMap().toString();
        String controllerName = handlerMethod.getBeanType().getSimpleName();
        String methodName = handlerMethod.getMethod().getName();

        logger.warn("Acceso denegado: {} - URL: {}, Parámetros: {}, Controlador: {}, Método: {}",
                ex.getMessage(), requestUrl, requestParams, controllerName, methodName);

        exceptionService.guardarExcepcion(ex, requestUrl, requestParams, methodName, null);

        ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.FORBIDDEN.value(), "Acceso denegado. No tienes los permisos necesarios."); // 403
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> handleBadCredentialsException(BadCredentialsException ex, HandlerMethod handlerMethod) {
        String requestUrl = this.request.getRequestURL().toString();
        String requestParams = this.request.getParameterMap().toString();
        String controllerName = handlerMethod.getBeanType().getSimpleName();
        String methodName = handlerMethod.getMethod().getName();

        logger.warn("Credenciales incorrectas: {} - URL: {}, Parámetros: {}, Controlador: {}, Método: {}",
                ex.getMessage(), requestUrl, requestParams, controllerName, methodName);

        exceptionService.guardarExcepcion(ex, requestUrl, requestParams, methodName, null);

        ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.UNAUTHORIZED.value(), "Usuario o contraseña incorrectos");
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BussinessNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleBussinessNotFoundException(BussinessNotFoundException ex, HandlerMethod handlerMethod) {
        String requestUrl = this.request.getRequestURL().toString();
        String requestParams = this.request.getParameterMap().toString();
        String controllerName = handlerMethod.getBeanType().getSimpleName();
        String methodName = handlerMethod.getMethod().getName();

        logger.warn("No encontrado: {} - URL: {}, Parámetros: {}, Controlador: {}, Método: {}",
                ex.getMessage(), requestUrl, requestParams, controllerName, methodName);

        exceptionService.guardarExcepcion(ex, requestUrl, requestParams, methodName, null);

        ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiErrorResponse>
    handleNotFoundException(NotFoundException ex, HandlerMethod handlerMethod) {
        String requestUrl = this.request.getRequestURL().toString();
        String requestParams = this.request.getParameterMap().toString();
        String controllerName = handlerMethod.getBeanType().getSimpleName();
        String methodName = handlerMethod.getMethod().getName();

        logger.warn("No encontrado: {} - URL: {}, Parámetros: {}, Controlador: {}, Método: {}", ex.getMessage(), requestUrl, requestParams, controllerName, methodName);

        exceptionService.guardarExcepcion(ex, requestUrl, requestParams, methodName, null);

        ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex,
            WebRequest request,
            HandlerMethod handlerMethod) {

        String requestUrl = this.request.getRequestURL().toString();
        String requestParams = this.request.getParameterMap().toString();
        String controllerName = handlerMethod.getBeanType().getSimpleName();
        String methodName = handlerMethod.getMethod().getName();

        logger.warn("Error de validación: {} - URL: {}, Parámetros: {}, Controlador: {}, Método: {}", ex.getMessage(), requestUrl, requestParams, controllerName, methodName);

        // Extrae los mensajes de error de las violaciones de validación
        List<String> errores = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        exceptionService.guardarExcepcion(ex, requestUrl, requestParams, methodName, null);


        ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), "Error de validación en la solicitud: " + errores);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


}