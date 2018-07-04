package com.shandong.human.resource.util;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

/**
 * 获得系统运行时的一些参数
 *
 * Author: constantine <1194479264@qq.com>
 * Date: 16/3/30 上午12:14
 */
public class SysInfo {

    /**
     * Cpu个数
     *
     * @return
     * @throws SigarException
     */
    public static int getCpuCount() throws SigarException {
        Sigar sigar = new Sigar();
        try {
            return sigar.getCpuInfoList().length;
        } finally {
            sigar.close();
        }
    }

    /**
     * Cpu总量(Hz为单位)以及Cpu相关信息
     */
    public void getCpuTotal() {
        Sigar sigar = new Sigar();
        CpuInfo[] infos;
        try {
            infos = sigar.getCpuInfoList();
            for (int i = 0; i < infos.length; i++) {
                CpuInfo info = infos[i];
                System.out.println("mhz=" + info.getMhz());// CPU的总量MHz
                System.out.println("vendor=" + info.getVendor());// 获得CPU的卖主，如：Intel
                System.out.println("model=" + info.getModel());// 获得CPU的类别，如：Celeron
                System.out.println("cache size=" + info.getCacheSize());// 缓冲存储器数
            }
        } catch (SigarException e) {
            e.printStackTrace();
        }
    }

    public void testCpuPerc() {
        Sigar sigar = new Sigar();
        // 方式一，主要是针对一块CPU的情况
        CpuPerc cpu;
        try {
            cpu = sigar.getCpuPerc();
            printCpuPerc(cpu);
        } catch (SigarException e) {
            e.printStackTrace();
        }
        // 方式二，不管是单块CPU还是多CPU都适用
        CpuPerc cpuList[] = null;
        try {
            cpuList = sigar.getCpuPercList();
        } catch (SigarException e) {
            e.printStackTrace();
            return;
        }
        for (int i = 0; i < cpuList.length; i++) {
            printCpuPerc(cpuList[i]);
        }
    }

    private void printCpuPerc(CpuPerc cpu) {
        System.out.println("User :" + CpuPerc.format(cpu.getUser()));// 用户使用率
        System.out.println("Sys :" + CpuPerc.format(cpu.getSys()));// 系统使用率
        System.out.println("Wait :" + CpuPerc.format(cpu.getWait()));// 当前等待率
        System.out.println("Nice :" + CpuPerc.format(cpu.getNice()));//
        System.out.println("Idle :" + CpuPerc.format(cpu.getIdle()));// 当前空闲率
        System.out.println("Total :" + CpuPerc.format(cpu.getCombined()));// 总的使用率
    }
}
