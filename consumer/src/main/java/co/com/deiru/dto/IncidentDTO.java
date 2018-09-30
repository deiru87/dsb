package co.com.deiru.dto;

import java.time.ZonedDateTime;

public class IncidentDTO {

    private ZonedDateTime dateIncident;
    private ZonedDateTime dateIncidentGroup;
    private String typeIncident;

    public ZonedDateTime getDateIncident() {
        return dateIncident;
    }

    public void setDateIncident(ZonedDateTime dateIncident) {
        this.dateIncident = dateIncident;
    }

    public ZonedDateTime getDateIncidentGroup() {
        return dateIncidentGroup;
    }

    public void setDateIncidentGroup(ZonedDateTime dateIncidentGroup) {
        this.dateIncidentGroup = dateIncidentGroup;
    }

    public String getTypeIncident() {
        return typeIncident;
    }

    public void setTypeIncident(String typeIncident) {
        this.typeIncident = typeIncident;
    }
}
