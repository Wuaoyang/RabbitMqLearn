import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 直接连接模型
 * @author aosun_wu
 * @date 2020-09-19 19:52
 */
public class DirectConnection {

    public static void main(String[] args) throws IOException, TimeoutException {
        produce();
        consume();
    }

    /**
     * 生产消息
     */
    public static void produce() throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("39.96.222.253");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");
        connectionFactory.setVirtualHost("/ems");
        // 创建连接
        Connection connection = connectionFactory.newConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 通道绑定队列
        /**
         * 	 '参数1':用来声明通道对应的队列
         *   '参数2':用来指定是否持久化队列
         *   '参数3':用来指定是否独占队列
         *   '参数4':用来指定是否自动删除队列
         *   '参数5':对队列的额外配置
         */
        channel.queueDeclare("hello",true,false,false,null);
        /**
         * 參數1：交换机名称
         * 参数2：队列名称
         * 参数3：传递消息额外机制
         * 参数4：消息的具体内容
         */
        channel.basicPublish("","hello",null,"hellow rabbitmq".getBytes());
        channel.close();
        connection.close();
    }

	/**
	 * 消费消息
	 */
	public static void consume() throws IOException, TimeoutException {
		// 创建连接工厂
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("39.96.222.253");
		connectionFactory.setPort(5672);
		connectionFactory.setUsername("ems");
		connectionFactory.setPassword("123");
		connectionFactory.setVirtualHost("/ems");
		// 创建连接
		Connection connection = connectionFactory.newConnection();
		// 创建通道
		Channel channel = connection.createChannel();
        /**
         * 	 '参数1':用来声明通道对应的队列
         *   '参数2':用来指定是否持久化队列
         *   '参数3':用来指定是否独占队列
         *   '参数4':用来指定是否自动删除队列
         *   '参数5':对队列的额外配置
         **/
		channel.queueDeclare("hello", true, false, false, null);
		channel.basicConsume("hello", true, new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				System.out.println(new String(body));
			}
		});

	}



}
