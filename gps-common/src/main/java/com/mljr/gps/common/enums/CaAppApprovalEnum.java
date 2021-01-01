package com.mljr.gps.common.enums;

/**
 * @Author：rongss
 * @Description
 * @Date：Created in 上午2:14 2018/2/21
 */
public class CaAppApprovalEnum {

    /**
     * 审批流程序号
     */
    public enum FlowNoEnum {
        ACCEPT(1, "领单"),
        APPROVAL(2, "审批"),
        SYSRTEM_BACK(3,"系统退回");

        private int index;
        private String name;

        FlowNoEnum(int index, String name) {
            this.index = index;
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public String getName() {
            return name;
        }

        public static String getNameByIndex(int index){
            for(CaAppApprovalEnum.FlowNoEnum e : CaAppApprovalEnum.FlowNoEnum.values()){
                if(e.getIndex() == index){
                    return e.getName();
                }
            }
            return null;
        }

        public static CaAppApprovalEnum.FlowNoEnum getByIndex(int index){
            for(CaAppApprovalEnum.FlowNoEnum e : CaAppApprovalEnum.FlowNoEnum.values()){
                if(e.getIndex() == index){
                    return e;
                }
            }
            return null;
        }
    }


    /**
     * 审批状态类型
     */
    public enum TypeEnum {
        //申请单状态
        CAAPP9(9,"取消","9"),
        CAAPP10(10,"正常审批中","10"),
        CAAPP11(11,"通过","11"),
        CAAPP12(12,"拒绝","12"),
        CAAPP13(13,"退回经销商修改","13"),
        CAAPP14(14,"退回审批","14"),
        CAAPP15(15,"复审拒绝","15"),
        CAAPP16(16,"复核通过","16"),
        CAAPP17(17,"拒绝复核未通过","17"),
        CAAPPNULL(-1,"","");//异常操作

        private int index;
        private String name;
        private String indexStr;

        TypeEnum(int index, String name,String indexStr) {
            this.index = index;
            this.name = name;
            this.indexStr = indexStr;
        }

        public String getIndexStr() {
            return indexStr;
        }

        public int getIndex() {
            return index;
        }

        public String getName() {
            return name;
        }

        public static String getNameByIndex(int index){
            for(CaAppApprovalEnum.TypeEnum e : CaAppApprovalEnum.TypeEnum.values()){
                if(e.getIndex() == index){
                    return e.getName();
                }
            }
            return null;
        }

        public static CaAppApprovalEnum.TypeEnum getByIndex(int index){
            for(CaAppApprovalEnum.TypeEnum e : CaAppApprovalEnum.TypeEnum.values()){
                if(e.getIndex() == index){
                    return e;
                }
            }
            return null;
        }
    }
}
