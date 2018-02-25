package com.ahwechat.redcheckbill.common;

public class Constant {
    public static  final int DB_HAVE_WX_NOT_HAVE=1;//1db有，微信商户无
    public static  final int WX_HAVE_DB_NOT_HAVE=2;//2db无，微信商户有
    public static  final int DB_HAVE_WX_HAVE_AND_TRUE=3;//3db有，微信商户有，金额对上
    public static  final int DB_HAVE_WX_HAVE_AND_FALSE=4;//4db有，微信商户有，金额对不上

    public static  final int MONEY_DETAIL=1;//1资金流水方式
    public static  final int RED_DETAIL=2;//2红包记录方式
}
