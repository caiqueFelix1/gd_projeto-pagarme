package br.com.projectpagarme.dtos.responses;

import br.com.projectpagarme.enums.PaymentMapperEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionResponseDTO {

    private Long id;

    private BigDecimal transactionValue;

    private String transactionDescription;

    private PaymentMapperEnum typePayment;

    private String cardNumber;

    private String cardOwnerName;

    private String dateExpirationCard;

    private PaymentResponseDTO payment;
}
