/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.m2i.apischlack.service;

import fr.m2i.apischlack.model.Channel;
import java.util.List;

/**
 *
 * @author ben
 */
public interface IChannelService {
    List<Channel> findAll();

    Channel findById(Long id);

    Channel create(Channel channel);
    Channel save(Channel channel);
    Channel update(Long id,Channel content);
    void delete(Long id);
}