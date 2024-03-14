package com.example.secclient.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataResponse<T> extends BaseResponse {
    private T data;

    public DataResponse(boolean status, String status_text, T data) {
        super(status, status_text);
        this.data = data;
    }
}
