package br.com.projectpagarme.projectpagarme.dtos.responses;

import br.com.projectpagarme.projectpagarme.enums.PaymentMapperEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

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
