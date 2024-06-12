package com.lab6.server.jsonManager;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lab6.server.product.Product;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.LinkedHashSet;

public class JsonManager {
    public LinkedHashSet<Product> read(File file) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
        objectMapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"));
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        LinkedHashSet<Product> products;
        try {

            try {

                FileReader reader = new FileReader(file);
                products = objectMapper.readValue(reader, new TypeReference<LinkedHashSet<Product>>(){});
                reader.close();

            } catch (IOException e) {
                throw new IOException("Целостность файла повреждена или указан неверный путь.");
            }

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }

        return products;
    }

    public void write(LinkedHashSet<? extends Product> products, File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"));
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.println(objectMapper.writeValueAsString(products));
            writer.flush();
            writer.close();
        } catch (FileNotFoundException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
