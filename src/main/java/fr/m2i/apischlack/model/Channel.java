/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.m2i.apischlack.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="channels")
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy="channel", cascade=CascadeType.ALL)
    @Column(name="listMessage")
    private List<Message> listMessage;

    @Enumerated(EnumType.STRING)
    @Column(name="main", columnDefinition = "ENUM('MAIN', 'NOTMAIN', 'SUB') NOT NULL")
    private ChannelMain main;

    public Channel() {
    }

    public Channel(Long id, String name, List<Message> listMessage, ChannelMain main) {
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

    public ChannelMain getMain() {
        return main;
    }

    public void setMain(ChannelMain main) {
        this.main = main;
    }

    @Override
    public String toString() {
        return "Channel{" + "id=" + id + ", name=" + name + ", listMessage=" + listMessage + ", main=" + main + '}';
    }
    

}