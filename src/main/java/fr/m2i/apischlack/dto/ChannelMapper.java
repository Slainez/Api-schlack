/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.m2i.apischlack.dto;

import fr.m2i.apischlack.model.Channel;
import fr.m2i.apischlack.model.ChannelMain;

/**
 *
 * @author ben
 */
public class ChannelMapper {
    
public static ChannelDTO buildChannelDTO(Channel Channel) {
        if (Channel == null) {
            return null;
        }
        String main;
        if (Channel.getMain() == ChannelMain.MAIN) {
            main = "MAIN";
        } else if (Channel.getMain() == ChannelMain.NOTMAIN){
            main = "NOTMAIN";
        }else{
            main = "SUB";

        }

        return new ChannelDTO(Channel.getId(), Channel.getName(), Channel.getListMessage(), main);

    }

    public static Channel buildChannel(ChannelDTO ChannelDTO) {
        if (ChannelDTO == null) {
            return null;
        }
        ChannelMain main;
        if ("MAIN".equals(ChannelDTO.getMain())) {
            main = ChannelMain.MAIN;
        } else if ("NOTMAIN".equals(ChannelDTO.getMain())){
            main = ChannelMain.NOTMAIN;
        }else{
            main = ChannelMain.SUB;

        }
        
        Channel newChannel = new Channel(ChannelDTO.getId(), ChannelDTO.getName(), ChannelDTO.getListMessage(), main);
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

        if (content.getMain() != null) {
            Channel.setMain(content.getMain());
        }

        return Channel;
    }

}
