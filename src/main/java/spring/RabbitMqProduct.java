package spring;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 使用springboot的方式来集成使用RabbitMQ —— 生产者
 *
 * @author aosun_wu
 * @date 2020-09-29 23:20
 */
@SpringBootTest(classes = RabbitmqSrpingbootApplication.class)
@RunWith(SpringRunner.class)
public class RabbitMqProduct {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 第一种hello world模型使用
     */
    @Test
    public void testHello(){
        rabbitTemplate.convertAndSend("springBootHello","hello world");
    }

    /**
     * 第二种work模型使用
     */
    @Test
    public void testWork(){
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work","hello work!");
        }
    }

    /**
     * 第三种广播fanout模型使用
     */
    @Test
	public void testFanout() {
        rabbitTemplate.convertAndSend("logs","","这是日志广播");
    }

    /**
     * 第四种direct模型使用
     */
    @Test
    public void testDirect(){
        rabbitTemplate.convertAndSend("directs","error","error 的日志信息");
    }

    /**
     * 第五种订阅 topic 模型使用
     */
    @Test
    public void testTopic(){
        rabbitTemplate.convertAndSend("topics","user.save.findAll","user.save.findAll 的消息");
    }






}
