package com.jyp.greenhouse.core.util;

import com.sun.management.OperatingSystemMXBean;
import sun.management.ManagementFactoryHelper;

import javax.servlet.ServletContext;

/**
 * Created by oplsu on 2016/12/29.
 */
public class OSInfo {
    private String osName;
    private String osVersion;
    private String javaVersion;
    private String serverInfo;
    private long totalMemory;
    private long freeMemory;
    private long useMemory;
    private long threadsnum;


    private OSInfo() {
    }

    public String getOsName() {
        return osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public String getJavaVersion() {
        return javaVersion;
    }

    public long getFreeMemory() {
        return freeMemory;
    }

    public long getUseMemory() {
        return useMemory;
    }

    public long getThreadsnum() {
        return threadsnum;
    }

    /**
     * 获取服务器信息
     *
     * @return
     */
    public String getServerInfo() {
        return serverInfo;
    }

    /**
     * 获取java进程内存大小，以M为单位
     *
     * @return
     */
    public long getTotalMemory() {
        return totalMemory;
    }

    public static OSInfo getCurrentOSInfo() {
        OSInfo instance = new OSInfo();
        instance.osName = System.getProperty("os.name");
        instance.osVersion = System.getProperty("os.version");
        instance.javaVersion = System.getProperty("java.version");
        ServletContext sc = ServletUtil.getRequest().getServletContext();
        instance.serverInfo = sc.getServerInfo();

        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactoryHelper
                .getOperatingSystemMXBean();
        // 总的物理内存
        instance.totalMemory = osmxb.getTotalPhysicalMemorySize() / 1024 / 1024;
        // 剩余的物理内存
        instance.freeMemory = osmxb.getFreePhysicalMemorySize() / 1024 / 1024;
        // 已使用的物理内存
        instance.useMemory = (osmxb.getTotalPhysicalMemorySize() - osmxb
                .getFreePhysicalMemorySize())
                / 1024 / 1024;
        // 获得线程总数

        ThreadGroup group = Thread.currentThread().getThreadGroup();
        ThreadGroup topGroup = group;
// 遍历线程组树，获取根线程组
        while (group != null) {
            topGroup = group;
            group = group.getParent();
        }
// 激活的线程数加倍
        int estimatedSize = topGroup.activeCount() * 2;
        Thread[] slackList = new Thread[estimatedSize];
// 获取根线程组的所有线程
        int actualSize = topGroup.enumerate(slackList);
// copy into a list that is the exact size
        Thread[] list = new Thread[actualSize];
        System.arraycopy(slackList, 0, list, 0, actualSize);
//        for (Thread thread : list) {
//            System.out.println(thread.getName());
//        }
        instance.threadsnum = list.length;
        return instance;
    }


    public static void main(String[] args) {
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactoryHelper
                .getOperatingSystemMXBean();
        // 操作系统
        String osName = System.getProperty("os.name");
        // 总的物理内存
        long totalMemorySize = osmxb.getTotalPhysicalMemorySize() / 1024 / 1024;
        // 剩余的物理内存
        long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize() / 1024 / 1024;
        // 已使用的物理内存
        long usedMemory = (osmxb.getTotalPhysicalMemorySize() - osmxb
                .getFreePhysicalMemorySize())
                / 1024 / 1024;
        // 获得线程总数
        ThreadGroup parentThread;
        for (parentThread = Thread.currentThread().getThreadGroup(); parentThread
                .getParent() != null; parentThread = parentThread.getParent())
            ;
        int totalThread = parentThread.activeCount();
        System.out.println(osName);
        System.out.println(totalMemorySize);
        System.out.println(freePhysicalMemorySize);
        System.out.println(usedMemory);
        System.out.println(totalThread);

    }
}


