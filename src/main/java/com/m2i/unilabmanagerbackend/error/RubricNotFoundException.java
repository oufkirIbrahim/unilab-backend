package com.m2i.unilabmanagerbackend.error;

import jakarta.persistence.Access;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;



public class RubricNotFoundException extends Exception{

    public RubricNotFoundException(){
        super();
    }
    public RubricNotFoundException(String message){
        super(message);
    }
    public RubricNotFoundException(Throwable cause){
        super(cause);
    }
    public RubricNotFoundException(String message, Throwable cause){
        super(message,cause);
    }

    protected RubricNotFoundException(String message, Throwable cause,
                                      boolean enableSuppression,
                                      boolean writableStackTrace){
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
