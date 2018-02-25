package com.ahwechat.redcheckbill.controller;

import com.ahwechat.redcheckbill.service.CheckBillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CheckBillController {
    public static Logger logger = LoggerFactory.getLogger(CheckBillController.class);

    @Autowired
    private CheckBillService checkBillService;

    @RequestMapping("/checkbill/{dayTime}")
    public void checkBill(@PathVariable String dayTime) throws IOException {
        try {
            logger.info("对账开始");
            //导入交易中心下的资金管理下的资金流水的当日现金红包支出记录的csv文件时，选择该方法
//            checkBillService.checkBill(dayTime);
            //导入营销中心下的现金红包下的记录查询的当日红包记录的csv文件时，选择该方法
            checkBillService.checkBillByRed(dayTime);
            logger.info("对账结束");
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn(e.getMessage());
        }
    }
}
