/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.m2i.apischlack.dto;

import fr.m2i.apischlack.model.Channel;
import fr.m2i.apischlack.model.Message;

/**
 *
 * @author moi
 */
public class MessageMapper {
    
    public static MessageDTO buildMessageDTO(Message message){
        
        ChannelDTO channelDTO = null;

        if (message.getChannel() != null) {
            channelDTO = ChannelMapper.buildChannelDTO(message.getChannel());
        }
        MessageDTO messageDTO = new MessageDTO(message.getId(),message.getUser(),message.getContent(),message.getTimestamp(),channelDTO);
        
        return messageDTO ;
    }
    
        public static Message buildMessage(MessageDTO messageDTO){
            //System.out.println("buildMessage :"+ messageDTO);
            
            Channel channel = null;

        if (messageDTO.getChannel() != null && messageDTO.getChannel().getId() != null) {
            System.out.println("buildMessage If :"+ messageDTO);
            channel = new Channel();
            channel.setId(messageDTO.getChannel().getId());
        }
        Message message = new Message(messageDTO.getId(),messageDTO.getUser(),messageDTO.getContent(),messageDTO.getTimestamp(),channel);
        
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
