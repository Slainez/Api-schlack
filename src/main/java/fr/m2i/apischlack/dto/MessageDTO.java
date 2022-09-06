
package fr.m2i.apischlack.dto;

import fr.m2i.apischlack.model.Channel;
import java.util.Date;

/**
 *
 * @author moi
 */
public class MessageDTO {
    private Long id ;
    private String user ;
    private String content ;
    private Date timestamp;
    private ChannelDTO channel ;

    public MessageDTO() {
    }

    public MessageDTO(Long id, String user, String content, Date timestamp, ChannelDTO channel) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.timestamp = timestamp;
        this.channel = channel;
    }

    public MessageDTO(String user, String content, Date timestamp) {
        this.user = user;
        this.content = content;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public ChannelDTO getChannel() {
        return channel;
    }

    public void setChannel(ChannelDTO channel) {
        this.channel = channel;
    }

   
    
}
