package utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

/**
 * 工具类
 *
 * @author aosun_wu
 * @date 2020-09-20 0:45
 */
public class RabbitMQUtils {

    private static ConnectionFactory connectionFactory;
    private static Properties properties;
    static {
        // 重量级资源 类加载执行只执行一次
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("39.96.222.253");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");
        connectionFactory.setVirtualHost("/ems");
    }

    /**
     * 定义提供连接对象的方法
     */
    public static Connection getConnection(){
        try {
            return connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭通道和关闭连接工具方法
     */
    public static void closeConnectionAndChanel(Channel channel,Connection conn){
        try {
            if (channel!=null){
                channel.close();
            }
            if (conn!=null){
                conn.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

}
