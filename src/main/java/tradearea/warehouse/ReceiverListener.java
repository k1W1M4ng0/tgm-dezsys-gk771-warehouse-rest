package tradearea.warehouse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import tradearea.WarehouseApplication;

public class ReceiverListener implements MessageListener{
    Logger logger = LoggerFactory.getLogger(ReceiverListener.class);

    @Autowired
    private WarehouseService service;

    @Override
    public void onMessage(Message message) {
        try {
            // get the message as text
            TextMessage textMessage = (TextMessage)message;
            String text = textMessage.getText();

            // if the message is for this warehouse
            if(text.startsWith(WarehouseApplication.warehouseApplicationID)) {
                // get the id that was accepted
                String warehouseID = text.split(";")[1];
                service.acknowledgeSentMessage(warehouseID);
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
