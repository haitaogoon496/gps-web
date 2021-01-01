package com.mljr.gps.param;

import com.mljr.gps.entity.SyUserRoleKey;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description:
 * @Date : 2019/1/11$ 15:41$
 * @Author : liht
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRoleParam {

    @ApiModelProperty(value = "添加的角色list")
    private List<SyUserRoleKey> insertUsers;
    @ApiModelProperty(value = "删除的角色list")
    private List<SyUserRoleKey> deleteUsers;
}
