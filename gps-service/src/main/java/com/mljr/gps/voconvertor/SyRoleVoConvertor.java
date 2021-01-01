package com.mljr.gps.voconvertor;

import com.mljr.gps.common.util.StringUtils;
import com.mljr.gps.entity.SyRole;
import com.mljr.gps.vo.SyRoleVo;

/**
 * @description:
 * @Date : 2019/1/10$ 14:12$
 * @Author : liht
 */
public class SyRoleVoConvertor extends VoConvertor<SyRoleVo,SyRole> {
    @Override
    public SyRoleVo convert(SyRole bo) {
        SyRoleVo roleVo = new SyRoleVo();
        roleVo.setRoleName(StringUtils.killNull(bo.getRoleName()));
        roleVo.setRoleId(StringUtils.killNull(bo.getRoleId()));
        roleVo.setRoleType(StringUtils.killNull(bo.getRoleType()));
        roleVo.setValidFalg(StringUtils.killNull(bo.getValidFalg()));
        roleVo.setRoleDesc(StringUtils.killNull(bo.getRoleDesc()));

        return roleVo;
    }
}
