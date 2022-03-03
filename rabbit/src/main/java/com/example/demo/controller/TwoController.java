package com.example.demo.controller;

import com.example.demo.constant.RabbitmqConstant;
import com.example.demo.dto.TwoDto;
import com.example.demo.service.RabbitmqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("two")
public class TwoController {

    @Autowired
    private RabbitmqService rabbitmqService;

    @PutMapping
    private ResponseEntity putTwo(@RequestBody TwoDto twoDto) {
        this.rabbitmqService.sendMessage(RabbitmqConstant.QUEUE_TWO, twoDto);
        return new ResponseEntity(HttpStatus.OK);

    }
}
