package com.example.mtb.utility;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class ResponseStructure<T> {
    private int statuscode;
    private String message;
    private T data;
    ;

}


