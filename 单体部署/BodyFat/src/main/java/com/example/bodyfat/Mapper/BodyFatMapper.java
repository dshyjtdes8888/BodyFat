package com.example.bodyfat.Mapper;

import com.example.bodyfat.Entity.BodyFat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BodyFatMapper {
    List<BodyFat> findAll();
}
