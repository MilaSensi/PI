package com.pi;

import com.pi.controller.OrdersController;
import com.pi.model.Payment;
import com.pi.repository.PaymentRepo;
import com.pi.service.PaymentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.number.OrderingComparison.comparesEqualTo;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(OrdersController.class)
public class PaymentTests {

    @Mock
    private PaymentRepo paymentRepo;

    @Test
    public void testGetById() {
        Payment payment = paymentRepo.getOne(1);
        assertThat(1, comparesEqualTo(payment.getId()));
    }
}
