package com.example.jingsai.tcp.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 终端返回结果集工具
 * </p>
 *
 * @author liwen <devinlive@163.com>
 * @version 1.0
 * @since 2022/10/12
 */
public class ExecResult {

    public int exitCode;
    public String stdout;
    public String stderr;


    public ExecResult(int exitValue, String stdout, String stderr) {
        this.exitCode = exitValue;
        this.stdout = stdout;
        this.stderr = stderr;

    }

    // execute a command and return
    public static ExecResult exec(String[] cmd) throws IOException, InterruptedException {
        Runtime rt = Runtime.getRuntime();
        Process process = rt.exec(cmd);
        process.getErrorStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder stdoutBuilder = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            stdoutBuilder.append(line);
            stdoutBuilder.append('\n');
        }
        br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        StringBuilder stderrBuilder = new StringBuilder();
        while ((line = br.readLine()) != null) {
            stderrBuilder.append(line);
            stderrBuilder.append('\n');
        }
        process.waitFor();
        return new ExecResult(process.exitValue(), stdoutBuilder.toString(), stderrBuilder.toString());
    }

    @Override
    public String toString() {
        return "ExecResult{" +
                "exitCode=" + exitCode +
                ", stdout='" + stdout + '\'' +
                ", stderr='" + stderr + '\'' +
                '}';
    }
}
