package br.com.projectpagarme.projectpagarme.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum PaymentMapperEnum {

    DEBIT_CARD("paid"),
    CREDIT_CARD("waiting_funds");

    private String status;
}
