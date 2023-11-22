package com.example.calculatorinformation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConverterController {

    @GetMapping("/ping")
    public ResponseEntity<String> pingServer() {
        return ResponseEntity.ok("Сервер запущен");
    }

    @GetMapping("/status")
    public ResponseEntity<String> serverStatus() {
        return ResponseEntity.ok("Сервер доступен и работает");
    }

    @GetMapping("/convert")
    public String convertUnits(
            @RequestParam("value") double value,
            @RequestParam("from") String fromUnit,
            @RequestParam("to") String toUnit
    ) {
        double result;

        // Логика конвертации между различными единицами информации
        if (fromUnit.equalsIgnoreCase("bit")) {
            value /= 8; // Переводим биты в байты
        } else if (fromUnit.equalsIgnoreCase("kb")) {
            value *= 1024; // Переводим килобайты в байты
        } else if (fromUnit.equalsIgnoreCase("mb")) {
            value *= 1024 * 1024; // Переводим мегабайты в байты
        } else if (fromUnit.equalsIgnoreCase("gb")) {
            value *= 1024 * 1024 * 1024; // Переводим гигабайты в байты
        }

        // Конвертация из байтов в нужную целевую единицу
        if (toUnit.equalsIgnoreCase("bit")) {
            result = value * 8; // Переводим байты в биты
            return value + " " + fromUnit + " = " + result + " " + toUnit;
        } else if (toUnit.equalsIgnoreCase("kb")) {
            result = value / 1024; // Переводим байты в килобайты
            return value + " " + fromUnit + " = " + result + " " + toUnit;
        } else if (toUnit.equalsIgnoreCase("mb")) {
            result = value / (1024 * 1024); // Переводим байты в мегабайты
            return value + " " + fromUnit + " = " + result + " " + toUnit;
        } else if (toUnit.equalsIgnoreCase("gb")) {
            result = value / (1024 * 1024 * 1024); // Переводим байты в гигабайты
            return value + " " + fromUnit + " = " + result + " " + toUnit;
        } else if (toUnit.equalsIgnoreCase("byte")) {
            return value + " " + fromUnit + " = " + value + " " + toUnit;
        } else {
            return "Преобразование не поддерживается";
        }
    }
}
