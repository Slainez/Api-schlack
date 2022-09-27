/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.m2i.apischlack.exception;
public class IllegalArgException extends IllegalArgumentException {

    public IllegalArgException() {
        super("Resource was not found");
    }

    public IllegalArgException(String message) {
        super(message);
    }
}