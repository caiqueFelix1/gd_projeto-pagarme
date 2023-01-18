package br.com.projectpagarme.projectpagarme.services.Impl;

import br.com.projectpagarme.projectpagarme.entities.PaymentEntity;
import br.com.projectpagarme.projectpagarme.enums.PaymentMapperEnum;
import br.com.projectpagarme.projectpagarme.repositories.PaymentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceImplTest {

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    private final PaymentMapperEnum MOCK_DEBIT_CARD = PaymentMapperEnum.DEBIT_CARD;

    private final PaymentMapperEnum MOCK_CREDIT_CARD = PaymentMapperEnum.CREDIT_CARD;

    private final String MOCK_STATUS_PAID = PaymentMapperEnum.DEBIT_CARD.getStatus();

    private final String MOCK_STATUS_WAITING_PAID = PaymentMapperEnum.CREDIT_CARD.getStatus();

    private static final LocalDate MOCK_DATE_DEBIT = LocalDate.now();

    private static final LocalDate MOCK_DATE_CREDIT = LocalDate.now().plusDays(30);


    @Before
    public void setupMock(){
        MockitoAnnotations.openMocks(this);
        when(paymentRepository.save(itIsReturnAnObjectOfPaymentEntity())).thenReturn(itIsReturnAnObjectOfPaymentEntity());
        when(paymentRepository.save(itIsReturnAnObjectOfPaymentEntityWithCredit())).thenReturn(itIsReturnAnObjectOfPaymentEntityWithCredit());
    }

    @Test
    public void createANewPaymentWithSuccessfullyItShouldReturnOk(){
        assertEquals(itIsReturnAnObjectOfPaymentEntity(), paymentService.create(MOCK_DEBIT_CARD));
    }

    @Test
    public void createANewPaymentInCreditWithSuccessfullyItShouldReturnOk(){
        assertEquals(itIsReturnAnObjectOfPaymentEntityWithCredit(), paymentService.create(MOCK_CREDIT_CARD));
    }

    private PaymentEntity itIsReturnAnObjectOfPaymentEntity() {
        return new PaymentEntity(MOCK_STATUS_PAID,
                MOCK_DATE_DEBIT);
    }

    private PaymentEntity itIsReturnAnObjectOfPaymentEntityWithCredit() {
        return new PaymentEntity(MOCK_STATUS_WAITING_PAID,
                MOCK_DATE_CREDIT);
    }
}
