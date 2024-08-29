package org.example.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.dto.ProductDTO;
import org.example.dto.ResponseDTO;
import org.example.interceptor.Loggable;
import org.example.model.Product;
import org.example.model.User;
import org.example.service.ProductService;
import org.example.service.UserService;
import org.example.util.ResponseUtil;

import java.util.Collections;

@Path("/product")
@Loggable
public class ProductResource {

    @Inject
    ProductService productService;

    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public ResponseDTO getProducts() {

        try {
            return ResponseUtil.createResponse(200,
                    "success",
                    productService.getAllProducts());
        } catch (Exception e) {
            return ResponseUtil.createResponse(500,
                    "Internal server error",
                    Collections.emptyList());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userId}")
    public ResponseDTO getProduct(@PathParam("userId") Long userId) {

        try {
            System.out.println("rs  "+productService.getProductByUserId(userId));

            return ResponseUtil.createResponse(200,
                    "success",
                    productService.getProductByUserId(userId));
        } catch (Exception e) {
            return ResponseUtil.createResponse(500,
                    "Internal server error",
                    Collections.emptyList());
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)   //客戶端傳到服務端
    @Produces(MediaType.APPLICATION_JSON)   //回傳格式
    public ResponseDTO insertUser(ProductDTO productDTO) {
        try {
            String name = productDTO.getName();
            Double price = productDTO.getPrice();
            Long userId = productDTO.getUserId();
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            User user = userService.findUserById(userId);

            product.setUser(user);
            // 插入使用者並取得插入結果
            boolean createdProduct = productService.createProduct(product);

            if (createdProduct) {
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
}
