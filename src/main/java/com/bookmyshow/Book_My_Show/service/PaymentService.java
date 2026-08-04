package com.bookmyshow.Book_My_Show.service;

import com.bookmyshow.Book_My_Show.dto.reponse.PaymentResponse;
import com.bookmyshow.Book_My_Show.dto.request.CreatePaymentRequest;
import com.bookmyshow.Book_My_Show.entity.Booking;
import com.bookmyshow.Book_My_Show.entity.Payment;
import com.bookmyshow.Book_My_Show.exception.InvalidRequestException;
import com.bookmyshow.Book_My_Show.exception.ResourceNotFoundException;
import com.bookmyshow.Book_My_Show.mapper.PaymentMapper;
import com.bookmyshow.Book_My_Show.repo.BookingRepo;
import com.bookmyshow.Book_My_Show.repo.PaymentRepo;
import com.bookmyshow.Book_My_Show.repo.UserRepo;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private UserRepo userRepo;

    //create payment
    @Transactional
    public PaymentResponse createPayment(CreatePaymentRequest request)
    {
        Booking booking=bookingRepo.findById(request.getBookingId())
                .orElseThrow(()-> new ResourceNotFoundException("No booking found with id "+request.getBookingId()));
        if (booking.getPayment()!=null)
        {
            throw new InvalidRequestException("Payment has already been completed for this booking");
        }

        if (!booking.getStatus().equalsIgnoreCase("PENDING")) {
            throw new InvalidRequestException(
                    "Only pending bookings can be paid");
        }

        if ("CANCELLED".equals(booking.getStatus())) {

            throw new InvalidRequestException("Booking has expired");

        }

        BigDecimal amount=booking.getTotalAmount();

        String transactionId="TXN-"+ UUID.randomUUID().toString().substring(0,8).toUpperCase();

        Payment payment=Payment.builder()
                .transactionId(transactionId)
                .amount(amount)
                .paymentMethod(request.getPaymentMethod())
                .paymentTime(LocalDateTime.now())
                .paymentStatus("SUCCESS")
                .booking(booking)
                .build();

        booking.setPayment(payment);
        booking.setStatus("CONFIRMED");

        Payment savedPayment=paymentRepo.save(payment);

        return PaymentMapper.mapResponseToDto(savedPayment);
    }


    //find payment by id
    public PaymentResponse findPaymentById(Long paymentId)
    {
        Payment payment=paymentRepo.findById(paymentId)
                .orElseThrow(()->new ResourceNotFoundException("No payment found with id "+paymentId));

        return PaymentMapper.mapResponseToDto(payment);
    }

    //find payment by transaction id
    public PaymentResponse findPaymentByTransactionId(String transactionId)
    {

        Payment payment=paymentRepo.findByTransactionId(transactionId)
                .orElseThrow(()-> new ResourceNotFoundException("No payment found with transaction id "+transactionId));

        return PaymentMapper.mapResponseToDto(payment);

    }


    //find payments by user id
    public List<PaymentResponse> findPaymentsByUserId(Long userId)
    {
        userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("No user found with id "+userId));

        List<Payment> payments=paymentRepo.findByBookingUserId(userId);

        return payments.stream()
                .map(PaymentMapper::mapResponseToDto)
                .toList();
    }


    //finds all payments
    public List<PaymentResponse> findAllPayments()
    {
        List<Payment> payments=paymentRepo.findAll();

        return payments.stream()
                .map(PaymentMapper::mapResponseToDto)
                .toList();
    }


}
