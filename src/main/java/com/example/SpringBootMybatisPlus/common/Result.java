package com.example.SpringBootMybatisPlus.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private Integer code;//状态
    private String message;//对错的信息
    private T data;//数据
    public static final int SUCCESS=20000;
    public static final int ERROR=20001;
    public static<T>  Result<T> success(){
        return new Result<>(SUCCESS,"success",null);
    }

    public static<T>  Result<T> success(T data){
        return new Result<>(SUCCESS,"success",data);
    }

    public static<T>  Result<T> success(T data, String message){
        return new Result<>(SUCCESS,message,data);
    }

    public static<T>  Result<T> success(String message){
        return new Result<>(SUCCESS,message,null);
    }

    public static<T>  Result<T> fail(){
        return new Result<>(ERROR,"fail",null);
    }

    public static<T>  Result<T> fail(Integer code){
        return new Result<>(code,"fail",null);
    }

    public static<T>  Result<T> fail(Integer code, String message){
        return new Result<>(code,message,null);
    }

    public static<T>  Result<T> fail( String message){
        return new Result<>(ERROR,message,null);
    }

}
