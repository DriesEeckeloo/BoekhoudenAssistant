package be.kuleuven.boekhoudenassistent.repositories;

import be.kuleuven.boekhoudenassistent.domain.Bkpf;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BkpfRepository {
    private final JdbcTemplate template;
    private final RowMapper<Bkpf> bkpfMapper = (rs, rowNum) -> new Bkpf(
            rs.getString("BUKRS"),
            rs.getString("BELNR"),
            rs.getString("GJAHR"),
            rs.getString("BLART"),
            rs.getDate("BLDAT").toLocalDate(),
            rs.getDate("BUDAT").toLocalDate(),
            rs.getString("MONAT"),
            rs.getDate("CPUDT").toLocalDate(),
            rs.getTime("CPUTM").toLocalTime()
    );
    public BkpfRepository(JdbcTemplate template) {
        this.template = template;
    }

    public List<Bkpf> getAllBkpf() {
        var sql = """
                select Bukrs, Belnr, Gjahr, Blart, Bldat, Budat, Monat, Cpudt, Cputm
                from BKPF
                order by 1, 2
                """;
        return template.query(sql, bkpfMapper);
    }

}
