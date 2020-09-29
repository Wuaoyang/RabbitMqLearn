package topic;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * 消费者1
 *
 * @author aosun_wu
 * @date 2020-09-28 23:38
 */
public class Consumer1 {

    public static void main(String[] args) throws IOException {

        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("topicExchange","topic");
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue,"topicExchange","user.save");
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				System.out.println("消费者1 {" + new String(body) + "}");
            }
        });

    }

}
