package com.mljr.gps.voconvertor;

import com.mljr.gps.entity.SyUser;
import com.mljr.gps.vo.SyUserVo;
import org.springframework.beans.BeanUtils;

/**
 * @description:
 * @Date : 2019/1/10$ 14:55$
 * @Author : liht
 */
public class SyUserVoConvertor extends VoConvertor<SyUserVo, SyUser> {
    @Override
    public SyUserVo convert(SyUser bo) {
        SyUserVo syUserVo = new SyUserVo();
        BeanUtils.copyProperties(bo, syUserVo);
        return syUserVo;
    }
}
