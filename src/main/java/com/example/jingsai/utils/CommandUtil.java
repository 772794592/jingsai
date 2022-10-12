package com.example.jingsai.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandUtil {


    static public class ExecReturn {
        public int exitCode;
        public String stdout;
        public String stderr;

        public ExecReturn(int exitCode, String stdout, String stderr) {
            this.exitCode = exitCode;
            this.stdout = stdout;
            this.stderr = stderr;
        }

        @Override
        public String toString() {
            return "LocalCommandExecutor{" +
                    "exitCode='" + exitCode + '\'' +
                    ", stdout='" + stdout + '\'' +
                    ", stderr='" + stderr + '\'' +
                    '}';
        }
    }


    // execute a command and return
    static public ExecReturn exec(String[] cmd) throws IOException, InterruptedException {
        Runtime rt = Runtime.getRuntime();
        Process process = rt.exec(cmd);
        process.getErrorStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder stdoutBuilder = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            stdoutBuilder.append('\n');
            stdoutBuilder.append(line);
        }
        br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        StringBuilder stderrBuilder = new StringBuilder();
        while ((line = br.readLine()) != null) {
            stderrBuilder.append('\n');
            stderrBuilder.append(line);
        }
        process.waitFor();
        return new ExecReturn(process.exitValue(), stdoutBuilder.toString(), stderrBuilder.toString());
    }

}
