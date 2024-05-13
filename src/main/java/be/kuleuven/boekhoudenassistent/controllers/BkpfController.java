package be.kuleuven.boekhoudenassistent.controllers;

import be.kuleuven.boekhoudenassistent.domain.Bkpf;
import be.kuleuven.boekhoudenassistent.dto.BkpfFilter;
import be.kuleuven.boekhoudenassistent.dto.DetailsResult;
import be.kuleuven.boekhoudenassistent.dto.FilterResult;
import be.kuleuven.boekhoudenassistent.services.BkpfService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("bkpf")
public class BkpfController {
    private final BkpfService bkpfService;

    public BkpfController(BkpfService bkpfService) {
        this.bkpfService = bkpfService;
    }

    @GetMapping
    public List<Bkpf> getAllBkpf() {
        return bkpfService.getAllBkpf();
    }

    @PostMapping("/filter")
    public List<Bkpf> getAllBkpfWhere(@RequestBody BkpfFilter filter) {
        return bkpfService.getAllBkpfWhere(filter);
    }

    @PostMapping("/filter/details")
    public List<FilterResult> getAllBkpfWhereWithDetails(@RequestBody BkpfFilter filter) {
        return bkpfService.getAllBkpfWhereWithDetails(filter);
    }

}
