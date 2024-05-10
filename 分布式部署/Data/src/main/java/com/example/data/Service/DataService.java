package com.example.data.Service;

import com.example.data.Entity.BodyFat;
import com.example.data.Mapper.BodyFatMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DataService {
    @Autowired
    private BodyFatMapper bodyFatMapper;

    @RabbitListener(queues = "body_queue")
    public String receiveMessage(String message) {
        System.out.println("Received message: " + message);

        if(Objects.equals(message, "findAll")) {
            // 处理消息并返回JSON响应
            List<BodyFat> bodyFatList = bodyFatMapper.findAll();
            // 将 List<BodyFat> 转换为 JSON 字符串
            return serializeToJson(bodyFatList);
        }
        return "";
    }

    private String serializeToJson(List<BodyFat> bodyFatList) {
        // 使用你喜欢的 JSON 库进行序列化
        // 以下示例使用 Jackson 库
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(bodyFatList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            // 处理异常
            return "";
        }
    }

}
