package br.com.projectpagarme.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BalanceResponseDTO {

    private BigDecimal waitingFunds;

    private BigDecimal available;
}
