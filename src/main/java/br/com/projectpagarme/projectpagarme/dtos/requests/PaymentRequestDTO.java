package br.com.projectpagarme.projectpagarme.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentRequestDTO {

    private String status;

    private LocalDate paymentDate;
}
