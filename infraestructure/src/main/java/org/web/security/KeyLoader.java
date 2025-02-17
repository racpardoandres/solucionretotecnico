package org.web.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.slf4j.Logger; // Importa Logger
import org.slf4j.LoggerFactory; // Importa LoggerFactory
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class KeyLoader {

    private static final Logger logger = LoggerFactory.getLogger(KeyLoader.class); // Logger

    @Bean
    public static PrivateKey loadPrivateKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String linea = "";
       
        try {


            ClassPathResource resource = new ClassPathResource("rutaprivada.txt");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
                              
              linea = reader.readLine();
                              
            }

            String key = new String(Files.readAllBytes(Paths.get(linea)))
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s", ""); // Remove new lines and spaces

            byte[] privateKeyBytes = Base64.getDecoder().decode(key);

            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            logger.error("Error al cargar la clave privada desde {}", linea, e); // Log del error
            throw e; // Re-lanza la excepción después de loguearla
        }
    }

    @Bean
    public static PublicKey loadPublicKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String linea = "";

        try {

            ClassPathResource resource = new ClassPathResource("rutapublica.txt");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {

                linea = reader.readLine();

            }
            // Leer el archivo completo como bytes
            byte[] fileBytes = Files.readAllBytes(Paths.get(linea));

            // Convertir a String y eliminar cabeceras y saltos de línea
            String keyContent = new String(fileBytes)
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s", ""); // Elimina espacios, saltos de línea y retornos de carro

            // Imprimir el contenido (para depuración)
            System.out.println("Contenido del archivo public.pem (sin cabeceras): "
                    + keyContent);

            // Decodificar Base64
            byte[] publicKeyBytes = Base64.getMimeDecoder().decode(keyContent);

            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);

        } catch (IllegalArgumentException e) {
            logger.error("Error al decodificar Base64 de la clave pública: {}", e.getMessage());
            throw e; // Re-lanza la excepción
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            logger.error("Error al cargar la clave pública desde {}", linea, e);
            throw e; // Re-lanza la excepción
        }
    }

}