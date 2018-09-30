package co.com.deiru.rest;

import co.com.deiru.dao.IncidentsDAO;
import co.com.deiru.dto.IncidentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/v1")
public class IncidentRestService {

    @Autowired
    IncidentsDAO incidentsDAO;

    @RequestMapping(value = "/incidents/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IncidentDTO>> getIncidents(){
        List<IncidentDTO> incidents = incidentsDAO.getIncidents();
        return new ResponseEntity<>(incidents, HttpStatus.OK);
    }

    @RequestMapping(value = "/license/validate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> validateLicense(@RequestBody  String license){
        return new ResponseEntity<>("{\"success\" : \"License Validated\"}", HttpStatus.OK);
    }
}
