package com.bookmyshow.Book_My_Show.repo;

import com.bookmyshow.Book_My_Show.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepo extends JpaRepository<Payment,Long> {


    Optional<Payment> findByTransactionId(String transactionId);

    Optional<Payment> findByBookingId(Long bookingId);

    List<Payment> findByBookingUserId(Long userId);

}
