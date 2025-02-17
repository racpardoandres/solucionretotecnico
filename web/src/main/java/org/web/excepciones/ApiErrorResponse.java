package org.web.excepciones;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ApiErrorResponse {


    private int status;
        private String message;
        private LocalDateTime timestamp;  // Agrega la fecha y hora

        public ApiErrorResponse(int status, String message) {
            this.status = status;
            this.message = message;
            this.timestamp = LocalDateTime.now(); // Inicializa la fecha y hora actual
        }

}
