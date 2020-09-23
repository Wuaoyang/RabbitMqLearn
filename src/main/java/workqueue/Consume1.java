package workqueue;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * 消费者1
 * @author aosun_wu
 * @date 2020-09-22 22:38
 */
public class Consume1 {

    /**
     * 消费者1
     */
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        final Channel channel = connection.createChannel();
		channel.queueDeclare("hello", true, false, false, null);
		channel.basicQos(2);// 一次只接受一条未确认的消息
		channel.basicConsume("hello", false, new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				System.out.println("消费者1: " + new String(body));
				channel.basicAck(envelope.getDeliveryTag(), false);// 手动确认消息
			}
		});
    }

}
