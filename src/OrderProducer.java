import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;

public class OrderProducer {

    public static void main(String[] args) {

        Properties props = new Properties();

        props.put(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
            "localhost:9092"
        );

        props.put(
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
            "org.apache.kafka.common.serialization.StringSerializer"
        );

        props.put(
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
            "org.apache.kafka.common.serialization.StringSerializer"
        );

        KafkaProducer<String, String> producer =
            new KafkaProducer<>(props);

        String jsonMessage =
            "{\"orderId\":1001,"
            + "\"customer\":\"Mior\","
            + "\"product\":\"Laptop\","
            + "\"amount\":3500}";

        ProducerRecord<String, String> record =
            new ProducerRecord<>(
                "orders",
                "order-1001",
                jsonMessage
            );

        producer.send(record);

        System.out.println("Message sent to Kafka!");

        producer.close();
    }
}