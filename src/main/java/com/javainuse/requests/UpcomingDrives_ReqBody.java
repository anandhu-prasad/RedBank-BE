package com.javainuse.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpcomingDrives_ReqBody {
    private String state;
    private String district;
    private Integer pincode;
}
