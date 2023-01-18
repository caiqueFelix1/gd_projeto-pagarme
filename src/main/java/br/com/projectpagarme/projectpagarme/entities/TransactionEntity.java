package br.com.projectpagarme.projectpagarme.entities;

import br.com.projectpagarme.projectpagarme.enums.PaymentMapperEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

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

    private String dateExpirationCard;

    private String cvv;

    @OneToOne
    private PaymentEntity payment;

    public TransactionEntity(BigDecimal transactionValue, String transactionDescription, PaymentMapperEnum typePayment, String cardNumber, String cardOwnerName, String dateExpirationCard, String cvv, PaymentEntity payment) {
        this.transactionValue = transactionValue;
        this.transactionDescription = transactionDescription;
        this.typePayment = typePayment;
        this.cardNumber = cardNumber;
        this.cardOwnerName = cardOwnerName;
        this.dateExpirationCard = dateExpirationCard;
        this.cvv = cvv;
        this.payment = payment;
    }

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
