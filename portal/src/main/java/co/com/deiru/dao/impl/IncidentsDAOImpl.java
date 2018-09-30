package co.com.deiru.dao.impl;

import co.com.deiru.dao.IncidentsDAO;
import co.com.deiru.dto.IncidentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository(value = "incidentsDAO")
public class IncidentsDAOImpl implements IncidentsDAO {

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<IncidentDTO> getIncidents() {

        return jdbcTemplate.query("select * from incidents", new RowMapper<IncidentDTO>() {
            @Override
            public IncidentDTO mapRow(ResultSet resultSet, int i) throws SQLException {
                IncidentDTO incidentDTO = new IncidentDTO();
                incidentDTO.setTypeIncident(resultSet.getString(4).trim());
                incidentDTO.setIncidentDate(resultSet.getString(2));
                return incidentDTO;
            }
        });
    }

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
