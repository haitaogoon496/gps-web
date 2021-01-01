package com.mljr.gps.facade;

import com.lyqc.base.enums.ConArgTypeEnum;
import com.mljr.gps.entity.SyArgControl;
import com.mljr.gps.form.SyArgControlForm;
import com.mljr.gps.service.SyArgControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 数据字典Facade
 * @Date : 2018/6/19 下午5:34
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Service
public class SyArgControlFacade {

    @Autowired
    private SyArgControlService syArgControlService;

    /**
     * 根据业务类型查询枚举
     * @param argTypeEnum
     * @return
     */
    public List<SyArgControl> queryList(ConArgTypeEnum argTypeEnum) {
        SyArgControlForm form = new SyArgControlForm();
        form.setConArgType(argTypeEnum.getIndex());
        return syArgControlService.queryList(form);
    }

}
