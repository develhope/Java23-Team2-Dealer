package com.develhope.spring.deals.dtos;

public class DeleteOrderResponseDTO {
    private String message;

    public DeleteOrderResponseDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
