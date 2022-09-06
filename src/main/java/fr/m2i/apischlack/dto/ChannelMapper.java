/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.m2i.apischlack.dto;

import fr.m2i.apischlack.model.Channel;
import fr.m2i.apischlack.model.ChannelType;

/**
 *
 * @author ben
 */
public class ChannelMapper {
    
public static ChannelDTO buildChannelDTO(Channel Channel) {
        if (Channel == null) {
            return null;
        }
        String channelType;
        if (Channel.getChannelType()== ChannelType.MAIN) {
            channelType = "MAIN";
        } else if (Channel.getChannelType()== ChannelType.NOTMAIN){
            channelType = "NOTMAIN";
        }else{
            channelType = "SUB";

        }

        return new ChannelDTO(Channel.getId(), Channel.getName(), Channel.getListMessage(), channelType);

    }

    public static Channel buildChannel(ChannelDTO ChannelDTO) {
        if (ChannelDTO == null) {
            return null;
        }
        ChannelType channelType;
        if ("MAIN".equals(ChannelDTO.getChannelType())) {
            channelType = ChannelType.MAIN;
        } else if ("NOTMAIN".equals(ChannelDTO.getChannelType())){
            channelType = ChannelType.NOTMAIN;
        }else{
            channelType = ChannelType.SUB;

        }
        
        Channel newChannel = new Channel(ChannelDTO.getId(), ChannelDTO.getName(), ChannelDTO.getListMessage(), channelType);
        return newChannel;
    }

    public static Channel copy(Channel Channel, Channel content) {
         if (Channel == null || content == null) {
            return null;
        }

         if (content.getName() != null) {
            Channel.setName(content.getName());
        }
         
        if (content.getListMessage() != null) {
            Channel.setListMessage(content.getListMessage());
        }

        if (content.getChannelType()!= null) {
            Channel.setChannelType(content.getChannelType());
        }

        return Channel;
    }

}
