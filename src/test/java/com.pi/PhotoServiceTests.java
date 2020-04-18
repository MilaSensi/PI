package com.pi;

import com.pi.model.*;
import com.pi.repository.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.hamcrest.number.OrderingComparison.comparesEqualTo;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PhotoServiceTests {

    @Autowired
    private PaymentStatusRepo paymentStatusRepo;
    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private PersonTypeRepo personTypeRepo;
    @Autowired
    private PersonRepo personRepo;
    @Autowired
    private PhotoServiceRepo photoServiceRepo;

    @Before
    public void setup() {
        init();
    }

    public void init() {
        PaymentStatus ps = paymentStatusRepo.findByCode("TEST PAYMENT STATUS");
        if (ps != null) {
            paymentStatusRepo.delete(ps);
        }
        Person person = personRepo.findByLogin("TEST LOGIN");
        if (person != null) {
            for (Payment payment : paymentRepo.findByPerson(person.getId())) {
                paymentRepo.delete(payment);
            }
            personRepo.delete(person);
        }
        PersonType personType = personTypeRepo.findByCode("TEST PERSON TYPE");
        if (personType != null) {
            personTypeRepo.delete(personType);
        }
        PhotoService photoService = photoServiceRepo.findByName("TEST");
        if (photoService != null) {
            photoServiceRepo.delete(photoService);
        }
    }

    @Test
    @Transactional
    public void testSavePersonType() {
        init();
        PersonType personType = new PersonType();
        personType.setCode("TEST PERSON TYPE");
        personType.setName("TEST");
        personTypeRepo.saveAndFlush(personType);
        PersonType p = personTypeRepo.findByCode("TEST PERSON TYPE");
        assertNotEquals(p, null);
        assertThat("TEST PERSON TYPE", comparesEqualTo(p.getCode()));
        assertThat("TEST", comparesEqualTo(p.getName()));
    }

    @Test
    @Transactional
    public void testSavePerson() {
        init();
        PersonType personType = new PersonType();
        personType.setCode("TEST PERSON TYPE");
        personType.setName("TEST");
        personTypeRepo.saveAndFlush(personType);

        Person person = new Person();
        person.setBirthday(new Date());
        person.setFullName("TEST");
        person.setInn("1234554321");
        person.setLogin("TEST LOGIN");
        person.setPasswordHash("TEST");
        person.setPersonType(personTypeRepo.findByCode("TEST PERSON TYPE"));
        personRepo.save(person);
        Person p = personRepo.findByLogin("TEST LOGIN");
        assertNotEquals(p, null);
        assertThat("TEST LOGIN", comparesEqualTo(p.getLogin()));
    }

    @Test
    public void testSavePhotoService() {
        init();
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
        init();
        PaymentStatus paymentStatus = new PaymentStatus();
        paymentStatus.setCode("TEST PAYMENT STATUS");
        paymentStatus.setName("Тестовый");
        paymentStatusRepo.save(paymentStatus);
        PaymentStatus ps = paymentStatusRepo.findByCode("TEST PAYMENT STATUS");
        assertThat("TEST PAYMENT STATUS", comparesEqualTo(ps.getCode()));
        assertThat("Тестовый", comparesEqualTo(ps.getName()));
    }

    @Test
    @Transactional
    public void testSavePayment() {
        init();
        PaymentStatus paymentStatus = new PaymentStatus();
        paymentStatus.setCode("TEST PAYMENT STATUS");
        paymentStatus.setName("Тестовый");
        paymentStatusRepo.save(paymentStatus);

        PersonType personType = new PersonType();
        personType.setCode("TEST PERSON TYPE");
        personType.setName("TEST");
        personTypeRepo.save(personType);

        Person person = new Person();
        person.setBirthday(new Date());
        person.setFullName("TEST");
        person.setInn("1234554321");
        person.setLogin("TEST LOGIN");
        person.setPasswordHash("TEST");
        person.setPersonType(personTypeRepo.findByCode("TEST PERSON TYPE"));
        person = personRepo.save(person);

        PhotoService ps = new PhotoService();
        ps.setName("TEST");
        ps.setPrice(10000);
        ps = photoServiceRepo.save(ps);

        Payment payment = new Payment();
        payment.setDateStart(new Date());
        payment.setPaymentStatus(paymentStatusRepo.findByCode("TEST PAYMENT STATUS"));
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
