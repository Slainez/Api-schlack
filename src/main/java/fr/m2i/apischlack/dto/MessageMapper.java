/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.m2i.apischlack.dto;

import fr.m2i.apischlack.model.Message;

/**
 *
 * @author moi
 */
public class MessageMapper {
    
    public static MessageDTO buildMessageDTO(Message message){
        MessageDTO messageDTO = new MessageDTO(message.getId(),message.getUser(),message.getContent(),message.getTimestamp(),message.getChannel());
        
        return messageDTO ;
    }
    
        public static Message buildMessage(MessageDTO messageDTO){
        Message message = new Message(messageDTO.getId(),messageDTO.getUser(),messageDTO.getContent(),messageDTO.getTimestamp(),messageDTO.getChannel());
        
        return message ;
    }
        
    public static Message copy(Message message ,Message content ){
        
        if(content.getUser() != null){
            message.setUser(content.getUser());
        }
        if(content.getContent() != null){
            message.setContent(content.getContent());
        }
        if(content.getChannel() != null){
            message.setChannel(content.getChannel());
        }
         if(content.getTimestamp()!= null){
            message.setTimestamp(content.getTimestamp());
        }
        
        
        return message ;
    }   
}
