package com.mljr.gps.vo;

import lombok.Data;

/**
 * 根据vin码查车型
 * @author Cyn
 *
 */
@Data
public class VehicleVo {
	/**
	 * 厂商名称
	 */
	private String companyName;
	/**
	 * 品牌名称
	 */
	private String brandName;
	/**
	 * 车系名称
	 */
	private String familyName;
	/**
	 * 车款
	 */
	private String groupName;
	/**
	 * 车型编码
	 */
	private String vehicleCode;
	/**
	 * 车型名称
	 */
	private String vehicleName;
	/**
	 * 排量
	 */
	private String displacement;
	/**
	 * 额定座位数
	 */
	private String seat;
	/**
	 * 新车购置价
	 */
	private String purchasePrice;
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 变速器类型
	 */
	private String gearboxType;
	/**
	 * 年款
	 */
	private String yearPattern;
	/**
	 * 额定载质量
	 */
	private String tonnage;

}
