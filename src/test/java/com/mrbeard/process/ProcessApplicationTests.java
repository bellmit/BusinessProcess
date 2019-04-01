package com.mrbeard.process;

import com.mrbeard.process.util.UUIDUtil;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessApplicationTests {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Test
    public void contextLoads() {
    }

    @Test
    public void testStringUtils(){
        String labvalue = "#ge[]dfdf/fdf,fdf90990";
        labvalue = StringUtils.replace(labvalue,"#,",",");

        logger.info(labvalue);
    }

    @Test
    public void testIntegerWithInt(){
        Integer a = new Integer("12");
        int b = new Integer("13");
        System.out.println(a>b);
    }

}

