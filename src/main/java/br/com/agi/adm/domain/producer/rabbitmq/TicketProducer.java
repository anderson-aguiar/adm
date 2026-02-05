package br.com.agi.adm.domain.producer.rabbitmq;

import br.com.agi.adm.domain.dto.request.TicketRequestDTO;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class TicketProducer {

    private final StreamBridge streamBridge;

    public TicketProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void publish(TicketRequestDTO dto) {
        streamBridge.send("ticketProducer-out-0", dto);
    }

}
