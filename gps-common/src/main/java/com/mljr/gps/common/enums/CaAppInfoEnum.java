package com.mljr.gps.common.enums;

import com.lyqc.base.enums.EnumValue;

/**
 * @Author：rongss
 * @Description
 * @Date：Created in 下午1:57 2018/2/18
 */
public class CaAppInfoEnum {

    public enum StatusEnum implements EnumValue {

        ALL(0, "全部", ""),
        CAAPP7(7, "初审已通过", "7"),
        CAAPP8(8, "准入已通过", "8"),
        /**
         * 已删除
         */
        CAAPP10(10, "已删除", "10"),
        /**
         * 已取消
         */
        CAAPP11(11, "已取消", "11"),
        /**
         * 已拒绝
         */
        CAAPP12(12, "已拒绝", "12"),
        /**
         * 新建待提交
         */
        CAAPP13(13, "新建待提交", "13"),
        /**
         * 复审退回修改
         */
        CAAPP14(14, "复审退回修改", "14"),
        /**
         * 贷前退回修改
         */
        CAAPP15(15, "贷前退回修改", "15"),
        /**
         * 贷后退回修改
         */
        CAAPP16(16, "贷后退回修改", "16"),
        /**
         * 等待上传贷前资料
         */
        CAAPP17(17, "等待上传贷前资料", "17"),
        /**
         * 等待上传贷后资料
         */
        CAAPP18(18, "等待上传贷后资料", "18"),
        /**
         * 等待放款
         */
        CAAPP19(19, "等待放款", "19"),
        /**
         * 等待初审领单
         */
        CAAPP20(20, "等待初审领单", "20"),
        /**
         * 初审审批
         */
        CAAPP21(21, "初审审批", "21"),
        /**
         * 等待复审领单
         */
        CAAPP22(22, "等待复审领单", "22"),
        /**
         * 复审审批
         */
        CAAPP23(23, "复审审批", "23"),
        /**
         * 退回复审审批
         */
        CAAPP30(30, "退回复审审批", "30"),
        /**
         * 终审领单
         */
        @Deprecated
        CAAPP33(33, "终审领单", "33"),
        /**
         * 终审审批
         */
        CAAPP24(24, "终审审批", "24"),
        /**
         * 等待贷前领单
         */
        CAAPP25(25, "等待贷前领单", "25"),
        /**
         * 贷前资料审批
         */
        CAAPP26(26, "贷前资料审批", "26"),
        /**
         * 等待贷后领单
         */
        CAAPP27(27, "等待贷后领单", "27"),
        /**
         * 贷后资料审批
         */
        CAAPP28(28, "贷后资料审批", "28"),
        /**
         * 等待放款
         */
        CAAPP29(29, "等待放款", "29"),
        /**
         * 风控验证中
         */
        CAAPP9(9, "风控验证中", "9"),
        /**
         * 放款成功
         */
        CAAPP32(32, "放款成功", "32");



        StatusEnum(int index, String name, String indexStr) {
            this.index = index;
            this.name = name;
            this.indexStr = indexStr;
        }

        private int index;

        private String indexStr;

        private String name;


        @Override
        public int getIndex() {
            return this.index;
        }


        @Override
        public String getName() {
            return this.name;
        }


        public void setIndex(int index) {
            this.index = index;
        }


        public String getIndexStr() {
            return indexStr;
        }


        public void setIndexStr(String indexStr) {
            this.indexStr = indexStr;
        }


        public void setName(String name) {
            this.name = name;
        }


        public static StatusEnum[] valuesNotNull(){
            return new StatusEnum[]{CAAPP7,CAAPP8,CAAPP9,CAAPP10,CAAPP11,CAAPP12,CAAPP13,CAAPP14,CAAPP15,CAAPP16,CAAPP17,CAAPP18,CAAPP19,CAAPP20,CAAPP21,CAAPP22,CAAPP23,CAAPP24,CAAPP25,CAAPP26,CAAPP27,CAAPP28,CAAPP29,CAAPP32};
        }


        /**
         * 根据索引获取名称
         * @param index 索引
         * @return
         */
        public static String getNameByIndex(int index) {
            for (CaAppInfoEnum.StatusEnum e : CaAppInfoEnum.StatusEnum.values()) {
                if (e.getIndex() == index) {
                    return e.getName();
                }
            }
            return null;
        }


        /**
         * 根据索引获取枚举对象
         * @param index 索引
         * @return
         */
        public static CaAppInfoEnum.StatusEnum getByIndex(int index) {
            for (CaAppInfoEnum.StatusEnum e : CaAppInfoEnum.StatusEnum.values()) {
                if (e.getIndex() == index) {
                    return e;
                }
            }
            return null;
        }
    }

    public enum SendApprEnum implements EnumValue {

        /**
         * 默认
         */
        INIT(0, "默认", "0"),
        /**
         * 复审发送有用
         */
        SECOND_SEND(1, "复审发送有用", "1"),
        /**
         * 核查
         */
        MUST_EXAMINE(6, "核查", "6"),
        /**
         * 已拒绝
         */
        MIDDLE(9, "已拒绝", "9");

        SendApprEnum(int index, String name, String indexStr) {
            this.index = index;
            this.name = name;
            this.indexStr = indexStr;
        }

        private int index;

        private String indexStr;

        private String name;


        @Override
        public int getIndex() {
            return this.index;
        }


        @Override
        public String getName() {
            return this.name;
        }


        public void setIndex(int index) {
            this.index = index;
        }


        public String getIndexStr() {
            return indexStr;
        }


        public void setIndexStr(String indexStr) {
            this.indexStr = indexStr;
        }


        public void setName(String name) {
            this.name = name;
        }


        /**
         * 根据索引获取名称
         * @param index 索引
         * @return
         */
        public static String getNameByIndex(int index) {
            for (CaAppInfoEnum.SendApprEnum e : CaAppInfoEnum.SendApprEnum.values()) {
                if (e.getIndex() == index) {
                    return e.getName();
                }
            }
            return null;
        }


        /**
         * 根据索引获取枚举对象
         * @param index 索引
         * @return
         */
        public static CaAppInfoEnum.SendApprEnum getByIndex(int index) {
            for (CaAppInfoEnum.SendApprEnum e : CaAppInfoEnum.SendApprEnum.values()) {
                if (e.getIndex() == index) {
                    return e;
                }
            }
            return null;
        }
    }

    public enum AssureEnum implements EnumValue {

        /**
         * 无担保人
         */
        NO(0, "无担保人"),
        /**
         * 个人担保
         */
        PERSONAL(1, "个人担保"),
        /**
         * 企业担保
         */
        COMPANY(2, "企业担保");

        AssureEnum(int index, String name) {
            this.index = index;
            this.name = name;
        }

        private int index;

        private String name;


        @Override
        public int getIndex() {
            return this.index;
        }


        @Override
        public String getName() {
            return this.name;
        }


        public void setIndex(int index) {
            this.index = index;
        }


        public void setName(String name) {
            this.name = name;
        }


        /**
         * 根据索引获取名称
         * @param index 索引
         * @return
         */
        public static String getNameByIndex(int index) {
            for (CaAppInfoEnum.AssureEnum e : CaAppInfoEnum.AssureEnum.values()) {
                if (e.getIndex() == index) {
                    return e.getName();
                }
            }
            return null;
        }


        /**
         * 根据索引获取枚举对象
         * @param index 索引
         * @return
         */
        public static CaAppInfoEnum.AssureEnum getByIndex(int index) {
            for (CaAppInfoEnum.AssureEnum e : CaAppInfoEnum.AssureEnum.values()) {
                if (e.getIndex() == index) {
                    return e;
                }
            }
            return null;
        }
    }

    public enum ComeFromEnum implements EnumValue {

        PC(0, "PC"), APP(1, "APP"), WEIXIN(2, "WEIXIN");

        ComeFromEnum(int index, String name) {
            this.index = index;
            this.name = name;
        }

        private int index;

        private String name;


        @Override
        public int getIndex() {
            return this.index;
        }


        @Override
        public String getName() {
            return this.name;
        }


        public void setIndex(int index) {
            this.index = index;
        }


        public void setName(String name) {
            this.name = name;
        }


        /**
         * 根据索引获取名称
         * @param index 索引
         * @return
         */
        public static String getNameByIndex(int index) {
            for (CaAppInfoEnum.ComeFromEnum e : CaAppInfoEnum.ComeFromEnum.values()) {
                if (e.getIndex() == index) {
                    return e.getName();
                }
            }
            return null;
        }


        /**
         * 根据索引获取枚举对象
         * @param index 索引
         * @return
         */
        public static CaAppInfoEnum.ComeFromEnum getByIndex(int index) {
            for (CaAppInfoEnum.ComeFromEnum e : CaAppInfoEnum.ComeFromEnum.values()) {
                if (e.getIndex() == index) {
                    return e;
                }
            }
            return null;
        }
    }

    /**
     * GPS费用是否贷款
     */
    public enum IsGpsLoanEnum implements EnumValue {

        NO(0, "NO"), YES(1, "YES");

        IsGpsLoanEnum(int index, String name) {
            this.index = index;
            this.name = name;
        }

        private int index;

        private String name;


        @Override
        public int getIndex() {
            return this.index;
        }


        @Override
        public String getName() {
            return this.name;
        }


        public void setIndex(int index) {
            this.index = index;
        }


        public void setName(String name) {
            this.name = name;
        }


        /**
         * 根据索引获取名称
         * @param index 索引
         * @return
         */
        public static String getNameByIndex(int index) {
            for (CaAppInfoEnum.IsGpsLoanEnum e : CaAppInfoEnum.IsGpsLoanEnum.values()) {
                if (e.getIndex() == index) {
                    return e.getName();
                }
            }
            return null;
        }


        /**
         * 根据索引获取枚举对象
         * @param index 索引
         * @return
         */
        public static CaAppInfoEnum.IsGpsLoanEnum getByIndex(int index) {
            for (CaAppInfoEnum.IsGpsLoanEnum e : CaAppInfoEnum.IsGpsLoanEnum.values()) {
                if (e.getIndex() == index) {
                    return e;
                }
            }
            return null;
        }
    }

    /**
     * 是否公牌 0否 1是
     */
    public enum IsCompanyLicenseEnum implements EnumValue {

        NO(0, "NO"), YES(1, "YES");

        IsCompanyLicenseEnum(int index, String name) {
            this.index = index;
            this.name = name;
        }

        private int index;

        private String name;


        @Override
        public int getIndex() {
            return this.index;
        }


        @Override
        public String getName() {
            return this.name;
        }


        public void setIndex(int index) {
            this.index = index;
        }


        public void setName(String name) {
            this.name = name;
        }


        /**
         * 根据索引获取名称
         * @param index 索引
         * @return
         */
        public static String getNameByIndex(int index) {
            for (CaAppInfoEnum.IsCompanyLicenseEnum e : CaAppInfoEnum.IsCompanyLicenseEnum.values()) {
                if (e.getIndex() == index) {
                    return e.getName();
                }
            }
            return null;
        }


        /**
         * 根据索引获取枚举对象
         * @param index 索引
         * @return
         */
        public static CaAppInfoEnum.IsCompanyLicenseEnum getByIndex(int index) {
            for (CaAppInfoEnum.IsCompanyLicenseEnum e : CaAppInfoEnum.IsCompanyLicenseEnum.values()) {
                if (e.getIndex() == index) {
                    return e;
                }
            }
            return null;
        }
    }

    /**
     * 是否二手车 0新车 1二手车
     */
    public enum IsOldEnum implements EnumValue {

        NEW(0, "NEW"), OLD(1, "OLD");

        IsOldEnum(int index, String name) {
            this.index = index;
            this.name = name;
        }

        private int index;

        private String name;


        @Override
        public int getIndex() {
            return this.index;
        }


        @Override
        public String getName() {
            return this.name;
        }


        public void setIndex(int index) {
            this.index = index;
        }


        public void setName(String name) {
            this.name = name;
        }


        /**
         * 根据索引获取名称
         * @param index 索引
         * @return
         */
        public static String getNameByIndex(int index) {
            for (CaAppInfoEnum.IsOldEnum e : CaAppInfoEnum.IsOldEnum.values()) {
                if (e.getIndex() == index) {
                    return e.getName();
                }
            }
            return null;
        }


        /**
         * 根据索引获取枚举对象
         * @param index 索引
         * @return
         */
        public static CaAppInfoEnum.IsOldEnum getByIndex(int index) {
            for (CaAppInfoEnum.IsOldEnum e : CaAppInfoEnum.IsOldEnum.values()) {
                if (e.getIndex() == index) {
                    return e;
                }
            }
            return null;
        }
    }

    public enum LoanFileStatusEnum {

        STATUS_WAIT_UPLOAD("0", "待上传"), STATUS_PASS("1", "审核通过"), STATUS_REFUSE("2", "审核退回"), STATUS_UPLOAD("3", "上传成功"), STATUS_WAIT_APPROVAL("4", "待审核"),
        /**
         * 特殊状态，可直接进入待放列表
         */
        STATUS_WAIT_REPEAT("5", "待补充上传资料"), STATUS_NEW_ORDER("6", "作为新单请款");


        private String value;

        private String name;


        LoanFileStatusEnum(String value, String name) {
            this.name = name;
            this.value = value;
        }


        public String getName() {
            return this.name;
        }


        public String getValue() {
            return this.value;
        }

    }

    /**
     * 是否查找央行征信 0不 1查
     */
    public enum AuthYhEnum implements EnumValue {

        NO(0, "不查"),
        YES(1, "查找");

        AuthYhEnum(int index, String name) {
            this.index = index;
            this.name = name;
        }

        private int index;

        private String name;


        @Override
        public int getIndex() {
            return this.index;
        }


        @Override
        public String getName() {
            return this.name;
        }


        public void setIndex(int index) {
            this.index = index;
        }


        public void setName(String name) {
            this.name = name;
        }

    }

    /**
     * 0 未拒绝 1 拒绝复核未通过 2 拒绝复核通过 3 拒绝待复核
     */
    public enum RefuseStatusEnum implements EnumValue {

        NO_REFUSE(0,"未拒绝"),
        REVIEW_REFUSE(1,"拒绝复核不通过"),
        FINAL_REFUSE(2, "终审拒绝"),
        SECOND_REFUSE(3, "复审拒绝");

        RefuseStatusEnum(int index, String name) {
            this.index = index;
            this.name = name;
        }

        private int index;

        private String name;


        @Override
        public int getIndex() {
            return this.index;
        }


        @Override
        public String getName() {
            return this.name;
        }


        public void setIndex(int index) {
            this.index = index;
        }


        public void setName(String name) {
            this.name = name;
        }


        /**
         * 根据索引获取名称
         * @param index 索引
         * @return
         */
        public static String getNameByIndex(int index) {
            for (CaAppInfoEnum.RefuseStatusEnum e : CaAppInfoEnum.RefuseStatusEnum.values()) {
                if (e.getIndex() == index) {
                    return e.getName();
                }
            }
            return null;
        }


        /**
         * 根据索引获取枚举对象
         * @param index 索引
         * @return
         */
        public static CaAppInfoEnum.RefuseStatusEnum getByIndex(int index) {
            for (CaAppInfoEnum.RefuseStatusEnum e : CaAppInfoEnum.RefuseStatusEnum.values()) {
                if (e.getIndex() == index) {
                    return e;
                }
            }
            return null;
        }
    }

}
