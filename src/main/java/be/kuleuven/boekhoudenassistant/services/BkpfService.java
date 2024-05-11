package be.kuleuven.boekhoudenassistant.services;

import be.kuleuven.boekhoudenassistant.dto.BkpfDto;
import be.kuleuven.boekhoudenassistant.repositories.BkpfRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BkpfService {
    private final BkpfRepository bkpfRepository;

    public BkpfService(BkpfRepository bkpfRepository) {
        this.bkpfRepository = bkpfRepository;
    }

    public BkpfDto getAllBkpf() {
        return bkpfRepository.getAllBkpf();
    }
}
