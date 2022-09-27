/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.m2i.apischlack.service;

import fr.m2i.apischlack.dto.ChannelMapper;
import fr.m2i.apischlack.exception.NotFoundException;
import fr.m2i.apischlack.model.Channel;
import fr.m2i.apischlack.repository.ChannelRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ben
 */
@Service
public class ChannelService implements IChannelService{
  private final ChannelRepository repo;

    @Autowired
    public ChannelService(ChannelRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Channel> findAll() {
        return repo.findAll();
    }

    @Override
    public Channel findById(Long id) throws NotFoundException {
        return repo.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Channel with id: " + id + " was not found");
        });
    }

    @Override
    public Channel create(Channel customer) {
        return repo.save(customer);
    }

    public Channel save(Channel customer) {
        return repo.save(customer);
    }
    
    @Override
    public Channel update(Long id, Channel content) throws NotFoundException{

        Channel toUpdate = findById(id);
        toUpdate = ChannelMapper.copy(toUpdate, content);

        return repo.save(toUpdate);
    }
    
    @Override
    public void delete(Long id) {
        Channel toDelete = findById(id);
        repo.delete(toDelete);
    }
}