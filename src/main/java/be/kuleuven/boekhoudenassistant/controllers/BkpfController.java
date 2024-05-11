package be.kuleuven.boekhoudenassistant.controllers;

import be.kuleuven.boekhoudenassistant.dto.BkpfDto;
import be.kuleuven.boekhoudenassistant.services.BkpfService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bkpf")
public class BkpfController {
    private final BkpfService bkpfService;

    public BkpfController(BkpfService bkpfService) {
        this.bkpfService = bkpfService;
    }

    @GetMapping
    public BkpfDto getAllBkpf() {
        return bkpfService.getAllBkpf();
    }
}
