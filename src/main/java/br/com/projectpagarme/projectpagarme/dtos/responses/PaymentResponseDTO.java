package br.com.projectpagarme.projectpagarme.dtos.responses;

import br.com.projectpagarme.projectpagarme.enums.PaymentMapperEnum;
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
