package be.kuleuven.boekhoudenassistant.repositories;

import be.kuleuven.boekhoudenassistant.dto.BkpfDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BkpfRepository {
    private final JdbcTemplate template;
    private final RowMapper<BkpfDto> bkpfDtoMapper = (result, rowNum) -> new BkpfDto(result.getString("GJAHR"), result.getString("BUKRS"), result.getString("BELNR"));

    public BkpfRepository(JdbcTemplate template) {
        this.template = template;
    }

    public List<BkpfDto> getAllBkpf() {
        var sql = """
                select GJAHR, BUKRS, BELNR
                from BKPF
                """;
        return template.query(sql, bkpfDtoMapper);
    }

}
