package br.com.projectpagarme.projectpagarme.dtos.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionRequestDTO {

    private BigDecimal transactionValue;

    @NotBlank(message = "{blank.field}")
    @Size(min = 3, max = 50, message = "{invalid.size}")
    private String transactionDescription;

    private String typePayment;

    private String cardNumber;

    private String cardOwnerName;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date dateExpirationCard;

    private String cvv;

    @Override
    public String toString() {
        return "TransactionRequestDTO{" +
                "transactionValue=" + transactionValue +
                ", transactionDescription='" + transactionDescription + '\'' +
                ", typePayment='" + typePayment + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardOwnerName='" + cardOwnerName + '\'' +
                ", dateExpirationCard=" + dateExpirationCard +
                '}';
    }
}
