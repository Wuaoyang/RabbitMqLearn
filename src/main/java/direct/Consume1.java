package direct;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * 订阅模型 - Direct（直连） - 消费者1
 *
 * @author aosun_wu
 * @date 2020-09-26 10:28
 */
public class Consume1 {

	public static void main(String[] args) throws IOException {
		Connection connection = RabbitMQUtils.getConnection();
		Channel channel = connection.createChannel();
		/**
		 * 参数1： 交换机名称
		 * 参数2：交换机类型
		 */
		channel.exchangeDeclare("directExchange", "direct");
		String routerKey = "error";
		// 创建临时队列
		String queue = channel.queueDeclare().getQueue();
		// 将队列和交换机绑定
		channel.queueBind(queue, "directExchange", routerKey);
		// 消费
		channel.basicConsume(queue, true, new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				System.out.println("消费者1：" + new String(body));
			}
		});
	}

}
