package co.com.deiru.dao.impl;

import co.com.deiru.dao.IncidentsDAO;
import co.com.deiru.dto.IncidentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Repository(value = "incidentsDAO")
public class IncidentsDAOImpl implements IncidentsDAO {

    private JdbcTemplate jdbcTemplate;


    @Override
    public void saveIncident(IncidentDTO incidentDTO) {


        boolean isExecuted = jdbcTemplate.execute("insert into incidents values ( nextval('seq_incident_id'), ?, ?, ?)",new PreparedStatementCallback<Boolean>(){
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {

                ps.setString(1, incidentDTO.getDateIncident().toLocalDateTime().toString());
                ps.setString(2, incidentDTO.getDateIncidentGroup().toLocalDateTime().toString());
                ps.setString(3, incidentDTO.getTypeIncident());

                return ps.execute();

            }
        });

    }


    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

}
