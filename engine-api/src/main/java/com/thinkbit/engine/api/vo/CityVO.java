package com.thinkbit.engine.api.vo;

import lombok.Data;

import java.io.Serializable;


@Data
public class CityVO implements Serializable {

    /**
     * 城市名
     */
    private String cityName;

    /**
     * 所属省的名称
     */
    private String provinceName;


}
