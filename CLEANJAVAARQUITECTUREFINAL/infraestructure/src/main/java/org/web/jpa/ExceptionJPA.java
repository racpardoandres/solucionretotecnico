package org.web.jpa;

import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "exceptions")
public class ExceptionJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "class_name", nullable = false)
    private String className;

    @Column(name = "method_name", nullable = false)
    private String methodName;

    @Column(name = "line_number", nullable = false)
    private Integer lineNumber;

    @Column(name = "message", nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(name = "stack_trace", columnDefinition = "TEXT")
    private String stackTrace;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "request_url")
    private String requestUrl;

    @Column(name = "request_params", columnDefinition = "TEXT")
    private String requestParams;

    // Constructor, getters y setters

    public ExceptionJPA() {} // Constructor vacío requerido por JPA

    public ExceptionJPA(LocalDateTime timestamp, String className, String methodName, Integer lineNumber, String message, String stackTrace, Long userId, String requestUrl, String requestParams) {
        this.timestamp = timestamp;
        this.className = className;
        this.methodName = methodName;
        this.lineNumber = lineNumber;
        this.message = message;
        this.stackTrace = stackTrace;
        this.userId = userId;
        this.requestUrl = requestUrl;
        this.requestParams = requestParams;
    }


}