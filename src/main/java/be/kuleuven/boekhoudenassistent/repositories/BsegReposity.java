package be.kuleuven.boekhoudenassistent.repositories;

import be.kuleuven.boekhoudenassistent.dto.BsegDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BsegReposity {
    private final JdbcTemplate template;
    private final RowMapper<BsegDto> bsegDtoRowMapper = (rs, rowNum) -> new BsegDto(
            rs.getString("BUZEI"),
            rs.getString("BSCHL"),
            rs.getDate("AUGDT").toLocalDate()
    );

    public BsegReposity(JdbcTemplate template) {
        this.template = template;
    }

    public List<BsegDto> getBsegWhereGjahr_Bukrs_Belnr(String gjahr, String bukrs, String belnr) {
        var sql = """
                select BUZEI, BSCHL, AUGDT
                from BSEG
                where GJAHR = ? and BUKRS = ? and BELNR = ?
                """;
        return template.query(sql, bsegDtoRowMapper, gjahr, bukrs, belnr);
    }
}
