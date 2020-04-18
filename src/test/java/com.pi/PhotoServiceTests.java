package com.pi;

import com.pi.model.*;
import com.pi.repository.*;
import com.pi.service.PaymentService;
import com.pi.service.PersonService;
import com.pi.service.PhotoServiceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collection;
import java.util.Date;

import static org.hamcrest.number.OrderingComparison.comparesEqualTo;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PhotoServiceTests {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private PaymentStatusRepo paymentStatusRepo;
    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private PersonService personService;
    @Autowired
    private PhotoServiceService photoServiceService;
    @Autowired
    private PersonTypeRepo personTypeRepo;
    @Autowired
    private PersonRepo personRepo;
    @Autowired
    private PhotoServiceRepo photoServiceRepo;

    @Before
    public void setup() throws Exception {
        initPaymentStatus();
    }

    public void initPaymentStatus() {
        paymentStatusRepo.delete(paymentStatusRepo.findByCode("TEST"));
        Person person = personRepo.findByLogin("TEST");
        if (person != null) {
            for (Payment payment : paymentRepo.findByPerson(person.getId())) {
                paymentRepo.delete(payment);
            }
            personRepo.delete(person);
        }

        photoServiceRepo.delete(photoServiceRepo.findByName("TEST"));
    }

    @Test
    public void testSavePerson() {
        Person person = new Person();
        person.setBirthday(new Date());
        person.setFullName("TEST");
        person.setInn("12345");
        person.setLogin("TEST LOGIN");
        person.setPasswordHash("TEST");
        person.setPersonType(personTypeRepo.findByCode("TEST"));
        personRepo.save(person);
        Person p = personRepo.findByLogin("TEST");
        assertNotEquals(p, null);
        assertThat("TEST LOGIN", comparesEqualTo(p.getLogin()));
    }

    @Test
    public void testSavePhotoService() {
        PhotoService ps = new PhotoService();
        ps.setName("TEST");
        ps.setPrice(10000);
        photoServiceRepo.save(ps);
        PhotoService photoService = photoServiceRepo.findByName("TEST");
        assertNotEquals(photoService, null);
        assertThat("TEST", comparesEqualTo(photoService.getName()));
        assertThat(10000, comparesEqualTo(photoService.getPrice()));
    }

    @Test
    public void testSavePaymentStatus() {
        Collection<PaymentStatus> statuses = paymentStatusRepo.findAll();
        assertThat(4, comparesEqualTo(statuses.size()));
        PaymentStatus paymentStatus = new PaymentStatus();
        paymentStatus.setCode("TEST");
        paymentStatus.setName("Тестовый");
        paymentStatusRepo.save(paymentStatus);
        PaymentStatus ps = paymentStatusRepo.findByCode("TEST");
        assertThat("TEST", comparesEqualTo(ps.getCode()));
        assertThat("Тестовый", comparesEqualTo(ps.getName()));
        statuses = paymentStatusRepo.findAll();
        assertThat(5, comparesEqualTo(statuses.size()));
    }

    @Test
    public void testSavePayment() {
        PaymentStatus paymentStatus = new PaymentStatus();
        paymentStatus.setCode("TEST");
        paymentStatus.setName("Тестовый");
        paymentStatusRepo.save(paymentStatus);

        PersonType personType = new PersonType();
        personType.setCode("TEST PAYMENT STATUS");
        personType.setName("TEST");
        personTypeRepo.save(personType);

        Person person = new Person();
        person.setBirthday(new Date());
        person.setFullName("TEST");
        person.setInn("12345");
        person.setLogin("TEST LOGIN");
        person.setPasswordHash("TEST");
        person.setPersonType(personTypeRepo.findByCode("TEST"));
        person = personRepo.save(person);

        PhotoService ps = new PhotoService();
        ps.setName("TEST");
        ps.setPrice(10000);
        ps = photoServiceRepo.save(ps);

        Payment payment = new Payment();
        payment.setDateStart(new Date());
        payment.setPaymentStatus(paymentStatusRepo.findByCode("TEST"));
        payment.setSpecialist(person);
        payment.setPhotoService(ps);
        payment.setPerson(person);
        payment = paymentRepo.saveAndFlush(payment);

        Payment p = paymentRepo.findById(payment.getId()).orElse(null);

        assertNotEquals(p, null);
        assertThat("TEST PAYMENT STATUS", comparesEqualTo(p.getPaymentStatus().getCode()));
        assertThat("TEST LOGIN", comparesEqualTo(p.getSpecialist().getLogin()));
        assertThat("TEST", comparesEqualTo(p.getPhotoService().getName()));
        assertThat(10000, comparesEqualTo(p.getPhotoService().getPrice()));
    }
}
