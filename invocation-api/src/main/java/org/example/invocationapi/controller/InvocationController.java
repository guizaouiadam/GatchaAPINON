package org.example.invocationapi.controller;

import org.example.invocationapi.model.Invocation;
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
    public ResponseEntity<Invocation> invokeMonster(@RequestHeader("Authorization") String token) {
        Invocation invocation = invocationService.invokeMonster(token);
        return ResponseEntity.ok(invocation);
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }
}