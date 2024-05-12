package be.kuleuven.boekhoudenassistent.dto;

import be.kuleuven.boekhoudenassistent.domain.Bkpf;

import java.util.ArrayList;

public record FilterResult(Bkpf bkpf, ArrayList<DetailsResult> detailsResults) {
}
