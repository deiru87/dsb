package co.com.deiru.service;

import co.com.deiru.dao.IncidentsDAO;
import co.com.deiru.dto.IncidentDTO;
import co.com.deiru.dto.ResponseLicense;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.ZonedDateTime;

@Service
public class IncidentService {

    @Autowired
    IncidentsDAO incidentsDAO;

    @Autowired
    RestTemplate restTemplate;

    @Value("${client.rest.license}")
    private String url;

    private static final Logger log = LoggerFactory.getLogger(IncidentService.class);

    @KafkaListener(topics = "test")
    public void saveIncident(@Payload String message) throws  Exception {
        log.info("received message='{}'", message);

        String[] arrString = message.split("\r\n");

        String val = restTemplate.postForObject(url, arrString[3], String.class);


        if(val!=null && val.contains("success")){
            IncidentDTO incidentDTO = new IncidentDTO();
            incidentDTO.setTypeIncident(arrString[0].contains("7") ? "phishing" : "malware");
            incidentDTO.setDateIncident(ZonedDateTime.now());
            incidentDTO.setDateIncidentGroup(ZonedDateTime.now());

            incidentsDAO.saveIncident(incidentDTO);
        }


    }
}
