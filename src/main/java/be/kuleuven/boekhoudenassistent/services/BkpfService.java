package be.kuleuven.boekhoudenassistent.services;

import be.kuleuven.boekhoudenassistent.domain.Bkpf;
import be.kuleuven.boekhoudenassistent.dto.BkpfFilter;
import be.kuleuven.boekhoudenassistent.dto.DetailsResult;
import be.kuleuven.boekhoudenassistent.dto.FilterResult;
import be.kuleuven.boekhoudenassistent.repositories.BkpfRepository;
import be.kuleuven.boekhoudenassistent.repositories.BsegReposity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BkpfService {
    private final BkpfRepository bkpfRepository;
    private final BsegReposity bsegReposity;

    public BkpfService(BkpfRepository bkpfRepository, BsegReposity bsegReposity) {
        this.bkpfRepository = bkpfRepository;
        this.bsegReposity = bsegReposity;
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

    public List<FilterResult> getAllBkpfWhereWithDetails(BkpfFilter filter) {
        var coll = getAllBkpfWhere(filter);
        var result = new ArrayList<FilterResult>();
        for (var bkpf : coll) {
            var detailResult = new ArrayList<DetailsResult>();
            var bsegDtoList = bsegReposity.getBsegWhereGjahr_Bukrs_Belnr(bkpf.getGJAHR(), bkpf.getBUKRS(), bkpf.getBELNR());
            for (var bsegDto : bsegDtoList) {
                detailResult.add(new DetailsResult(bkpf.getBUKRS(), bkpf.getGJAHR(), bkpf.getBELNR(), bkpf.getBLART(), bkpf.getBLDAT(), bsegDto.BUZEI(), bsegDto.BSCHL(), bsegDto.AUGDT()));
            }
            result.add(new FilterResult(bkpf, detailResult));
        }
        return result;
    }
}
