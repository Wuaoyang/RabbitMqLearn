package spring;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 第一种hello world模型使用
 *
 * @author aosun_wu
 * @date 2020-09-29 23:20
 */
@Component
@RabbitListener(queuesToDeclare = @Queue("springBootHello"))
public class HelloCustomer {

    /**
     * 消费者
     */
    @RabbitHandler
    public void receive1(String message){
        System.out.println("message = " + message);
    }
}
