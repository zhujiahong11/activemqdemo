package test;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Myproducer {
    private static final String ACTIVEMQ_URL = "tcp://47.113.123.15:61616";
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("myQueue");
        MessageProducer producer = session.createProducer(destination);
        for (int i=1;i<=10;i++){
            TextMessage message = session.createTextMessage("第" + i + "个文本消息");
            producer.send(message);
            System.out.println("已发送的消息："+message.getText());
        }
        connection.close();

    }


}
