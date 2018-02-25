package com.ahwechat.redcheckbill.service;

import com.ahwechat.redcheckbill.common.Constant;
import com.ahwechat.redcheckbill.dao.CheckBillDao;
import com.ahwechat.redcheckbill.model.ActivityRed;
import com.ahwechat.redcheckbill.model.CheckBillLog;
import com.csvreader.CsvReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.*;

import static com.ahwechat.redcheckbill.util.DateTimeUtil.toDate;

@Service
@Transactional
public class CheckBillService {

    private static HashMap<String,Object> mapDB=new HashMap<>();

    private static HashMap<String,Object> mapwWX=new HashMap<>();

    private static Set<String> setDB=new HashSet<>();

    private static Set<String> setWX=new HashSet<>();

    @Autowired
    private CheckBillDao checkBillDao;

    @Value("${check.bill.file}")
    private String checkBillFile;

    @Value("${money.bat}")
    private int moneyBat;

    public List<ActivityRed> getRedList(int bat, String dayTime,String dbNotInWX){
        return  checkBillDao.getRedList(bat, dayTime,dbNotInWX);
    }
    //导入交易中心下的资金管理下的资金流水的当日现金红包支出记录的csv文件时，选择该方法
//    public void checkBill(String dayTime) throws IOException {
//        List<ActivityRed> redList=getRedList(moneyBat,dayTime,null);
//        for (ActivityRed activityRed:redList) {
//            mapDB.put(activityRed.getVc_mch_billno(),activityRed);
//            setDB.add(activityRed.getVc_mch_billno());
//        }
////        System.out.println(new Gson().toJson(mapDB));
////        System.out.println(new Gson().toJson(setDB));
//        //生成CsvReader对象，以，为分隔符，GBK编码方式
//        CsvReader r = new CsvReader(checkBillFile, ',', Charset.forName("GBK"));
//        //读取表头
//        r.readHeaders();
//        //逐条读取记录，直至读完
//        while (r.readRecord()) {
//            //读取一条记录
////            System.out.println(r.getRawRecord());
//            if(r.get("收支类型").contains("收入")){
//                continue;
//            }
//            mapwWX.put(r.get("业务凭证号").substring(1),r.get("收支金额(元)").substring(1));
//            setWX.add(r.get("业务凭证号").substring(1));
//        }
//        r.close();
//        System.out.println(new Gson().toJson(mapwWX));
//        System.out.println(new Gson().toJson(setWX));
//        for (String key:mapwWX.keySet()) {
//            CheckBillLog checkBillLog=new CheckBillLog();
//            //数据库和微信商户流水都有的记录
//            if(mapDB.containsKey(key)){
//                //数据库和微信商户流水都有，并且金额对上的记录状态：3
//                if(((ActivityRed)mapDB.get(key)).getDec_red_money()
//                        .subtract(new BigDecimal(mapwWX.get(key).toString()))
//                        .compareTo(BigDecimal.ZERO)==0){
//                    checkBillLog.setInt_check_bill_state(Constant.DB_HAVE_WX_HAVE_AND_TRUE);
//                }
//                //数据库和微信商户流水都有，但是金额对不上的记录状态：4
//                else{
//                    checkBillLog.setInt_check_bill_state(Constant.DB_HAVE_WX_HAVE_AND_FALSE);
//                }
//                checkBillLog.setInt_account_id(((ActivityRed) mapDB.get(key)).getInt_account_id());
//                checkBillLog.setDec_db_red_money(((ActivityRed) mapDB.get(key)).getDec_red_money());
//                checkBillLog.setVc_encryptted_information(((ActivityRed) mapDB.get(key)).getVc_encryptted_information());
//                checkBillLog.setVc_station_id(((ActivityRed) mapDB.get(key)).getVc_station_id());
//                checkBillLog.setVc_game_name(((ActivityRed) mapDB.get(key)).getVc_game_name());
//                checkBillLog.setVc_sell_term_code(((ActivityRed) mapDB.get(key)).getVc_sell_term_code());
//                checkBillLog.setVc_run_code(((ActivityRed) mapDB.get(key)).getVc_run_code());
//            }
//            //微信商户流水有，数据库没有状态：2
//            else{
//                checkBillLog.setInt_check_bill_state(Constant.WX_HAVE_DB_NOT_HAVE);
//            }
//            checkBillLog.setDec_wx_red_money(new BigDecimal(mapwWX.get(key).toString()));
//            checkBillLog.setInt_activity_id(13l);
//            checkBillLog.setVc_mch_billno(key);
//            checkBillLog.setInt_bat(moneyBat);
//            checkBillLog.setDt_statistical_day(toDate(dayTime+" 00:00:00"));
//            checkBillLog.setInt_check_bill_style(Constant.MONEY_DETAIL);
//            saveCheckBillLog(checkBillLog);
//        }
//        //求出数据库有，微信商户流水没有的商户订单号状态：1
//        setDB.removeAll(setWX);
////        System.out.println(setDB);
//
//        if(setDB!=null&&setDB.size()!=0){
//            String dbNotInWXStr="";
//            for (String str:setDB) {
//                dbNotInWXStr=dbNotInWXStr+"'"+str+"',";
//            }
//            dbNotInWXStr=dbNotInWXStr.substring(0,dbNotInWXStr.length()-1);
////            System.out.println(dbNotInWXStr);
//            List<ActivityRed> redListNotInWX=getRedList(moneyBat,dayTime,dbNotInWXStr);
////            System.out.println(new Gson().toJson(redListNotInWX));
//            for (ActivityRed activityRed:redListNotInWX) {
//                CheckBillLog checkBillLog=new CheckBillLog();
//                checkBillLog.setInt_activity_id(13l);
//                checkBillLog.setInt_account_id(activityRed.getInt_account_id());
//                checkBillLog.setInt_check_bill_state(Constant.DB_HAVE_WX_NOT_HAVE);
//                checkBillLog.setDec_db_red_money(activityRed.getDec_red_money());
//                checkBillLog.setVc_mch_billno(activityRed.getVc_mch_billno());
//                checkBillLog.setInt_bat(moneyBat);
//                checkBillLog.setDt_statistical_day(toDate(dayTime+" 00:00:00"));
//                checkBillLog.setInt_check_bill_style(Constant.MONEY_DETAIL);
//                checkBillLog.setVc_encryptted_information(activityRed.getVc_encryptted_information());
//                checkBillLog.setVc_station_id(activityRed.getVc_station_id());
//                checkBillLog.setVc_game_name(activityRed.getVc_game_name());
//                checkBillLog.setVc_sell_term_code(activityRed.getVc_sell_term_code());
//                checkBillLog.setVc_run_code(activityRed.getVc_run_code());
//                saveCheckBillLog(checkBillLog);
//            }
//        }
//    }
    //导入营销中心下的现金红包下的记录查询的当日红包记录的csv文件时，选择该方法
    public void checkBillByRed(String dayTime) throws IOException {
        List<ActivityRed> redList=getRedList(moneyBat,dayTime,null);
        for (ActivityRed activityRed:redList) {
            mapDB.put(activityRed.getVc_mch_billno(),activityRed);
            setDB.add(activityRed.getVc_mch_billno());
        }
//        System.out.println(new Gson().toJson(mapDB));
//        System.out.println(new Gson().toJson(setDB));
        //生成CsvReader对象，以，为分隔符，GBK编码方式
        CsvReader r = new CsvReader(checkBillFile, ',', Charset.forName("GBK"));
        //读取表头
        r.readHeaders();
        //逐条读取记录，直至读完
        while (r.readRecord()) {
            //读取一条记录
//            System.out.println(r.getRawRecord());
            if(r.get("红包状态").equals("发放失败")
//                    ||r.get("红包状态").equals("过期未领退款")
                    ||r.get("红包状态").equals("发放中")){
                continue;
            }
            Map<String,String> map=new HashMap<>();
            map.put("redMoney",r.get("红包金额(元)"));
            map.put("redState",r.get("红包状态"));
            map.put("failReason",r.get("失败原因"));
            mapwWX.put(r.get("商户订单号").split("'")[1],map);
            setWX.add(r.get("商户订单号").split("'")[1]);
        }
        r.close();
//        System.out.println(new Gson().toJson(mapwWX));
//        System.out.println(new Gson().toJson(setWX));
        for (String key:mapwWX.keySet()) {
            CheckBillLog checkBillLog=new CheckBillLog();
            //数据库和微信红包发放记录都有的记录
            if(mapDB.containsKey(key)){
                //数据库和微信红包发放记录都有，并且金额对上的记录状态：3
                if(((ActivityRed)mapDB.get(key)).getDec_red_money()
                        .subtract(new BigDecimal(((Map<String,String>)mapwWX.get(key)).get("redMoney").toString()))
                        .compareTo(BigDecimal.ZERO)==0){
                    checkBillLog.setInt_check_bill_state(Constant.DB_HAVE_WX_HAVE_AND_TRUE);
                }
                //数据库和微信红包发放记录都有，但是金额对不上的记录状态：4
                else{
                    checkBillLog.setInt_check_bill_state(Constant.DB_HAVE_WX_HAVE_AND_FALSE);
                }
                checkBillLog.setInt_account_id(((ActivityRed) mapDB.get(key)).getInt_account_id());
                checkBillLog.setDec_db_red_money(((ActivityRed) mapDB.get(key)).getDec_red_money());
                checkBillLog.setVc_encryptted_information(((ActivityRed) mapDB.get(key)).getVc_encryptted_information());
                checkBillLog.setVc_station_id(((ActivityRed) mapDB.get(key)).getVc_station_id());
                checkBillLog.setVc_game_name(((ActivityRed) mapDB.get(key)).getVc_game_name());
                checkBillLog.setVc_sell_term_code(((ActivityRed) mapDB.get(key)).getVc_sell_term_code());
                checkBillLog.setVc_run_code(((ActivityRed) mapDB.get(key)).getVc_run_code());
            }
            //微信红包发放记录有，数据库没有状态：2
            else{
                checkBillLog.setInt_check_bill_state(Constant.WX_HAVE_DB_NOT_HAVE);
            }
            checkBillLog.setDec_wx_red_money(new BigDecimal(((Map<String,String>)mapwWX.get(key)).get("redMoney").toString()));
            checkBillLog.setInt_activity_id(13l);
            checkBillLog.setVc_mch_billno(key);
            checkBillLog.setInt_bat(moneyBat);
            checkBillLog.setDt_statistical_day(toDate(dayTime+" 00:00:00"));
            checkBillLog.setInt_check_bill_style(Constant.RED_DETAIL);
            checkBillLog.setVc_red_state(((Map<String, String>) mapwWX.get(key)).get("redState"));
            checkBillLog.setVc_fail_reason(((Map<String, String>) mapwWX.get(key)).get("failReason"));
            saveCheckBillLog(checkBillLog);
        }
        //求出数据库有，微信红包发放记录没有的商户订单号状态：1
        setDB.removeAll(setWX);
//        System.out.println(setDB);

        if(setDB!=null&&setDB.size()!=0){
            String dbNotInWXStr="";
            for (String str:setDB) {
                dbNotInWXStr=dbNotInWXStr+"'"+str+"',";
            }
            dbNotInWXStr=dbNotInWXStr.substring(0,dbNotInWXStr.length()-1);
//            System.out.println(dbNotInWXStr);
            List<ActivityRed> redListNotInWX=getRedList(moneyBat,dayTime,dbNotInWXStr);
//            System.out.println(new Gson().toJson(redListNotInWX));
            for (ActivityRed activityRed:redListNotInWX) {
                CheckBillLog checkBillLog=new CheckBillLog();
                checkBillLog.setInt_activity_id(13l);
                checkBillLog.setInt_account_id(activityRed.getInt_account_id());
                checkBillLog.setInt_check_bill_state(Constant.DB_HAVE_WX_NOT_HAVE);
                checkBillLog.setDec_db_red_money(activityRed.getDec_red_money());
                checkBillLog.setVc_mch_billno(activityRed.getVc_mch_billno());
                checkBillLog.setInt_bat(moneyBat);
                checkBillLog.setDt_statistical_day(toDate(dayTime+" 00:00:00"));
                checkBillLog.setInt_check_bill_style(Constant.RED_DETAIL);
                checkBillLog.setVc_encryptted_information(activityRed.getVc_encryptted_information());
                checkBillLog.setVc_station_id(activityRed.getVc_station_id());
                checkBillLog.setVc_game_name(activityRed.getVc_game_name());
                checkBillLog.setVc_sell_term_code(activityRed.getVc_sell_term_code());
                checkBillLog.setVc_run_code(activityRed.getVc_run_code());
                saveCheckBillLog(checkBillLog);
            }
        }
    }

    public void saveCheckBillLog(CheckBillLog checkBillLog){
        checkBillDao.saveCheckBillLog(checkBillLog);
    }
}
