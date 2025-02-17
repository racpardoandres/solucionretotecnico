package org.web.interfaces;

public interface ExceptionService {
    void guardarExcepcion(Exception ex, String requestUrl,
                          String requestParams, String method, Long userId);
}