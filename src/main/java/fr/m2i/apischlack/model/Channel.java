/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.m2i.apischlack.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="channels")
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="listMessage")
    private List<Object> listMessage;


    @Column(name="main", columnDefinition = "TINYINT(1) DEFAULT 1 NOT NULL")
    private Boolean main;

    public Channel() {
    }

    public Channel(Long id, String name, List<Object> listMessage, Boolean main) {
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

    public List<Object> getListMessage() {
        return listMessage;
    }

    public void setListMessage(List<Object> listMessage) {
        this.listMessage = listMessage;
    }

    public Boolean getMain() {
        return main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }

    @Override
    public String toString() {
        return "Channel{" + "id=" + id + ", name=" + name + ", listMessage=" + listMessage + ", main=" + main + '}';
    }
    

}