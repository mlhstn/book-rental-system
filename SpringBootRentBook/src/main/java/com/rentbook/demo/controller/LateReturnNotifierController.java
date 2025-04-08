package com.rentbook.demo.controller;

import com.rentbook.demo.busines.abstracts.ILateReturnNotifierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test/notifications")
public class LateReturnNotifierController {

    private final ILateReturnNotifierService notifierService;

    public LateReturnNotifierController(ILateReturnNotifierService notifierService) {
        this.notifierService = notifierService;
    }

    @GetMapping("/send-late-return-emails")
    public ResponseEntity<String> sendLateReturnEmailsManually() {
        notifierService.notifyLateReturns();
        return ResponseEntity.ok("Geciken kiralamalar için e-posta gönderimi tetiklendi.");
    }
}
