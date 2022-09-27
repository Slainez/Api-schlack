/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.m2i.apischlack.controller;

import fr.m2i.apischlack.dto.ChannelDTO;
import fr.m2i.apischlack.dto.ChannelMapper;
import fr.m2i.apischlack.exception.NotFoundException;
import fr.m2i.apischlack.model.Channel;
import fr.m2i.apischlack.response.ErrorResponseEntity;
import fr.m2i.apischlack.service.IChannelService;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/channels")
public class ChannelController {
    private final IChannelService channelService;

    @Autowired
    public ChannelController(IChannelService channelService) {
        this.channelService = channelService;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Returns the list of all channels", nickname = "Get all channels", response = ChannelDTO.class)
    public ResponseEntity<Object> getAllChannel() {
        List<Channel> channels = channelService.findAll();
        List<ChannelDTO> dtos = new ArrayList();

        for (Channel Channel : channels) {
            ChannelDTO dto = ChannelMapper.buildChannelDTO(Channel);
            dtos.add(dto);
        }

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    
    // findById
    
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(value = "Create an Channel", nickname = "Create an Channel", response = ChannelDTO.class)
    public ResponseEntity<Object> createChannel(@RequestBody ChannelDTO dto) {
        try {
            Channel toCreate = ChannelMapper.buildChannel(dto);
            Channel created = channelService.save(toCreate);
            ChannelDTO createdDTO = ChannelMapper.buildChannelDTO(created);

            return ResponseEntity.status(HttpStatus.OK).body(createdDTO);

        } catch (Exception e) {
            return ErrorResponseEntity.build("An error occured", 500, "/v1/channels", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(value = "update a Channel", nickname = "Update an Channel by id", response = ChannelDTO.class)
    public ResponseEntity<Object> updateChannel(@PathVariable("id") String id,
            @RequestBody ChannelDTO dto) {
        
        try {
            Long ChannelId = Long.parseLong(id);
            Channel toUpdate = ChannelMapper.buildChannel(dto);
            Channel updated = channelService.update(ChannelId, toUpdate);
            ChannelDTO updatedDTO = ChannelMapper.buildChannelDTO(updated);

            return ResponseEntity.status(HttpStatus.OK).body(updatedDTO);

        } catch (NumberFormatException ne) {
            return ErrorResponseEntity.build("The parameter 'id' is not valid", 400, "/v1/channels/" + id, HttpStatus.BAD_REQUEST);
        } catch (NotFoundException nfe) {
            return ErrorResponseEntity.build("Channel was not found", 404, "/v1/channels/" + id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ErrorResponseEntity.build("An error occured", 500, "/v1/channels/" + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return a Channel", nickname = "Get a Channel by id", response = ChannelDTO.class)
    public ResponseEntity<Object> getChannelById(@PathVariable("id") String id) {
        
        try {
            Long ChannelId = Long.parseLong(id);
            Channel founded = channelService.findById(ChannelId);
            ChannelDTO dto = ChannelMapper.buildChannelDTO(founded);

            return ResponseEntity.status(HttpStatus.OK).body(dto);

        } catch (NumberFormatException ne) {
            return ErrorResponseEntity.build("The parameter 'id' is not valid", 400, "/v1/channels/" + id, HttpStatus.BAD_REQUEST);
        } catch (NotFoundException nfe) {
            return ErrorResponseEntity.build("Channel was not found", 404, "/v1/channels/" + id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ErrorResponseEntity.build("An error occured", 500, "/v1/channels/" + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   @DeleteMapping(value = "/{id}")
   @ApiOperation(value = "delete an Channel", nickname = "Delete an Channel by id", code = 204)
    public ResponseEntity<Object> deleteChannel(@PathVariable("id") String id) {
        try {
            Long ChannelId = Long.parseLong(id);
            channelService.delete(ChannelId);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        } catch (NumberFormatException ne) {
            return ErrorResponseEntity.build("The parameter 'id' is not valid", 400, "/v1/channels/" + id, HttpStatus.BAD_REQUEST);
        } catch (NotFoundException nfe) {
            return ErrorResponseEntity.build("Channel was not found", 404, "/v1/channels/" + id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ErrorResponseEntity.build("An error occured", 500, "/v1/channels/" + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}