package com.bobo.community;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class KafkaTest {
    @Autowired
    private KafkaProducer kafkaProducer;
    @Autowired
    private KafkaConsumer kafkaConsumer;

    @Test
    public void testKafka(){
        kafkaProducer.sendMessage("test", "第一个消息");
        kafkaProducer.sendMessage("test", "第2个消息");
        kafkaProducer.sendMessage("test", "第3个消息");
        kafkaProducer.sendMessage("test", "第4个消息");
        try {
            Thread.sleep(1000l *10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

@Component
class KafkaProducer{
    @Autowired
    private KafkaTemplate kafkaTemplate;
    public void sendMessage(String topic, String content){
        kafkaTemplate.send(topic, content);
    }

}

@Component
class KafkaConsumer{
    @KafkaListener(topics = "test")
    public void handleMessage(ConsumerRecord record){
        System.out.println(record.value());
    }
}
