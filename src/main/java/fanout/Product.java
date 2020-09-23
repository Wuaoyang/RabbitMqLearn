package fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * 生产者
 * @author aosun_wu
 * @date 2020-09-23 23:54
 */
public class Product {

    /**
     * 生产消息
     */
	public static void main(String[] args) throws IOException {
		Connection connection = RabbitMQUtils.getConnection();
		Channel channel = connection.createChannel();
		// 第一个参数是交换机名称；第二个参数是广播固定String
		channel.exchangeDeclare("logs", "fanout");
		channel.basicPublish("logs", "", null, "hello Fanout".getBytes());
		RabbitMQUtils.closeConnectionAndChanel(channel, connection);
	}

}
