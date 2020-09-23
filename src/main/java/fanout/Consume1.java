package fanout;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * 消费者1
 *
 * @author aosun_wu
 * @date 2020-09-23 23:56
 */
public class Consume1 {

    /**
     * 消费消息
     */
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        // 指定交换机
        channel.exchangeDeclare("logs","fanout");
        // 创建临时队列
        String queue = channel.queueDeclare().getQueue();
        // 将临时队列和交换机绑定
        channel.queueBind(queue,"logs","");
        // 处理消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				System.out.println("消费者1: " + new String(body));
            }
        });
    }

}
