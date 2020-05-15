package com.greatwall.pumpkin.tool;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author 星空
 *
 */
@Slf4j
@Data
@Component
public class SnowFlakeTool {

    /**
     * 数据中心
     */

    private long dataCenterId = 0;

    /**
     * 机器标识
     */

    private long machineId = 0;

    /**
     * 序列号
     */
    private long sequence = 0L;

    /**
     * 上一次时间戳
     */
    private long lastTimeStamp = -1L;

    /**
     * 起始的时间戳 1514736000000L 代表2018年1月1日 0点0分0秒
     */
    private final static long START_TIMESTAMP = 1514736000000L;

    /**
     * 每一部分占用的位数
     */
    /**
     * 序列号占用的位数
     */
    private final static long SEQUENCE_BIT = 12;
    /**
     * 机器标识占用的位数
     */
    private final static long MACHINE_BIT = 5;
    /**
     * 数据中心占用的位数
     */
    private final static long DATA_CENTER_BIT = 5;

    /**
     * 每一部分的最大值
     */
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_DATA_CENTER_NUM = -1L ^ (-1L << DATA_CENTER_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATA_CENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTAMP_LEFT = DATA_CENTER_LEFT + DATA_CENTER_BIT;

    /**
     * 根据指定的数据中心ID和机器标志ID生成指定的序列号
     * 
     * @param dataCenterId
     *            数据中心ID
     * @param machineId
     *            机器标志ID
     */
    public SnowFlakeTool() {
        if (dataCenterId > MAX_DATA_CENTER_NUM || dataCenterId < 0) {
            throw new IllegalArgumentException("DtaCenterId can't be greater than MAX_DATA_CENTER_NUM or less than 0！");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("MachineId can't be greater than MAX_MACHINE_NUM or less than 0！");
        }
    }

    /**
     * 产生下一个ID
     * 
     * @return
     */
    public synchronized long nextId() {

        long currTimeStamp = getNewTimeStamp();
        if (currTimeStamp < lastTimeStamp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currTimeStamp == lastTimeStamp) {
            // 相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            // 同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currTimeStamp = getNextMill();
            }
        } else {
            // 不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastTimeStamp = currTimeStamp;

        /**
         * (currTimeStamp - START_TIMESTAMP) << TIMESTAMP_LEFT // 时间戳部分 dataCenterId << DATA_CENTER_LEFT // 数据中心部分
         * machineId << MACHINE_LEFT // 机器标识部分 sequence; // 序列号部分
         */
        return (currTimeStamp - START_TIMESTAMP) << TIMESTAMP_LEFT | dataCenterId << DATA_CENTER_LEFT
            | machineId << MACHINE_LEFT | sequence;
    }

    private long getNextMill() {
        long mill = getNewTimeStamp();
        while (mill <= lastTimeStamp) {
            mill = getNewTimeStamp();
        }
        return mill;
    }

    private long getNewTimeStamp() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {

        SnowFlakeTool snowFlake = new SnowFlakeTool();
        int testLen = 10;
        for (int i = 0; i < testLen; i++) {
            // 10进制
            Long id = snowFlake.nextId();
            // 62进制
            String convertedNumStr = NumericConvertTool.toOtherNumberSystem(id, 62);

            // 10进制转化为62进制
            log.info("10进制：" + id + "  62进制:" + convertedNumStr);

            // 62进制转化为10进制
            log.info("62进制：" + convertedNumStr + "  10进制:" + NumericConvertTool.toDecimalNumber(convertedNumStr, 62));
        }
    }
}
