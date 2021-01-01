[TOC]

## 系统概述
车辆GPS审批平台
### 1、APP销售端
>APP销售能够提交GPS，是发生在贷前或者贷后阶段，该阶段也可以意味着相当于流程的终身之后。其中，销售安装GPS的数量是通过ca_car_info中
gps_pro、gps_count、gps_dealer三个字段决定 ，而这三个字段的写入是销售APP提交准入或者复审通过之后，通过调用car-product提供的
/productEngine/call接口返回相应贷款申请单根据GPS档位返回的。

#### 1.1、APP发生阶段
>该阶段GPS初始化状态为"未审核"，如果提交后，状态为"审核中"。

![](docs/images/app-1.png '贷前或者贷后阶段')

#### 1.2、销售提交

![](docs/images/app-2.png '主界面')

如上图，GPS包含安装单和图片两部分，其中GPS安装单，需要根据申请单安装对应数量的GPS设备，而图片资料则需要根据安装的位置拍摄相关图片以供
GPS审核人员审核安装操作规范。

![](docs/images/app-3.png '安装单')

如上图，选择对应设备商，填写设备号，保存后，会调用simba==>gps-provider==>大数据==>赛格/久劲，返回GPS设备的状态。

![](docs/images/app-4.png '图片')

拍摄相关图片上传，最后GPS提交。

#### 1.3、数据流
+ 1、GPS保存会插入ca_car_gps表。
+ 2、GPS提交会插入ca_gps_flow、ca_gps_history、ca_gps_operate_record表，更新ca_app_info表is_gps状态。

### 2、天鹰GPS平台
>该平台主要面向GPS审批主管和GPS审核人员，其中对于GPS审核人员需要通过，领单(加入领单队列，系统自动派单)-审核流程机制审核。对于审批通过或者驳回操作
是通过判断该安装信息中是否包含人工审核结果有非审核成功的选项，则为驳回到销售端，否则审核通过。尽管审核通过，但是由于GPS审核流程与申请单审核流程并行
而对于二者，优先选择申请单的流程，如在放款之前，任何一个环节退回复审，则经过终审之后，依然需要销售重新确认GPS信息，并提交审核人员重新审核确认。

####2.1、菜单介绍

+ GPS安装单已驳回：该菜单列举GPS审核驳回的单子，可以提供给审核人员重新修正并通过，或者帮助销售修正GPS安装信息并审核通过。
+ GPS安装单已上传：列举销售提交过来的单子，提供给GPS主管查看审核进度情况。
+ GPS审核领单：提供给GPS审核人员领单，该菜单是人工领单。
+ 我的GPS领单：列举个人名下已经领单的记录。
+ GPS综合查询：提供相关查询。
+ GPS监控设置：提供给系统管理员以及GPS主管设置相关参数。

####2.2、系统相关驳回

>由于GPS审核流程与申请单审批流程并行，对于相关菜单退回复审操作，需要更新未审批的ca_gps_history表为驳回操作，意味着系统自动退回，并新增
ca_gps_operate_record表相应操作记录。

具体枚举com.lyqc.gpsweb.enums.GpsApprovalBackEnum

```java
BEFORE_LOAN_BACK(1, "贷前审核岗审批->退回复审审批"),
AFTER_LOAN_BACK(2, "贷后审核岗审批->退回复审审批"),
APPPROVAL_BACK(3, "审批退回管理->退回复审审批"),
APPPROVAL_DJ_BACK(4, "对接放款管理->退回复审审批"),
APPPROVAL_DZ_BACK(5, "垫资放款管理->退回复审审批"),
APPPROVAL_ZD_BACK(6, "自动放款管理->退回复审审批");
```
## 目录划分
1. gps-common:公共模块
2. gps-dao:
entity：存放mapper实体；
form：封装view视图查询条件；
mapper：orm交互
2. gps-service：service接口、实现类
3. gps-api：restfulApi接口

## 开发注意事项
1. restfulApi要使用Result<T>封装，该Result类提供相关静态方法，如：Result.suc()、Result.fail()。
2. 查询实体form类，如果属于通用规则类，要继承BaseForm类；
3. mapper接口要继承BaseMapper。
4. 分页查询接口，要使用PageVO<T>封装查询结果，PageForm<T>封装查询form类。
5. 相关接口都要写注释，不要懒省事。
6. controller类的相关api接口参照GpsRuleController类，即以*Record结尾。
## service接口定义规范
```java
/**
 * @description: GPS规则配置Service
 * @Date : 下午5:47 2018/2/4
 * @Author : 石冬冬-gps Hitler(dongdong.shi@mljr.com)
 */
public interface GpsRuleService {
    /**
     * 分页加载数据
     * @param form
     * @return
     */
    Result<PageVO<GpsRule>> loadRecords(PageForm<GpsRuleForm> form);
    /**
     * 查询实体对象
     * @param id
     * @return
     */
    Result<GpsRule> queryRecord(Integer id);
    /**
     * 保存实体对象
     * @param record
     * @return
     */
    Result<String> saveRecord(GpsRule record);
    /**
     * 删除对象
     * @param id
     * @return
     */
    Result<String> deleteRecord(Integer id);
}

```
## controller定义规范
```java
/**
 * @description: GPS规则配置
 * @Date : 下午6:15 2018/2/4
 * @Author : 石冬冬-gps Hitler(dongdong.shi@mljr.com)
 */
@RestController
@RequestMapping("/gpsRule")
public class GpsRuleController {
    @Autowired
    private GpsRuleService gpsRuleService;
    /**
     * 分页加载数据
     * @param form
     * @return Result<PageVO<GpsRule>>
     */
    @RequestMapping(value = "/loadRecords",method = {RequestMethod.POST})
    @ResponseBody
    public Result<PageVO<GpsRule>> loadRecords(PageForm<GpsRuleForm> form){
        return this.gpsRuleService.loadRecords(form);
    }
    /**
     * 查询详情
     * @param id
     * @return Result<GpsRule>
     */
    @RequestMapping(value = "/queryRecord/{id}",method = {RequestMethod.GET})
    @ResponseBody
    public Result<GpsRule> queryRecord(@PathVariable Integer id){
        return this.gpsRuleService.queryRecord(id);
    }
    /**
     * 删除
     * @param id
     * @return Result<String>
     */
    @RequestMapping(value = "/deleteRecord/{id}",method = {RequestMethod.GET})
    @ResponseBody
    public Result<String> deleteRecord(@PathVariable Integer id){
        return this.gpsRuleService.deleteRecord(id);
    }
    /**
     * 保存
     * @param record
     * @return Result<String>
     */
    @RequestMapping(value = "/saveRecord",method = {RequestMethod.POST})
    @ResponseBody
    public Result<String> saveRecord(GpsRule record){
        return this.gpsRuleService.saveRecord(record);
    }
}

```