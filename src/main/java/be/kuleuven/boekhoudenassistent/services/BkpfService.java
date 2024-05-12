package be.kuleuven.boekhoudenassistent.services;

import be.kuleuven.boekhoudenassistent.domain.Bkpf;
import be.kuleuven.boekhoudenassistent.dto.BkpfFilter;
import be.kuleuven.boekhoudenassistent.repositories.BkpfRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BkpfService {
    private final BkpfRepository bkpfRepository;

    public BkpfService(BkpfRepository bkpfRepository) {
        this.bkpfRepository = bkpfRepository;
    }

    public List<Bkpf> getAllBkpf() {
        return bkpfRepository.getAllBkpf();
    }

    public List<Bkpf> getAllBkpfWhere(BkpfFilter filter) {
        var coll = getAllBkpf();
        if (!filter.GJAHR().trim().isEmpty()){
            coll.removeIf(Bkpf -> !Bkpf.getGJAHR().equals(filter.GJAHR()));
        }
        if (!filter.BUKRS().trim().isEmpty()){
            coll.removeIf(Bkpf -> !Bkpf.getBUKRS().equals(filter.BUKRS()));
        }
        if (!filter.BELNR1().trim().isEmpty() && !filter.BELNR2().trim().isEmpty()){
            coll.removeIf(Bkpf -> Bkpf.getBELNR().compareTo(filter.BELNR1()) < 0);
            coll.removeIf(Bkpf -> Bkpf.getBELNR().compareTo(filter.BELNR2()) > 0);
        }
        return coll;
    }
}
