package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.model.PaymentMethod;
import at.technikum.springrestbackend.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/payment-methods")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    // Get all payment methods
    @GetMapping
    public ResponseEntity<List<PaymentMethod>> getAllPaymentMethods() {
        return new ResponseEntity<>(paymentMethodRepository.findAll(), HttpStatus.OK);
    }

    // Get a specific payment method by ID
    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethod> getPaymentMethodById(@PathVariable UUID id) {
        Optional<PaymentMethod> paymentMethod = paymentMethodRepository.findById(id);
        if (paymentMethod.isPresent()) {
            return new ResponseEntity<>(paymentMethod.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Create a new payment method
    @PostMapping
    public ResponseEntity<PaymentMethod> createPaymentMethod(@RequestBody PaymentMethod paymentMethod) {
        paymentMethod.setId(UUID.randomUUID());  // Generating a new UUID for the payment method
        PaymentMethod savedPaymentMethod = paymentMethodRepository.save(paymentMethod);
        return new ResponseEntity<>(savedPaymentMethod, HttpStatus.CREATED);
    }

    // Update an existing payment method
    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethod> updatePaymentMethod(@PathVariable UUID id, @RequestBody PaymentMethod paymentMethod) {
        if (!paymentMethodRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        paymentMethod.setId(id);
        PaymentMethod updatedPaymentMethod = paymentMethodRepository.save(paymentMethod);
        return new ResponseEntity<>(updatedPaymentMethod, HttpStatus.OK);
    }

    // Delete a payment method
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentMethod(@PathVariable UUID id) {
        if (!paymentMethodRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        paymentMethodRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
