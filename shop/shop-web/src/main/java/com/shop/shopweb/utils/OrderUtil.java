package com.shop.shopweb.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @description:
 * @author: csc
 * @create: 2019/12/24 20:50
 */
public class OrderUtil {

    public static void main(String[] args) {
        //获取订单编码
        System.out.println(createOrderNumber("123", 1));
        //获取四位随机数
        System.out.println(getRandNum(4));
    }

    /**
     * 生成编号(由收银员编码+订单类型编码+6位日期+时间戳后4位+随机数组成) 随机数补为32位
     * @param salePersonId 销售员编号
     * @param orderType 编号类型,1位(1-支付订单,2-退款订单)
     * @return
     * @throws Exception
     */
    public static String createOrderNumber(String salePersonId, int orderType) {
        DateFormat format = new SimpleDateFormat("yyMMdd");
        Date date = new Date();
        StringBuffer buffer = new StringBuffer(salePersonId);
        buffer.append(orderType);
        buffer.append(format.format(date));
        buffer.append((date.getTime() + "").substring(9));
        buffer.append(getRandNum(32 - buffer.length()));
        return buffer.toString();
    }

    /*
     * 获取四位随机数
     * @param leng 随机数长度
     * @return
     */
    public static String getRandNum(int leng) {
        Random random = new Random();
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < leng; i++)
            result.append(random.nextInt(10));
        if (result.length() > 0)
            return result.toString();
        return null;
    }
}
