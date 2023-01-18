package br.com.projectpagarme.facades.Impl;

import br.com.projectpagarme.dtos.requests.TransactionRequestDTO;
import br.com.projectpagarme.dtos.responses.PaymentResponseDTO;
import br.com.projectpagarme.dtos.responses.TransactionResponseDTO;
import br.com.projectpagarme.entities.PaymentEntity;
import br.com.projectpagarme.entities.TransactionEntity;
import br.com.projectpagarme.enums.PaymentMapperEnum;
import br.com.projectpagarme.facades.impl.TransactionFacadeImpl;
import br.com.projectpagarme.mappers.TransactionMapper;
import br.com.projectpagarme.dtos.responses.BalanceResponseDTO;
import br.com.projectpagarme.services.PaymentService;
import br.com.projectpagarme.services.TransactionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TransactionFacadeImplTest {

    @InjectMocks
    private TransactionFacadeImpl transactionFacade;

    @Mock
    private TransactionService transactionService;

    @Mock
    private TransactionMapper transactionMapper;

    @Mock
    private PaymentService paymentService;

    private final Long MOCK_ID_PAYMENT = 1L;

    private final Long MOCK_ID_TRANSACTION = 1L;

    private final BigDecimal MOCK_TRANSACTION_VALUE = new BigDecimal(10.00);

    private final String MOCK_DESCRIPTION_TRANSACTION = "SMARTPHONE";

    private final PaymentMapperEnum MOCK_DEBIT_CARD = PaymentMapperEnum.DEBIT_CARD;

    private final PaymentMapperEnum MOCK_CREDIT_CARD = PaymentMapperEnum.CREDIT_CARD;

    private final String MOCK_NUMBER_CARD = "**** **** **** 1111";

    private final String MOCK_OWNER_CARD = "COELHO LISBOA";

    private final String MOCK_VALIDATE_CARD = "01-23";

    private final String MOCK_STATUS_PAID = PaymentMapperEnum.DEBIT_CARD.getStatus();

    private final String MOCK_STATUS_WAITING_PAID = PaymentMapperEnum.CREDIT_CARD.getStatus();

    private final String MOCK_CVV_CARD = "333";

    private static final LocalDate MOCK_DATE_DEBIT = LocalDate.now();

    private static final LocalDate MOCK_DATE_CREDIT = LocalDate.now().plusDays(30);

    @Before
    public void setupMock(){
        MockitoAnnotations.openMocks(this);
        when(transactionService.getAll()).thenReturn(itIsReturningTheListOfTransactionEntity());
        when(transactionService.create(itIsReturnAnObjectOfTransactionEntity())).thenReturn(itIsReturnAnObjectOfTransactionEntity());
        when(transactionService.create(itIsReturnAnObjectOfTransactionEntityWithCreditCard())).thenReturn(itIsReturnAnObjectOfTransactionEntityWithCreditCard());

        when(transactionMapper.toListDto(itIsReturningTheListOfTransactionEntity())).thenReturn(itIsReturningTheListOfTransactionResponseDTO());

        when(transactionMapper.toDto(itIsReturnAnObjectOfTransactionEntity())).thenReturn(itIsReturningAnObjectOfTransactionResponseDTO());
        when(transactionMapper.toDto(itIsReturnAnObjectOfTransactionEntityWithCreditCard())).thenReturn(itIsReturningAnObjectOfTransactionResponseDTOWithCreditCard());
        when(transactionMapper.toEntity(itIsReturningAnObjectOfTransactionRequestDTO())).thenReturn(itIsReturnAnObjectOfTransactionEntity());
        when(transactionMapper.toEntity(itIsReturningAnObjectOfTransactionRequestDTOWithCreditCard())).thenReturn(itIsReturnAnObjectOfTransactionEntityWithCreditCard());
    }

    @Test
    public void getAllTransactionsItShouldReturnOk() throws Exception{
        assertEquals(itIsReturningTheListOfTransactionResponseDTO(), transactionFacade.getAll());
    }

    @Test
    public void createANewTransactionWithSuccessfullyItShouldReturnOk() throws Exception{
        Assert.assertEquals(itIsReturningAnObjectOfTransactionResponseDTO(), transactionFacade.create(itIsReturningAnObjectOfTransactionRequestDTO()));
    }

    @Test
    public void createANewTransactionWithCreditCardSuccessfullyItShouldReturnOk() throws Exception{
        Assert.assertEquals(itIsReturningAnObjectOfTransactionResponseDTOWithCreditCard(), transactionFacade.create(itIsReturningAnObjectOfTransactionRequestDTOWithCreditCard()));
    }

    @Test
    public void getBalanceShouldReturnOk() throws Exception{
        assertEquals(itIsReturningAnObjectOfResponseDTO(), transactionFacade.getBalance(MOCK_TRANSACTION_VALUE));
    }

    private BalanceResponseDTO itIsReturningAnObjectOfResponseDTO() {

        BigDecimal mockWaitingFounds = MOCK_TRANSACTION_VALUE;
        BigDecimal mockAvailable = MOCK_TRANSACTION_VALUE;

        return new BalanceResponseDTO(mockWaitingFounds, mockAvailable);
    }

    private List<TransactionEntity> itIsReturningTheListOfTransactionEntity() {
        List<TransactionEntity> getAll = new ArrayList<>();
        getAll.add(itIsReturnAnObjectOfTransactionEntity());
        getAll.add(itIsReturnAnObjectOfTransactionEntityWithCreditCard());

        return getAll;
    }

    private TransactionEntity itIsReturnAnObjectOfTransactionEntity(){

        return new TransactionEntity(MOCK_TRANSACTION_VALUE,
                MOCK_DESCRIPTION_TRANSACTION,
                MOCK_DEBIT_CARD,
                MOCK_NUMBER_CARD,
                MOCK_OWNER_CARD,
                MOCK_VALIDATE_CARD,
                MOCK_CVV_CARD,
                itIsReturningAnObjectPaymentEntityInDebitCard());
    }

    private List<TransactionResponseDTO> itIsReturningTheListOfTransactionResponseDTO(){
        List<TransactionResponseDTO> getAll = new ArrayList<>();
        getAll.add(itIsReturningAnObjectOfTransactionResponseDTO());
        getAll.add(itIsReturningAnObjectOfTransactionResponseDTOWithCreditCard());

        return getAll;
    }

    private TransactionResponseDTO itIsReturningAnObjectOfTransactionResponseDTO(){

        return new TransactionResponseDTO(MOCK_ID_TRANSACTION,
                MOCK_TRANSACTION_VALUE,
                MOCK_DESCRIPTION_TRANSACTION,
                MOCK_DEBIT_CARD,
                MOCK_NUMBER_CARD,
                MOCK_OWNER_CARD,
                MOCK_VALIDATE_CARD,
                itIsReturningAnObjectOfPaymentResponseDTOInDebit());
    }

    private TransactionRequestDTO itIsReturningAnObjectOfTransactionRequestDTO(){

        return new TransactionRequestDTO(MOCK_TRANSACTION_VALUE,
                MOCK_DESCRIPTION_TRANSACTION,
                MOCK_DEBIT_CARD,
                MOCK_NUMBER_CARD,
                MOCK_OWNER_CARD,
                MOCK_VALIDATE_CARD,
                MOCK_CVV_CARD);
    }


    //creditCard
    private TransactionEntity itIsReturnAnObjectOfTransactionEntityWithCreditCard(){

        return new TransactionEntity(MOCK_TRANSACTION_VALUE,
                MOCK_DESCRIPTION_TRANSACTION,
                MOCK_CREDIT_CARD,
                MOCK_NUMBER_CARD,
                MOCK_OWNER_CARD,
                MOCK_VALIDATE_CARD,
                MOCK_CVV_CARD,
                itIsReturningAnObjectPaymentEntityInCreditCard());
    }


    private TransactionResponseDTO itIsReturningAnObjectOfTransactionResponseDTOWithCreditCard(){

        return new TransactionResponseDTO(MOCK_ID_TRANSACTION,
                MOCK_TRANSACTION_VALUE,
                MOCK_DESCRIPTION_TRANSACTION,
                MOCK_CREDIT_CARD,
                MOCK_NUMBER_CARD,
                MOCK_OWNER_CARD,
                MOCK_VALIDATE_CARD,
                itIsReturningAnObjectOfPaymentResponseDTOInCredit());
    }

    private TransactionRequestDTO itIsReturningAnObjectOfTransactionRequestDTOWithCreditCard(){

        return new TransactionRequestDTO(MOCK_TRANSACTION_VALUE,
                MOCK_DESCRIPTION_TRANSACTION,
                MOCK_CREDIT_CARD,
                MOCK_NUMBER_CARD,
                MOCK_OWNER_CARD,
                MOCK_VALIDATE_CARD,
                MOCK_CVV_CARD);
    }

    private PaymentEntity itIsReturningAnObjectPaymentEntityInDebitCard(){
        return new PaymentEntity(MOCK_ID_PAYMENT,
                MOCK_STATUS_PAID,
                MOCK_DATE_DEBIT);
    }

    private PaymentEntity itIsReturningAnObjectPaymentEntityInCreditCard(){
        return new PaymentEntity(MOCK_ID_PAYMENT,
                MOCK_STATUS_WAITING_PAID,
                MOCK_DATE_CREDIT);
    }

    private PaymentResponseDTO itIsReturningAnObjectOfPaymentResponseDTOInDebit(){
        return new PaymentResponseDTO(MOCK_STATUS_PAID,
                MOCK_DATE_DEBIT);
    }

    private PaymentResponseDTO itIsReturningAnObjectOfPaymentResponseDTOInCredit(){
        return new PaymentResponseDTO(MOCK_STATUS_WAITING_PAID,
                MOCK_DATE_CREDIT);
    }
}
