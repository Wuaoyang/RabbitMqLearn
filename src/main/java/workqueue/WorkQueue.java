package workqueue;

import com.rabbitmq.client.*;
import org.junit.Test;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * WorkQueue（生产者 → 队列 → 多个消费者）
 * @author aosun_wu
 * @date 2020-09-20 22:45:14
 */
public class WorkQueue {

    public static void main(String[] args) throws IOException, TimeoutException {
        produce();
        consume2();
        consume1();
    }

    /**
     * 生产消息
     */
    @Test
	public static void produce() throws IOException, TimeoutException {
		Connection connection = RabbitMQUtils.getConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare("hello", true, false, false, null);
		for (int i = 0; i < 10; i++) {
			channel.basicPublish("", "hello", null, (i + "====>:我是消息").getBytes());
		}
		RabbitMQUtils.closeConnectionAndChanel(channel, connection);
	}

	/**
	 * 消费者1
	 */
	@Test
	public static void consume1() throws IOException, TimeoutException {
		Connection connection = RabbitMQUtils.getConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare("hello", true, false, false, null);
		channel.basicConsume("hello", true, new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				System.out.println("消费者1: " + new String(body));
			}
		});
	}

	/**
	 * 消费者2
	 */
	public static void consume2() throws IOException, TimeoutException {
		Connection connection = RabbitMQUtils.getConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare("hello", true, false, false, null);
		channel.basicConsume("hello", true, new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				try {
					Thread.sleep(1000); // 处理消息比较慢 一秒处理一个消息
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("消费者2: " + new String(body));
			}
		});
	}



}
