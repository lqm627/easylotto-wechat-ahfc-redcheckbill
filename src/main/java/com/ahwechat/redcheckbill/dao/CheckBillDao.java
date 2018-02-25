package com.ahwechat.redcheckbill.dao;

import com.ahwechat.redcheckbill.model.ActivityRed;
import com.ahwechat.redcheckbill.model.CheckBillLog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CheckBillDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ActivityRed> getRedList(int bat, String dayTime,String dbNotInWX) {
        String sql = "SELECT t.*,q.VC_ENCRYPTTED_INFORMATION,q.VC_STATUIN_ID AS vc_station_id,q.VC_GAME_NAME,q.VC_SELL_TERM_CODE,q.VC_RUN_CODE" +
                " FROM ecp_activity_red t" +
                " JOIN ecp_qr_code q ON q.INT_ACTIVITY_RED_ID=t.INT_REC_ID" +
                "  WHERE t.INT_IS_SUCCESS=2 AND t.VC_MCH_BILLNO is not NULL  and t.INT_BAT=" + bat;
        if(StringUtils.isNotBlank(dbNotInWX)){
            sql += " AND VC_MCH_BILLNO IN ("+dbNotInWX+")";
        }
        if (StringUtils.isBlank(dayTime)) {
            sql += " AND TO_DAYS( NOW( ) ) - TO_DAYS( t.DT_ENTRY_TIME) <= 1 ";
        } else {
            sql += " AND DATE_FORMAT(t.DT_ENTRY_TIME,'%Y-%m-%d')='" + dayTime + "'";
        }
        List<ActivityRed> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ActivityRed>(ActivityRed.class));
        if (list != null && list.size() != 0) {
            return list;
        }
        return null;
    }

    public void saveCheckBillLog(CheckBillLog checkBillLog){
        String str = "insert into ecp_check_bill_log(" +
                "INT_ACTIVITY_ID" +
                ",INT_ACCOUNT_ID" +
                ",INT_CHECK_BILL_STATE" +
                ",DEC_DB_RED_MONEY" +
                ",DEC_WX_RED_MONEY" +
                ",VC_MCH_BILLNO" +
                ",INT_BAT" +
                ",DT_ENTRY_TIME" +
                ",DT_STATISTICAL_DAY" +
                ",INT_CHECK_BILL_STYLE" +
                ",VC_RED_STATE" +
                ",VC_FAIL_REASON" +
                ",VC_ENCRYPTTED_INFORMATION" +
                ",VC_STATUIN_ID" +
                ",VC_GAME_NAME" +
                ",VC_SELL_TERM_CODE" +
                ",VC_RUN_CODE) values(?,?,?,?,?,?,?,now(),?,?,?,?,?,?,?,?,?)";
        Object obj[] = new Object[] {
                 checkBillLog.getInt_activity_id()
                ,checkBillLog.getInt_account_id()
                ,checkBillLog.getInt_check_bill_state()
                ,checkBillLog.getDec_db_red_money()
                ,checkBillLog.getDec_wx_red_money()
                ,checkBillLog.getVc_mch_billno()
                ,checkBillLog.getInt_bat()
                ,checkBillLog.getDt_statistical_day()
                ,checkBillLog.getInt_check_bill_style()
                ,checkBillLog.getVc_red_state()
                ,checkBillLog.getVc_fail_reason()
                ,checkBillLog.getVc_encryptted_information()
                ,checkBillLog.getVc_station_id()
                ,checkBillLog.getVc_game_name()
                ,checkBillLog.getVc_sell_term_code()
                ,checkBillLog.getVc_run_code()};
        jdbcTemplate.update(str, obj);
    }
}
