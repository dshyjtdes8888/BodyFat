package com.example.data.Mapper;

import com.example.data.Entity.BodyFat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BodyFatMapper {
    List<BodyFat> findAll();
}
