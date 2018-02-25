package com.ahwechat.redcheckbill.model;

import java.math.BigDecimal;
import java.util.Date;

public class CheckBillLog {
    private Long int_rec_id;
    private Long int_activity_id;
    private Long int_account_id;
    private Integer int_check_bill_state;
    private BigDecimal dec_db_red_money;
    private BigDecimal dec_wx_red_money;
    private String vc_mch_billno;
    private int int_bat;
    private Date dt_entry_time;
    private Date dt_statistical_day;
    private int int_check_bill_style;
    private String vc_red_state;
    private String vc_fail_reason;
    private String vc_encryptted_information;
    private String vc_station_id;
    private String vc_game_name;
    private String vc_sell_term_code;
    private String vc_run_code;

    public Long getInt_rec_id() {
        return int_rec_id;
    }

    public void setInt_rec_id(Long int_rec_id) {
        this.int_rec_id = int_rec_id;
    }

    public Long getInt_activity_id() {
        return int_activity_id;
    }

    public void setInt_activity_id(Long int_activity_id) {
        this.int_activity_id = int_activity_id;
    }

    public Long getInt_account_id() {
        return int_account_id;
    }

    public void setInt_account_id(Long int_account_id) {
        this.int_account_id = int_account_id;
    }

    public Integer getInt_check_bill_state() {
        return int_check_bill_state;
    }

    public void setInt_check_bill_state(Integer int_check_bill_state) {
        this.int_check_bill_state = int_check_bill_state;
    }

    public BigDecimal getDec_db_red_money() {
        return dec_db_red_money;
    }

    public void setDec_db_red_money(BigDecimal dec_db_red_money) {
        this.dec_db_red_money = dec_db_red_money;
    }

    public BigDecimal getDec_wx_red_money() {
        return dec_wx_red_money;
    }

    public void setDec_wx_red_money(BigDecimal dec_wx_red_money) {
        this.dec_wx_red_money = dec_wx_red_money;
    }

    public String getVc_mch_billno() {
        return vc_mch_billno;
    }

    public void setVc_mch_billno(String vc_mch_billno) {
        this.vc_mch_billno = vc_mch_billno;
    }

    public int getInt_bat() {
        return int_bat;
    }

    public void setInt_bat(int int_bat) {
        this.int_bat = int_bat;
    }

    public Date getDt_entry_time() {
        return dt_entry_time;
    }

    public void setDt_entry_time(Date dt_entry_time) {
        this.dt_entry_time = dt_entry_time;
    }

    public Date getDt_statistical_day() {
        return dt_statistical_day;
    }

    public void setDt_statistical_day(Date dt_statistical_day) {
        this.dt_statistical_day = dt_statistical_day;
    }

    public int getInt_check_bill_style() {
        return int_check_bill_style;
    }

    public void setInt_check_bill_style(int int_check_bill_style) {
        this.int_check_bill_style = int_check_bill_style;
    }

    public String getVc_red_state() {
        return vc_red_state;
    }

    public void setVc_red_state(String vc_red_state) {
        this.vc_red_state = vc_red_state;
    }

    public String getVc_fail_reason() {
        return vc_fail_reason;
    }

    public void setVc_fail_reason(String vc_fail_reason) {
        this.vc_fail_reason = vc_fail_reason;
    }

    public String getVc_encryptted_information() {
        return vc_encryptted_information;
    }

    public void setVc_encryptted_information(String vc_encryptted_information) {
        this.vc_encryptted_information = vc_encryptted_information;
    }

    public String getVc_station_id() {
        return vc_station_id;
    }

    public void setVc_station_id(String vc_station_id) {
        this.vc_station_id = vc_station_id;
    }

    public String getVc_game_name() {
        return vc_game_name;
    }

    public void setVc_game_name(String vc_game_name) {
        this.vc_game_name = vc_game_name;
    }

    public String getVc_sell_term_code() {
        return vc_sell_term_code;
    }

    public void setVc_sell_term_code(String vc_sell_term_code) {
        this.vc_sell_term_code = vc_sell_term_code;
    }

    public String getVc_run_code() {
        return vc_run_code;
    }

    public void setVc_run_code(String vc_run_code) {
        this.vc_run_code = vc_run_code;
    }
}
