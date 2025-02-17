package org.web.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.web.interfaces.ExceptionJPARepository;
import org.web.interfaces.ExceptionService;
import org.web.jpa.ExceptionJPA;

import java.time.LocalDateTime;

@Repository
@Transactional
public class ExceptionJpaRepositoryImpl implements ExceptionService {


    private final ExceptionJPARepository exceptionJPARepository;

    public ExceptionJpaRepositoryImpl(ExceptionJPARepository exceptionJPARepository) {
        this.exceptionJPARepository = exceptionJPARepository;
    }

    @Override
    public void guardarExcepcion(Exception ex, String requestUrl,
                                 String requestParams, String method, Long userId) {
        ExceptionJPA exceptionJPA = new ExceptionJPA(
                LocalDateTime.now(),
                ex.getClass().getName(),
                // Obtén el nombre del método donde se originó la excepción (si es posible)
                method, // O usa un valor por defecto
                // Obtén el número de línea donde se originó la excepción (si es posible)
                0, // O usa un valor por defecto
                ex.getMessage(),
                getStackTrace(ex), // Método auxiliar para obtener el stack trace
                userId,
                requestUrl,
                requestParams
        );
        exceptionJPARepository.save(exceptionJPA);
    }

    private String getStackTrace(Exception ex) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : ex.getStackTrace()) {
            sb.append(element).append("\n");
        }
        return sb.toString();
    }
}
