package com.mljr.gps.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @Date : 2019/1/28$ 14:44$
 * @Author : liht
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParamKey {

    private String key;

    private String condition;
}
