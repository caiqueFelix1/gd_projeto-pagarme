package br.com.projectpagarme.projectpagarme.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String status;

    private LocalDate paymentDate;

    public PaymentEntity(Long id) {

        this.id = id;
    }

    public PaymentEntity(String status, LocalDate paymentDate) {
        this.status = status;
        this.paymentDate = paymentDate;
    }

    public PaymentEntity(String paid, Date today) {
    }
}
