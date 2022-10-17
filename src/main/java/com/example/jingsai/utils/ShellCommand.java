package com.example.jingsai.utils;

public class ShellCommand {

    public static final String TOP = "top -b -n 1 -o +RES | sed -n '8,$p'";

    public static final String NIC_STATE = "ip addr | grep BROADCAST | tr : ' '| awk '{print $2,$9}'";

    public static final String NETSTAT = "netstat -anp | sed -n '2,/Active UNIX/p' | grep -v 'Active UNIX' | awk '{print $1,$4,$5,$6,$7}'";


    public static String[] getShell(String cmd, Object... args) {
        return new String[]{"/bin/sh", "-c", String.format(cmd, args)};
    }

}
