package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.model.PaymentMethod;
import at.technikum.springrestbackend.service.PaymentMethodService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/payment-methods")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    // Get all payment methods
    @GetMapping
    public ResponseEntity<List<PaymentMethod>> getAllPaymentMethods() {
        return paymentMethodService.getAllPaymentMethods();
    }

    // Get a specific payment method by ID
    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethod> getPaymentMethodById(@PathVariable UUID id) {
        return paymentMethodService.getPaymentMethodById(id);
    }

    // Create a new payment method
    @PostMapping
    public ResponseEntity<PaymentMethod> createPaymentMethod(@RequestBody PaymentMethod paymentMethod) {
        return paymentMethodService.createPaymentMethod(paymentMethod);
    }

    // Update an existing payment method
    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethod> updatePaymentMethod(@PathVariable UUID id, @RequestBody PaymentMethod paymentMethod) {
        return paymentMethodService.updatePaymentMethod(id, paymentMethod);
    }

    // Delete a payment method
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentMethod(@PathVariable UUID id) {
        return paymentMethodService.deletePaymentMethod(id);
    }
}
