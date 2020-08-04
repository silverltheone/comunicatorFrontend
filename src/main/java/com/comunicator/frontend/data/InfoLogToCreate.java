package com.comunicator.frontend.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public final class InfoLogToCreate {

    public Long userId;
    public String date;
    public String type;
}
