/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.m2i.apischlack.controller;
import fr.m2i.apischlack.dto.MessageDTO;
import fr.m2i.apischlack.dto.MessageMapper;
import fr.m2i.apischlack.model.Message;
import fr.m2i.apischlack.response.ErrorResponseEntity;
import fr.m2i.apischlack.service.IMessageService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class WebSocketChatController {
private final IMessageService messageService;

    @Autowired
    public WebSocketChatController(IMessageService messageService) {
        this.messageService = messageService;
    }
                

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public List<MessageDTO> greeting(Long ChanId) throws Exception {
            System.out.println("ChanId   "+ChanId);
            List<Message> messages = messageService.findAllMessageByChannel(ChanId);
            List<MessageDTO> dtos = new ArrayList();
            for (Message msg : messages) {
                MessageDTO dto = MessageMapper.buildMessageDTO(msg);
                dtos.add(dto);
            }
            return dtos;
    }
}