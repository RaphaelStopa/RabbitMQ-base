package com.example.consumer.one;

import com.example.demo.constant.RabbitmqConstant;
import com.example.demo.dto.OneDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OneConsumer {

    @RabbitListener(queues = RabbitmqConstant.QUEUE_ONE)
    private void consumer(OneDto oneDto){
        System.out.println(oneDto.amount);
        System.out.println(oneDto.code);
        System.out.println("==========================");
    }
}
