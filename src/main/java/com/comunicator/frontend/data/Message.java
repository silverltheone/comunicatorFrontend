package com.comunicator.frontend.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private Long id;
    private User sender;
    private User receiver;
    private LocalDate sendDate;
    private String message;
    private boolean wasRead;
}
