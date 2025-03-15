package org.example.invocationapi.controller;

import com.example.gatchaapi.model.Monster;
import org.example.invocationapi.service.InvocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invocation")
public class InvocationController {
    @Autowired
    private InvocationService invocationService;

    @PostMapping("/invoke")
    public ResponseEntity<Monster> invokeMonster() {
        Monster monster = invocationService.invokeMonster();
        if (monster != null) {
            return ResponseEntity.ok(monster);
        } else {
            return ResponseEntity.status(500).build();
        }
    }
}