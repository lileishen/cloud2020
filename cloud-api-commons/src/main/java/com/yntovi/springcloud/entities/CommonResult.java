package com.yntovi.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {
    //404 not folud

    private  Integer code;
    private  String message;
    private T data;

    public CommonResult(Integer code,String message){
        this(code,message,null);
    }

}
