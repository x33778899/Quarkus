package org.example.util;

import org.example.dto.ResponseDTO;

//減少重複使用率
public class ResponseUtil {
    public static ResponseDTO createResponse(int statusCode, String message, Object data) {
        ResponseDTO rs = new ResponseDTO();
        rs.setStatusCode(statusCode);
        rs.setMessage(message);
        rs.setData(data);
        return rs;
    }
}