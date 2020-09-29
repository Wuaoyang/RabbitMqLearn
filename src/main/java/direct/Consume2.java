package direct;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * 消费者2
 *
 * @author aosun_wu
 * @date 2020-09-27 22:31
 */
public class Consume2 {

	public static void main(String[] args) throws IOException {
		Connection connection = RabbitMQUtils.getConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare("directExchange", "direct");
		String queue = channel.queueDeclare().getQueue();
		channel.queueBind(queue, "directExchange", "info");
		channel.basicConsume(queue, true, new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				System.out.println("消费者2" + new String(body));
			}
		});
	}

}
