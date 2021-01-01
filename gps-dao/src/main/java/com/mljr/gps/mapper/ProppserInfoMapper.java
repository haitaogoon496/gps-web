package com.mljr.gps.mapper;

import com.mljr.gps.entity.ProppserInfo;
import com.mljr.gps.form.GpsQueryListForm;
import com.mljr.gps.vo.GpsQueryListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProppserInfoMapper {
    /**
     *
     * @mbggenerated 2017-11-23
     */
    int deleteByPrimaryKey(Integer proppserId);

    /**
     *
     * @mbggenerated 2017-11-23
     */
    int insert(ProppserInfo record);

    /**
     *
     * @mbggenerated 2017-11-23
     */
    int insertSelective(ProppserInfo record);

    /**
     *
     * @mbggenerated 2017-11-23
     */
    ProppserInfo selectByPrimaryKey(Integer proppserId);

    /**
     *
     * @mbggenerated 2017-11-23
     */
    int updateByPrimaryKeySelective(ProppserInfo record);

    /**
     *
     * @mbggenerated 2017-11-23
     */
    int updateByPrimaryKey(ProppserInfo record);

    /**
     * 通过列表查询
     * @param proppserIds
     * @return
     */
    List<ProppserInfo> listByProppserIds(@Param("proppserIds") List<Integer> proppserIds);

    /**
     *
     * @param form
     * @return
     */
    List<GpsQueryListVo> listByForm(GpsQueryListForm form);

}