package com.mrbeard.process.common;

import com.mrbeard.process.result.Result;
import com.mrbeard.process.result.ResultGenerator;
import com.mrbeard.process.util.ToolUtil;
import com.mrbeard.process.util.UUIDUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName GetUUids
 * @Description 获取UUIds
 * @Author Mrbeard
 * @Date 2019/3/8 11:09
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api")
public class GetUuIds {


    /**
     * 获取uuid
     * @param number
     * @return
     */
    @RequestMapping("/getUUids")
    public Result getUUids(Integer number){
        if(ToolUtil.isEmpty(number)){
            return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
        }
        List<String> ids = new ArrayList<>();
        for(int i = 0; i < number; i++){
            ids.add(UUIDUtil.getUUID());
        }
        return ResultGenerator.getSuccessResult(ids);
    }
}
