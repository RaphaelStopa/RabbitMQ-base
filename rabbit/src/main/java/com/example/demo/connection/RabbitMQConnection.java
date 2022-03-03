package com.example.demo.connection;

import com.example.demo.constant.RabbitmqConstant;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Queue;

import javax.annotation.PostConstruct;

@Component
public class RabbitMQConnection {
    private static final String NAME_EXCHANGE = "amq.direct";

    private AmqpAdmin amqpAdmin;

    public  RabbitMQConnection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue row(String nameQueue) {
        return new Queue(nameQueue, true, false, false);
    }

    private DirectExchange replacement(){
        return new DirectExchange(NAME_EXCHANGE);
    }

    private Binding relationship(Queue queue, DirectExchange replacement) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, replacement.getName(), queue.getName(), null);
    }

    @PostConstruct
    private void add() {
        Queue queueOne = this.row(RabbitmqConstant.QUEUE_ONE);
        Queue queueTwo = this.row(RabbitmqConstant.QUEUE_TWO);

        DirectExchange replacement = this.replacement();

        Binding linkOne = this.relationship(queueOne, replacement);
        Binding linkTwo = this.relationship(queueTwo, replacement);

        this.amqpAdmin.declareQueue(queueOne);
        this.amqpAdmin.declareQueue(queueTwo);

        this.amqpAdmin.declareExchange(replacement);
        this.amqpAdmin.declareBinding(linkOne);
        this.amqpAdmin.declareBinding(linkTwo);
    }
}
