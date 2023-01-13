package br.com.projectpagarme.projectpagarme.entities;

import br.com.projectpagarme.projectpagarme.enums.PaymentMapperEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal transactionValue;

    private String transactionDescription;

    private PaymentMapperEnum typePayment;

    private String cardNumber;

    private String cardOwnerName;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date dateExpirationCard;

    private String cvv;

    @OneToOne
    private PaymentEntity payment;

    @Override
    public String toString() {
        return "TransactionEntity{" +
                "id=" + id +
                ", transactionValue=" + transactionValue +
                ", transactionDescription='" + transactionDescription + '\'' +
                ", typePayment=" + typePayment +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardOwnerName='" + cardOwnerName + '\'' +
                ", dateExpirationCard=" + dateExpirationCard +
                ", payment=" + payment +
                '}';
    }
}
