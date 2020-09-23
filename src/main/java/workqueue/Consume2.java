package workqueue;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * 消费者2
 * @author aosun_wu
 * @date 2020-09-22 22:38
 */
public class Consume2 {

    /**
	 * 消费者2
	 */
	public static void main(String[] args) throws IOException {
		Connection connection = RabbitMQUtils.getConnection();
        final Channel channel = connection.createChannel();
		channel.queueDeclare("hello", true, false, false, null);
        channel.basicQos(2);//一次只接受一条未确认的消息
        channel.basicConsume("hello", false, new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				try {
					Thread.sleep(100000); // 处理消息比较慢 一秒处理一个消息
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("消费者2: " + new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);//手动确认消息
            }
		});
	}

}
