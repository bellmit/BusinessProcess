package com.mrbeard.process.blocks.professional.service.impl;

import com.mrbeard.process.blocks.professional.dto.ProcessDto;
import com.mrbeard.process.blocks.professional.service.ProcessService;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;
import com.mrbeard.process.result.ResultGenerator;
import org.springframework.stereotype.Service;

/**
 * @ClassName ProcessServiceImpl
 * @Description TODO
 * @Author Mrbeard
 * @Date 2019/2/20 17:02
 * @Version 1.0
 **/
@Service
public class ProcessServiceImpl implements ProcessService {

    /**
     * 创建新流程
     * @param processDto
     * @return
     * @throws ProcessRuntimeException
     */
    @Override
    public Result createProcess(ProcessDto processDto) throws ProcessRuntimeException {
        return ResultGenerator.getSuccessResult("Success");
    }
}
