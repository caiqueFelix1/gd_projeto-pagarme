package br.com.projectpagarme.projectpagarme.dtos.requests;

import br.com.projectpagarme.projectpagarme.enums.PaymentMapperEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionRequestDTO {

    @DecimalMin(value = "1", inclusive = false)
    @DecimalMax(value="99999.99", inclusive = false)
    private BigDecimal transactionValue;

    @NotBlank(message = "{blank.field}")
    @Size(min = 3, max = 50, message = "{invalid.size}")
    private String transactionDescription;

    @NotNull(message = "{null.field}")
    private PaymentMapperEnum typePayment;

    @NotBlank(message = "{blank.field}")
    @Size(min = 16, max = 19, message = "{invalid.size}")
    private String cardNumber;

    @NotBlank(message = "{blank.field}")
    @Size(min = 3, max = 55, message = "{invalid.size}")
    private String cardOwnerName;

    @NotBlank(message = "{blank.field}")
    @Size(min = 5, message = "{invalid.size.below.the.minimum}")
    @Size(max = 5, message = "{invalid.size.above.the.maximum}")
    private String dateExpirationCard;

    @NotBlank(message = "{blank.field}")
    @Size(min = 3, message = "{invalid.size.below.the.minimum}")
    @Size(max = 3, message = "{invalid.size.above.the.maximum}")
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
