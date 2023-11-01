package com.mohamed.applicationbancaire.controllers;

import com.mohamed.applicationbancaire.projections.TransactionDetails;
import com.mohamed.applicationbancaire.services.StaticsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statics")
@RequiredArgsConstructor
@Tag(name = "statics")
public class StaticsController {

    private final StaticsService service;

    @GetMapping("/sum-transactions/user/{user-id}/by-date")
    public ResponseEntity<List<TransactionDetails>> findSumTransactionsByDate(
            @RequestParam(name = "start-date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate starDate,
            @RequestParam(name = "end-date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            @PathVariable("user-id") Integer userId
    ){
        return ResponseEntity.ok(service.findSumTransactionsByDate(starDate, endDate, userId));
    }

    @GetMapping("/all-transactions/user/{user-id}/by-date")
    public ResponseEntity<List<TransactionDetails>> findTransactionsByDate(
            @RequestParam(name = "start-date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate starDate,
            @RequestParam(name = "end-date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            @PathVariable("user-id") Integer userId
    ){
        return ResponseEntity.ok(service.findTransactionsByDate(starDate, endDate, userId));
    }

    @GetMapping("/user/{user-id}/balance")
    public ResponseEntity<BigDecimal> getAccountBalance(@PathVariable("user-id") Integer userId) {
        return ResponseEntity.ok(service.getAccountBalance(userId));
    }

    @GetMapping("/user/{user-id}/highest-transfert")
    public ResponseEntity<BigDecimal> highestTransfert(@PathVariable("user-id") Integer userId) {
        return ResponseEntity.ok(service.highestTransfert(userId));
    }

    @GetMapping("/user/{user-id}/highest-deposit")
    public ResponseEntity<BigDecimal> highestDeposit(@PathVariable("user-id") Integer userId) {
        return ResponseEntity.ok(service.highestDeposit(userId));
    }

}
