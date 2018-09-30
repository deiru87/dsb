package co.com.deiru.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/v1")
@Service
public class ReceiverRestService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${topic.kafka}")
    private String topic;

    private static final Logger log = LoggerFactory.getLogger(ReceiverRestService.class);

    @RequestMapping(value = "/receiver/incident", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> receiveMessage(@RequestBody String message){
        log.info("Message received = '{}'" + message);
        kafkaTemplate.send(topic, message);
        return new ResponseEntity<>("{\"success\" : \"Message Received successful\"}", HttpStatus.OK);
    }
}
