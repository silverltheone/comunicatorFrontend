package com.comunicator.frontend.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InvitationToCreate {

    public Long senderId;
    public Long receiverId;
    public String sendDate;
    public Boolean wasRead;
}
