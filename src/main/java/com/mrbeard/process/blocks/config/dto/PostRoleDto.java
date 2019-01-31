package com.mrbeard.process.blocks.config.dto;

/**
 * @ClassName PostRoleDto
 * @Description 配置角色Dto
 * @Author Mrbeard
 * @Date 2019/1/30 14:10
 * @Version 1.0
 **/
public class PostRoleDto {
    /**
     * 角色id
     */
    private String rid;
    /**
     * 角色名
     */
    private String rname;
    /**
     * 角色描述
     */
    private String rdescription;

    /**
     * 角色权限数组
     */
    private String [] permissions;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getRdescription() {
        return rdescription;
    }

    public void setRdescription(String rdescription) {
        this.rdescription = rdescription;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }
}
