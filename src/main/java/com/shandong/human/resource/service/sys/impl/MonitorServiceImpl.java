package com.shandong.human.resource.service.sys.impl;

import com.shandong.human.resource.domain.MonitorInformation;
import com.shandong.human.resource.domain.MonitorInformationAddtion;
import com.shandong.human.resource.service.sys.MonitorService;
import com.sun.management.OperatingSystemMXBean;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.management.ManagementFactory;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Logger;

/**
 * 系统监控Service
 * <p>
 * Author: constantine <1194479264@qq.com>
 * Date: 16/3/14 下午2:27
 */
@Service
public class MonitorServiceImpl implements MonitorService {

    private static final int CPUTIME = 5000;

    private static final int PERCENT = 100;

    private static final int FAULTLENGTH = 10;

    private static Logger log = Logger.getLogger("CPUUsageLog");

    /**
     * 获得系统监控信息
     *
     * @return
     */
    public MonitorInformation getMonitorInformation() {

//        System.out.println(javaVersion);
//        System.out.println(arch);

        int kb = 1024;

        // 可使用内存
        long totalMemory = Runtime.getRuntime().totalMemory() / kb;
        // 剩余内存
        long freeMemory = Runtime.getRuntime().freeMemory() / kb;
        // 最大可使用内存
        long maxMemory = Runtime.getRuntime().maxMemory() / kb;
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        // 操作系统
        String osName = System.getProperty("os.name");
        // 总的物理内存
        long totalMemorySize = osmxb.getTotalPhysicalMemorySize() / kb;
        // 剩余的物理内存
        long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize() / kb;
        // 已使用的物理内存
        long usedMemory = (osmxb.getTotalPhysicalMemorySize() - osmxb.getFreePhysicalMemorySize()) / kb;

        // 获得线程总数
        ThreadGroup parentThread;
        for (parentThread = Thread.currentThread().getThreadGroup();
             parentThread.getParent() != null;
             parentThread = parentThread.getParent())
            ;

        int totalThread = parentThread.activeCount();

        double cpuRatio = getCpuRatioForLinux();

        MonitorInformation monitorInformation = new MonitorInformation();
        monitorInformation.setFreeMemory(freeMemory);
        monitorInformation.setFreePhysicalMemorySize(freePhysicalMemorySize);
        monitorInformation.setMaxMemory(maxMemory);
        monitorInformation.setOsName(osName);
        monitorInformation.setTotalMemory(totalMemory);
        monitorInformation.setTotalMemorySize(totalMemorySize);
        monitorInformation.setTotalThread(totalThread);
        monitorInformation.setUsedMemory(usedMemory);
        monitorInformation.setCpuRatio(cpuRatio);
        return monitorInformation;
    }

    /**
     * 获得CPU使用率
     * TODO: 等待添加
     *
     * @return cpu使用率
     */
    private double getCpuRatioForLinux() {
        return 0.0;
    }

    /**
     * 获得系统监控辅助信息
     *
     * @param request
     * @return
     */
    public MonitorInformationAddtion getMonitorInformationAddtion(HttpServletRequest request) {
        Properties props = System.getProperties();
        MonitorInformationAddtion mia = new MonitorInformationAddtion();

        mia.setJavaVersion(props.getProperty("java.version"));
        mia.setArch(props.getProperty("os.arch"));
        mia.setServerAddr(request.getRemoteAddr());
        mia.setServerHost(request.getRemoteHost());
        mia.setServerPort(request.getServerPort());
        mia.setServerName(request.getServerName());
        return mia;
    }
}
