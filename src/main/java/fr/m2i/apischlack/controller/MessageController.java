/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.m2i.apischlack.controller;
import fr.m2i.apischlack.dto.MessageDTO;
import fr.m2i.apischlack.dto.MessageMapper;
import fr.m2i.apischlack.exception.NotFoundException;
import fr.m2i.apischlack.exception.IllegalArgException;
import fr.m2i.apischlack.model.Message;
import fr.m2i.apischlack.response.ErrorResponseEntity;
import fr.m2i.apischlack.service.IMessageService;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ben
 */
@RestController
@RequestMapping("/v1/messages")
public class MessageController {
    
    
    private final IMessageService messageService;
    
    @Autowired
    public MessageController(IMessageService messageService) {
        this.messageService = messageService;
    }
    
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Returns the list of all messages from a channel", nickname = "Get all messages from channel", response = MessageDTO.class)
    public ResponseEntity<Object> getAllMessageFromChannel(@PathVariable("id") String id) {
        try {
            Long ChanId = Long.parseLong(id);
            List<Message> messages = messageService.findAllMessageByChannel(ChanId);
//            if (messages.isEmpty()) {
//                return ErrorResponseEntity.build("Channel was not found", 404, "/v1/channels/" + id, HttpStatus.NOT_FOUND);
//            }
            List<MessageDTO> dtos = new ArrayList();

            for (Message message : messages) {
                MessageDTO dto = MessageMapper.buildMessageDTO(message);
                dtos.add(dto);
            }

            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        } catch (NumberFormatException ne) {
            return ErrorResponseEntity.build("The parameter 'id' is not valid", 400, "/messages/" + id, HttpStatus.BAD_REQUEST);
        } catch (NotFoundException nfe) {
            return ErrorResponseEntity.build("Channel was not found", 404, "/messages/" + id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ErrorResponseEntity.build("An error occured", 500, "/messages/" + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // findById
    
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(value = "Create a message", nickname = "Create a message", response = MessageDTO.class)
    public ResponseEntity<Object> createMessage(@RequestBody MessageDTO dto) {
        try {
            if(dto.getId() == null){
            Message toCreate = MessageMapper.buildMessage(dto);
            Message created = messageService.save(toCreate);
            MessageDTO createdDTO = MessageMapper.buildMessageDTO(created);

            return ResponseEntity.status(HttpStatus.OK).body(createdDTO);
            }else{
            return ErrorResponseEntity.build("The parameter 'id' must be remove", 400, "/messages/", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return ErrorResponseEntity.build("An error occured", 500, "/messages", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(value = "update a message", nickname = "Update a message by id", response = MessageDTO.class)
    public ResponseEntity<Object> updateMessage(@PathVariable("id") String id,
            @RequestBody MessageDTO dto) {
        
        try {
            Long messageId = Long.parseLong(id);
            Message toUpdate = MessageMapper.buildMessage(dto);
            Message updated = messageService.update(messageId, toUpdate);
            MessageDTO updatedDTO = MessageMapper.buildMessageDTO(updated);

            return ResponseEntity.status(HttpStatus.OK).body(updatedDTO);

        } catch (NumberFormatException ne) {
            return ErrorResponseEntity.build("The parameter 'id' is not valid", 400, "/messages/" + id, HttpStatus.BAD_REQUEST);
        } catch (NotFoundException nfe) {
            return ErrorResponseEntity.build("Message was not found", 404, "/messages/" + id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.toString());
            return ErrorResponseEntity.build("An error occured", 500, "/messages/" + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @GetMapping(value = "/message/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return a message", nickname = "Get a message by id", response = MessageDTO.class)
    public ResponseEntity<Object> getMessageById(@PathVariable("id") String id) {
        
        try {
            Long messageId = Long.parseLong(id);
            Message founded = messageService.MessageFindById(messageId);
            MessageDTO dto = MessageMapper.buildMessageDTO(founded);

            return ResponseEntity.status(HttpStatus.OK).body(dto);

        } catch (NumberFormatException ne) {
            return ErrorResponseEntity.build("The parameter 'id' is not valid", 400, "/messages/" + id, HttpStatus.BAD_REQUEST);
        } catch (NotFoundException nfe) {
            return ErrorResponseEntity.build("Message was not found", 404, "/messages/" + id, HttpStatus.NOT_FOUND);
        } catch (NullPointerException npe) {
            return ErrorResponseEntity.build("Message was not found", 404, "/messages/" + id, HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            return ErrorResponseEntity.build("An error occured", 500, "/messages/" + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
   @DeleteMapping(value = "/{id}")
   @ApiOperation(value = "delete a message", nickname = "Delete a message by id", code = 204)
    public ResponseEntity<Object> deleteMessage(@PathVariable("id") String id) {
        try {
            Long messageId = Long.parseLong(id);
            if (messageService.delete(messageId)){
                return ResponseEntity.status(HttpStatus.OK).body("Message was successfully deleted");
            }else{
                return ErrorResponseEntity.build("Message was not found", 404, "/messages/" + id, HttpStatus.NOT_FOUND);
            }
  

        } catch (NumberFormatException ne) {
            return ErrorResponseEntity.build("The parameter 'id' is not valid", 400, "/messages/" + id, HttpStatus.BAD_REQUEST);
        } catch (NotFoundException nfe) {
            return ErrorResponseEntity.build("Message was not found", 404, "/messages/" + id, HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            System.out.println("e "+e);
            return ErrorResponseEntity.build("An error occured", 500, "/messages/" + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}