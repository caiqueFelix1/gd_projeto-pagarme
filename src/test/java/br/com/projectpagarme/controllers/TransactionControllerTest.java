package br.com.projectpagarme.controllers;


import br.com.projectpagarme.dtos.requests.TransactionRequestDTO;
import br.com.projectpagarme.enums.PaymentMapperEnum;
import br.com.projectpagarme.facades.TransactionFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration")
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionFacade transactionFacade;

    private final String TRANSACTION_ROUTE = "/transactions";

    private final String TRANSACTION_STATUS_ROUTE = "/transactions/balance";

    private final BigDecimal MOCK_TRANSACTION_VALUE = new BigDecimal(10.00);

    private final String MOCK_DESCRIPTION_TRANSACTION = "SMARTPHONE";

    private final PaymentMapperEnum MOCK_DEBIT_CARD = PaymentMapperEnum.DEBIT_CARD;

    private final PaymentMapperEnum MOCK_CREDIT_CARD = PaymentMapperEnum.CREDIT_CARD;

    private final String MOCK_NUMBER_CARD = "4444-3333-2222-1111";

    private final String MOCK_OWNER_CARD = "COELHO LISBOA";

    private final String MOCK_VALIDATE_CARD = "01-23";

    private final String MOCK_CVV_CARD = "333";

    @Test
    public void tryingToGetTheTransactionsListItShouldToReturnOk() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(get(TRANSACTION_ROUTE))
                .andExpect(status().isOk());
    }

    @Test
    public void tryingToGetTheBalanceStatusFromTransactionsItShouldToReturnOk() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(get(TRANSACTION_STATUS_ROUTE))
                .andExpect(status().isOk());
    }

    @Test
    public void tryingToCreateANewTransactionWithDebitCardItShouldToReturnOk() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(TRANSACTION_ROUTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(itIsReturningCorrectListInDebitCardFromTransactionRequestDTO())))
                .andExpect(status().isOk());
    }

    @Test
    public void tryingToCreateANewTransactionWithCreditCardItShouldToReturnOk() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(TRANSACTION_ROUTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(itIsReturningCorrectListInCreditCardFromTransactionRequestDTO())))
                .andExpect(status().isOk());
    }

    @Test
    public void tryingToCreateANewTransactionWithLessThanTheMinimumCharInValueItShouldToReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(TRANSACTION_ROUTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(itIsReturningLessThanTheMinimumCharInValue())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void tryingToCreateANewTransactionWithGreaterThanTheMaximumCharInValueItShouldToReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(TRANSACTION_ROUTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(itIsReturningGreaterThanTheMaximumCharInValue())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void tryingToCreateANewTransactionWithTransactionDescriptionFieldBlankItShouldToReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(TRANSACTION_ROUTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(itIsReturningBlankTransactionDescriptionField())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void tryingToCreateANewTransactionWithLessThanTheMinimumCharInTransactionDescriptionItShouldToReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(TRANSACTION_ROUTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(itIsReturningLessThanTheMinimumCharInTransactionDescription())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void tryingToCreateANewTransactionWithGreaterThanTheMaximumCharTransactionDescriptionItShouldToReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(TRANSACTION_ROUTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(itIsReturningGreaterThanTheMaximumCharTransactionDescription())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void tryingToCreateANewTransactionWithTypePaymentFieldBlankItShouldToReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(TRANSACTION_ROUTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(itIsReturningBlankTypePaymentField())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void tryingToCreateANewTransactionWithCardNumberFieldBlankItShouldToReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(TRANSACTION_ROUTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(itIsReturningBlankCardNumberField())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void tryingToCreateANewTransactionWithLessThanTheMinimumCharInCardNumberItShouldToReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(TRANSACTION_ROUTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(itIsReturningLessThanTheMinimumCharInCardNumber())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void tryingToCreateANewTransactionWithGreaterThanTheMaximumCharCardNumberItShouldToReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(TRANSACTION_ROUTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(itIsReturningGreaterThanTheMaximumCharCardNumber())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void tryingToCreateANewTransactionWithCardOwnerFieldBlankItShouldToReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(TRANSACTION_ROUTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(itIsReturningBlankCardOwnerField())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void tryingToCreateANewTransactionWithLessThanTheMinimumCharInCardOwnerItShouldToReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(TRANSACTION_ROUTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(itIsReturningLessThanTheMinimumCharInCardOwner())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void tryingToCreateANewTransactionWithGreaterThanTheMaximumCharCardOwnerItShouldToReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(TRANSACTION_ROUTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(itIsReturningGreaterThanTheMaximumCharCardOwner())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void tryingToCreateANewTransactionWithCvvFieldBlankItShouldToReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(TRANSACTION_ROUTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(itIsReturningBlankCvvField())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void tryingToCreateANewTransactionWithLessThanTheMinimumCharInCvvItShouldToReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(TRANSACTION_ROUTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(itIsReturningLessThanTheMinimumCharInCvvField())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void tryingToCreateANewTransactionWithGreaterThanTheMaximumCharCvvItShouldToReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(TRANSACTION_ROUTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(itIsReturningGreaterThanTheMaximumCharCvvField())))
                .andExpect(status().isBadRequest());
    }


    private TransactionRequestDTO itIsReturningCorrectListInDebitCardFromTransactionRequestDTO(){
        return new TransactionRequestDTO(MOCK_TRANSACTION_VALUE,
                MOCK_DESCRIPTION_TRANSACTION,
                MOCK_DEBIT_CARD,
                MOCK_NUMBER_CARD,
                MOCK_OWNER_CARD,
                MOCK_VALIDATE_CARD,
                MOCK_CVV_CARD);
    }

    private TransactionRequestDTO itIsReturningCorrectListInCreditCardFromTransactionRequestDTO(){
        return new TransactionRequestDTO(MOCK_TRANSACTION_VALUE,
                MOCK_DESCRIPTION_TRANSACTION,
                MOCK_CREDIT_CARD,
                MOCK_NUMBER_CARD,
                MOCK_OWNER_CARD,
                MOCK_VALIDATE_CARD,
                MOCK_CVV_CARD);
    }

    private TransactionRequestDTO itIsReturningLessThanTheMinimumCharInValue(){
        return new TransactionRequestDTO(
                (new BigDecimal(0.99)),
                MOCK_DESCRIPTION_TRANSACTION,
                MOCK_DEBIT_CARD,
                MOCK_NUMBER_CARD,
                MOCK_OWNER_CARD,
                MOCK_VALIDATE_CARD,
                MOCK_CVV_CARD);
    }

    private TransactionRequestDTO itIsReturningGreaterThanTheMaximumCharInValue(){
        return new TransactionRequestDTO(
                (new BigDecimal(100000.00)),
                MOCK_DESCRIPTION_TRANSACTION,
                MOCK_DEBIT_CARD,
                MOCK_NUMBER_CARD,
                MOCK_OWNER_CARD,
                MOCK_VALIDATE_CARD,
                MOCK_CVV_CARD);
    }

    private TransactionRequestDTO itIsReturningBlankTransactionDescriptionField(){
        return new TransactionRequestDTO(MOCK_TRANSACTION_VALUE,
                "   ",
                MOCK_DEBIT_CARD,
                MOCK_NUMBER_CARD,
                MOCK_OWNER_CARD,
                MOCK_VALIDATE_CARD,
                MOCK_CVV_CARD);
    }

    private TransactionRequestDTO itIsReturningLessThanTheMinimumCharInTransactionDescription(){
        return new TransactionRequestDTO(MOCK_TRANSACTION_VALUE,
                "SM",
                MOCK_DEBIT_CARD,
                MOCK_NUMBER_CARD,
                MOCK_OWNER_CARD,
                MOCK_VALIDATE_CARD,
                MOCK_CVV_CARD);
    }

    private TransactionRequestDTO itIsReturningGreaterThanTheMaximumCharTransactionDescription(){
        return new TransactionRequestDTO(MOCK_TRANSACTION_VALUE,
                "SMARTPHONESMARTPHONESMARTPHONESMARTPHONESMARTPHONES",
                MOCK_DEBIT_CARD,
                MOCK_NUMBER_CARD,
                MOCK_OWNER_CARD,
                MOCK_VALIDATE_CARD,
                MOCK_CVV_CARD);
    }

    private TransactionRequestDTO itIsReturningBlankTypePaymentField(){
        return new TransactionRequestDTO(MOCK_TRANSACTION_VALUE,
                MOCK_DESCRIPTION_TRANSACTION,
                null,
                MOCK_NUMBER_CARD,
                MOCK_OWNER_CARD,
                MOCK_VALIDATE_CARD,
                MOCK_CVV_CARD);
    }

    private TransactionRequestDTO itIsReturningBlankCardNumberField(){
        return new TransactionRequestDTO(MOCK_TRANSACTION_VALUE,
                MOCK_DESCRIPTION_TRANSACTION,
                MOCK_DEBIT_CARD,
                "  ",
                MOCK_OWNER_CARD,
                MOCK_VALIDATE_CARD,
                MOCK_CVV_CARD);
    }

    private TransactionRequestDTO itIsReturningLessThanTheMinimumCharInCardNumber(){
        return new TransactionRequestDTO(MOCK_TRANSACTION_VALUE,
                MOCK_DESCRIPTION_TRANSACTION,
                MOCK_DEBIT_CARD,
                "444433332222111",
                MOCK_OWNER_CARD,
                MOCK_VALIDATE_CARD,
                MOCK_CVV_CARD);
    }

    private TransactionRequestDTO itIsReturningGreaterThanTheMaximumCharCardNumber(){
        return new TransactionRequestDTO(MOCK_TRANSACTION_VALUE,
                MOCK_DESCRIPTION_TRANSACTION,
                MOCK_DEBIT_CARD,
                "4444-3333-2222-11111",
                MOCK_OWNER_CARD,
                MOCK_VALIDATE_CARD,
                MOCK_CVV_CARD);
}

    private TransactionRequestDTO itIsReturningBlankCardOwnerField(){
        return new TransactionRequestDTO(MOCK_TRANSACTION_VALUE,
                MOCK_DESCRIPTION_TRANSACTION,
                MOCK_DEBIT_CARD,
                MOCK_NUMBER_CARD,
                "   ",
                MOCK_VALIDATE_CARD,
                MOCK_CVV_CARD);
    }

    private TransactionRequestDTO itIsReturningLessThanTheMinimumCharInCardOwner(){
        return new TransactionRequestDTO(MOCK_TRANSACTION_VALUE,
                MOCK_DESCRIPTION_TRANSACTION,
                MOCK_DEBIT_CARD,
                MOCK_NUMBER_CARD,
                "CO",
                MOCK_VALIDATE_CARD,
                MOCK_CVV_CARD);
    }

    private TransactionRequestDTO itIsReturningGreaterThanTheMaximumCharCardOwner(){
        return new TransactionRequestDTO(MOCK_TRANSACTION_VALUE,
                MOCK_DESCRIPTION_TRANSACTION,
                MOCK_DEBIT_CARD,
                MOCK_NUMBER_CARD,
                "COELHO LISBOACOELHO LISBOACOELHO LISBOACOELHO LISBOACOEL",
                MOCK_VALIDATE_CARD,
                MOCK_CVV_CARD);
    }

    private TransactionRequestDTO itIsReturningBlankCvvField(){
        return new TransactionRequestDTO(MOCK_TRANSACTION_VALUE,
                MOCK_DESCRIPTION_TRANSACTION,
                MOCK_DEBIT_CARD,
                MOCK_NUMBER_CARD,
                MOCK_OWNER_CARD,
                MOCK_VALIDATE_CARD,
                "  ");
    }

    private TransactionRequestDTO itIsReturningLessThanTheMinimumCharInCvvField(){
        return new TransactionRequestDTO(MOCK_TRANSACTION_VALUE,
                MOCK_DESCRIPTION_TRANSACTION,
                MOCK_DEBIT_CARD,
                MOCK_NUMBER_CARD,
                MOCK_OWNER_CARD,
                MOCK_VALIDATE_CARD,
                "33");
    }

    private TransactionRequestDTO itIsReturningGreaterThanTheMaximumCharCvvField(){
        return new TransactionRequestDTO(MOCK_TRANSACTION_VALUE,
                MOCK_DESCRIPTION_TRANSACTION,
                MOCK_DEBIT_CARD,
                MOCK_NUMBER_CARD,
                MOCK_OWNER_CARD,
                MOCK_VALIDATE_CARD,
                "3333");
    }
}
