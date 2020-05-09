package com.pi.service;

import com.pi.model.Message;
import com.pi.model.Person;
import com.pi.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
public class MessageService {

    private final MessageRepo messageRepo;
    private final PersonService personService;

    @Autowired
    public MessageService(MessageRepo messageRepo, PersonService personService) {
        this.messageRepo = messageRepo;
        this.personService = personService;
    }

    public Collection<Message> getMessages(Integer client, Integer admin) {
        return messageRepo.findByClientAndAdmin(client, admin);
    }

    public void sendMessage(String message, String sender, Integer client, Integer admin) throws Exception {
        Message m = new Message();
        m.setMessage(message);
        m.setSender(sender);
        m.setDateSend(new Date());
        Person c = personService.getById(client);
        if (c == null) {
            throw new Exception("Client not found");
        }
        m.setClient(c);

        Person a = personService.getById(admin);
        if (a == null) {
            throw new Exception("Admin not found");
        }
        m.setAdmin(a);
        messageRepo.saveAndFlush(m);
    }
}
