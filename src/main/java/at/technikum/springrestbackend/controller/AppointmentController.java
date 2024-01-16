package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.dto.appointment.CreateAppointmentRequest;
import at.technikum.springrestbackend.model.Appointment;
import at.technikum.springrestbackend.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Appointment> createAppointment(@RequestBody CreateAppointmentRequest request) {
        return appointmentService.createAppointment(
                request.getLawyerId(),
                request.getUserId(),
                request.getDate(),
                request.getTime()
        );
    }
}
