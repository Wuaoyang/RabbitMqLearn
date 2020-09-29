package topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * 订阅模式 topic 生产者
 *
 * @author aosun_wu
 * @date 2020-09-28 23:34
 */
public class Product {

	public static void main(String[] args) throws IOException {
		Connection connection = RabbitMQUtils.getConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare("topicExchange", "topic");
//		channel.basicPublish("topicExchange", "user.save", null, "topic user.save".getBytes());
		channel.basicPublish("topicExchange", "#user.gg.gg", null, "#topic .gg.gg".getBytes());
		RabbitMQUtils.closeConnectionAndChanel(channel, connection);
	}

}
