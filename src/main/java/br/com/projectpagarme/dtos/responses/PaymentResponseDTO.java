package br.com.projectpagarme.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentResponseDTO {

    private String status;

    private LocalDate paymentDate;
}
