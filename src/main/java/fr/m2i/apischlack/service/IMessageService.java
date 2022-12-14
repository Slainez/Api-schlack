/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.m2i.apischlack.service;

import fr.m2i.apischlack.model.Message;
import java.util.List;

/**
 *
 * @author ben
 */
public interface IMessageService {
    List<Message> findAllMessageByChannel(Long id);
    Message MessageFindById(Long id);
    Message MessageFindByIdPut(Long id);
    Message save(Message message);
    Message update(Long id,Message content);
    boolean delete(Long id);
}
