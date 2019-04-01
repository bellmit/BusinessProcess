package com.mrbeard.process.blocks.professional.dto;

/**
 * @ClassName ProcessNodeDto
 * @Description TODO
 * @Author Mrbeard
 * @Date 2019/3/28 16:43
 * @Version 1.0
 **/
public class ProcessNodeDto {
    /**
     * id
     */
    private String id;
    /**
     * 不通过原因
     */
    private String unpassReason;

    /**
     * 是否通过
     */
    private String isPass;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnpassReason() {
        return unpassReason;
    }

    public void setUnpassReason(String unpassReason) {
        this.unpassReason = unpassReason;
    }

    public String getIsPass() {
        return isPass;
    }

    public void setIsPass(String isPass) {
        this.isPass = isPass;
    }
}
