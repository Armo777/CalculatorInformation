package com.example.calculatorinformation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/solve")
    public ResponseEntity<String> solveProblem(@RequestBody ProblemRequest request) {
        double value = request.getValue();
        String fromUnit = request.getFromUnit();
        String toUnit = request.getToUnit();

        // Логика конвертации единиц информации
        String result = convertUnits(value, fromUnit, toUnit);

        // Формирование ответа в формате JSON
        return ResponseEntity.ok("{\"result\": " + result + "}");
    }

    private String convertUnits(double value, String fromUnit, String toUnit) {

        double res;
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
            res = value * 8; // Переводим байты в биты
            return value + " " + fromUnit + " = " + res + " " + toUnit;
        } else if (toUnit.equalsIgnoreCase("kb")) {
            res = value / 1024; // Переводим байты в килобайты
            return value + " " + fromUnit + " = " + res + " " + toUnit;
        } else if (toUnit.equalsIgnoreCase("mb")) {
            res = value / (1024 * 1024); // Переводим байты в мегабайты
            return value + " " + fromUnit + " = " + res + " " + toUnit;
        } else if (toUnit.equalsIgnoreCase("gb")) {
            res = value / (1024 * 1024 * 1024); // Переводим байты в гигабайты
            return value + " " + fromUnit + " = " + res + " " + toUnit;
        } else if (toUnit.equalsIgnoreCase("byte")) {
            return value + " " + fromUnit + " = " + value + " " + toUnit;
        } else {
            return "Преобразование не поддерживается";
        }
    }
}
