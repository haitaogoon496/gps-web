package com.mljr.gps.service.impl;

import com.mljr.gps.entity.SyItem;
import com.mljr.gps.mapper.SyItemMapper;
import com.mljr.gps.service.SyItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @Date : 2018/8/17$ 11:16$
 * @Author : liht
 */
@Service
public class SyItemServiceImpl implements SyItemService {

    @Resource
    private SyItemMapper syItemMapper;


    @Override
    public List<SyItem> selectMenuItemByRoles(List<Integer> list) {
        return syItemMapper.selectMenuItemByRoles(list);
    }

}
