package org.example.api;


import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.dto.ResponseDTO;
import org.example.model.User;
import org.example.service.UserService;
import org.example.util.ResponseUtil;
import org.jboss.resteasy.annotations.Body;

import java.util.Collections;

@Path("/user")
public class UserResource {

    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public ResponseDTO getUsers() {

        try {
            return ResponseUtil.createResponse(200,
                    "success",
                    userService.findAllUsers());
        } catch (Exception e) {
            return ResponseUtil.createResponse(500,
                    "Internal server error",
                    Collections.emptyList());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public ResponseDTO getUser(@PathParam("id") Long id) {
        //java 10的寫法自動推敲回傳類型
        var user = userService.findUserById(id);
        return ResponseUtil.createResponse(200,
                "success",
                user != null ? user : Collections.emptyList());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)   //客戶端傳到服務端
    @Produces(MediaType.APPLICATION_JSON)   //回傳格式
    public ResponseDTO insertUser(User user) {
        try {
            // 插入使用者並取得插入結果
            boolean createdUser = userService.createUser(user);

            if (createdUser) {
                //插入資料成功
                return ResponseUtil.createResponse(200,
                        "User created successfully",
                        Collections.emptyList());
            } else {
                //沒資料
                return ResponseUtil.createResponse(400,
                        "User already exists",
                        Collections.emptyList());
            }
        } catch (Exception e) {
            //意外處理
            return ResponseUtil.createResponse(500,
                    "Internal server error",
                    Collections.emptyList());
        }

    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)   //回傳格式
    @Consumes(MediaType.APPLICATION_JSON)   //客戶端傳到服務端
    @Path("/{id}")
    public ResponseDTO upDateUser(@PathParam("id") Long id, User user) {
        boolean upDateUser = userService.updateUser(id,user);
        try {
            if (upDateUser) {
                return ResponseUtil.createResponse(200,
                        "User update successfully",
                        Collections.emptyList());

            } else {
                //沒資料
                return ResponseUtil.createResponse(400,
                        "User update fail",
                        Collections.emptyList());
            }
        } catch (Exception e) {
            //意外處理
            return ResponseUtil.createResponse(500,
                    "Internal server error",
                    Collections.emptyList());
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)   //回傳格式
    @Path("/{id}")
    public ResponseDTO deleteUser(@PathParam("id") Long id) {
        boolean deleteUser = userService.deleteUser(id);
        try {
            if (deleteUser) {
                return ResponseUtil.createResponse(200,
                        "User delete successfully",
                        Collections.emptyList());
            } else {
                //沒資料
                return ResponseUtil.createResponse(400,
                        "User delete fail",
                        Collections.emptyList());
            }
        } catch (Exception e) {
            //意外處理
            return ResponseUtil.createResponse(500,
                    "Internal server error",
                    Collections.emptyList());
        }
    }
}
