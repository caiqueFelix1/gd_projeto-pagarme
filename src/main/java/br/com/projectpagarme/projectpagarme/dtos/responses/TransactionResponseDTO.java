package br.com.projectpagarme.projectpagarme.dtos.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionResponseDTO {

    private BigDecimal transactionValue;

    private String transactionDescription;

    private String typePayment;

    private String cardNumber;

    private String cardOwnerName;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date dateExpirationCard;

    private String cvv;

    private PaymentResponseDTO payment;
}
