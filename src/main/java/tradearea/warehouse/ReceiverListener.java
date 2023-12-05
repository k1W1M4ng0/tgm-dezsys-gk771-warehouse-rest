package tradearea.warehouse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import tradearea.WarehouseApplication;

@Component
public class ReceiverListener implements MessageListener{
    Logger logger = LoggerFactory.getLogger(ReceiverListener.class);

    @Override
    public void onMessage(Message message) {
        logger.info("received ack message");
        try {
            // get the message as text
            TextMessage textMessage = (TextMessage)message;
            String text = textMessage.getText();

            // if the message is for this warehouse
            if(text.startsWith(WarehouseApplication.warehouseApplicationID)) {
                // get the id that was accepted
                String warehouseID = text.split(";")[1];
                WarehouseService.acknowledgeSentMessage(warehouseID);
                logger.info("accepted by central: " + warehouseID);

                // ack the message
                // textMessage.acknowledge();
            }

        }
        catch(Exception ex) {
            logger.error(ex.toString());
        }
    }
    
}
