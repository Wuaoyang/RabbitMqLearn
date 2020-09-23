package workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * WorkQueue（生产者 → 队列 → 多个消费者）
 * @author aosun_wu
 * @date 2020-09-20 22:45:14
 */
public class Product {

    /**
     * 生产消息
     */
	public static void main(String[] args) throws IOException {
		Connection connection = RabbitMQUtils.getConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare("hello", true, false, false, null);
		for (int i = 0; i < 2; i++) {
			channel.basicPublish("", "hello", null, (i + "====>:我是消息").getBytes());
		}
		RabbitMQUtils.closeConnectionAndChanel(channel, connection);
	}

}
