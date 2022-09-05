/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.m2i.apischlack.exception;
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("Resource was not found");
    }

    public NotFoundException(String message) {
        super(message);
    }
}