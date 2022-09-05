/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.m2i.apischlack.dto;

import fr.m2i.apischlack.model.Message;
import java.util.List;


/**
 *
 * @author ben
 */
public class ChannelDTO {

    private Long id;
    private String name;
    private List<Message> listMessage;
    private String main;

    public ChannelDTO() {
    }

    public ChannelDTO(Long id, String name, List<Message> listMessage, String main) {
        this.id = id;
        this.name = name;
        this.listMessage = listMessage;
        this.main = main;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Message> getListMessage() {
        return listMessage;
    }

    public void setListMessage(List<Message> listMessage) {
        this.listMessage = listMessage;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    
}
