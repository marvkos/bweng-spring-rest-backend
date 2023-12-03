package at.technikum.springrestbackend.service;

import at.technikum.springrestbackend.model.PaymentMethod;
import at.technikum.springrestbackend.repository.PaymentMethodRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    public ResponseEntity<List<PaymentMethod>> getAllPaymentMethods() {
        List<PaymentMethod> paymentMethods = paymentMethodRepository.findAll();
        return ResponseEntity.ok(paymentMethods);
    }

    public ResponseEntity<PaymentMethod> getPaymentMethodById(UUID id) {
        Optional<PaymentMethod> paymentMethod = paymentMethodRepository.findById(id);
        if (paymentMethod.isPresent()) {
            return new ResponseEntity<>(paymentMethod.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<PaymentMethod> createPaymentMethod(PaymentMethod paymentMethod) {
        paymentMethod.setId(UUID.randomUUID());  // Generating a new UUID for the payment method
        PaymentMethod savedPaymentMethod = paymentMethodRepository.save(paymentMethod);
        return new ResponseEntity<>(savedPaymentMethod, HttpStatus.CREATED);
    }

    public ResponseEntity<PaymentMethod> updatePaymentMethod(UUID id, PaymentMethod paymentMethod) {
        if (!paymentMethodRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        paymentMethod.setId(id);
        PaymentMethod updatedPaymentMethod = paymentMethodRepository.save(paymentMethod);
        return new ResponseEntity<>(updatedPaymentMethod, HttpStatus.OK);
    }

    public ResponseEntity<Void> deletePaymentMethod(UUID id) {
        if (!paymentMethodRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        paymentMethodRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
