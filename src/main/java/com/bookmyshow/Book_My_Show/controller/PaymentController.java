package com.bookmyshow.Book_My_Show.controller;

import com.bookmyshow.Book_My_Show.dto.reponse.PaymentResponse;
import com.bookmyshow.Book_My_Show.dto.request.CreatePaymentRequest;
import com.bookmyshow.Book_My_Show.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/payments")
public class PaymentController {


    @Autowired
    private PaymentService paymentService;


    //create payment
    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(@Valid @RequestBody CreatePaymentRequest request)
    {

        PaymentResponse response=paymentService.createPayment(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }


    //get payment by id
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable Long id)
    {

        PaymentResponse response=paymentService.findPaymentById(id);

        return ResponseEntity.ok(response);

    }


    //get payment by transaction id
    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<PaymentResponse> getPaymentByTransactionId(@PathVariable String transactionId)
    {

        PaymentResponse response=paymentService.findPaymentByTransactionId(transactionId);

        return ResponseEntity.ok(response);

    }


    //get payments by user id
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PaymentResponse>> getPaymentsByUserId(@PathVariable Long userId)
    {

        List<PaymentResponse> responses=paymentService.findPaymentsByUserId(userId);

        return ResponseEntity.ok(responses);

    }

    //get all payments
    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getAllPayments()
    {

        List<PaymentResponse> responses=paymentService.findAllPayments();

        return ResponseEntity.ok(responses);
    }


}
