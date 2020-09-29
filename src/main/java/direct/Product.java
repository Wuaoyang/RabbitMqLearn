package direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * 订阅模型 - Direct（直连） - 生产者
 *
 * @author aosun_wu
 * @date 2020-09-26 10:28
 */
public class Product {

	public static void main(String[] args) throws IOException {
		Connection connection = RabbitMQUtils.getConnection();
		Channel channel = connection.createChannel();
		/**
		 * 参数1： 交换机名称
		 * 参数2：交换机类型
		 */
		channel.exchangeDeclare("directExchange", "direct");
		String routerKey = "error";
		// 发布消息 （可以看到第二个消息，原本填入队列的地方，可以放入routerKey）
		channel.basicPublish("directExchange", routerKey, null, ("指定的routerKey:" + routerKey + "的消息").getBytes());
		RabbitMQUtils.closeConnectionAndChanel(channel, connection);
	}

}
