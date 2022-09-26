/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.m2i.apischlack.service;

import fr.m2i.apischlack.dto.MessageMapper;
import fr.m2i.apischlack.exception.NotFoundException;
import fr.m2i.apischlack.model.Message;
import fr.m2i.apischlack.repository.MessageRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ben
 */
@Service
public class MessageService implements IMessageService{
    
    private final MessageRepository repo;
    
    @Autowired
    public MessageService(MessageRepository repo) {
        this.repo = repo;
    }
    @Override
    public List<Message> findAllMessageByChannel(Long id) throws NotFoundException{
        return repo.getAllMessageFromChannel(id);
    }

    @Override
    public Message MessageFindById(Long id) throws NotFoundException{
        return repo.MessageFindById(id);
    }

    @Override
    public Message MessageFindByIdPut(Long id) throws NotFoundException{
        return repo.MessageFindByIdPut(id);
    }
    
    @Override
    public boolean delete(Long id) throws NotFoundException{
        Message toDelete = MessageFindById(id);
        if(toDelete != null){
           repo.delete(toDelete);
           return true;
        }
        return false;
    }

    @Override
    public Message save(Message message) {
         return repo.save(message);
    }

    @Override
    public Message update(Long id, Message content)throws NotFoundException{
        Message toUpdate = MessageFindByIdPut(id);
        toUpdate = MessageMapper.copy(toUpdate, content);
        return save(toUpdate);
    }
    
    
    
}
