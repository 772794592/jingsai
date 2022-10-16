package com.example.jingsai.initialize.task;

import com.example.jingsai.enums.CodeEnum;
import com.example.jingsai.exception.BizException;
import com.example.jingsai.service.ProcessInfoService;
import com.example.jingsai.utils.CommandUtil;
import com.example.jingsai.utils.ShellCommand;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ProcessInfoTask implements Runnable {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProcessInfoService processInfoService;

    @Override
    public void run() {

        String[] cmd = ShellCommand.getShell(ShellCommand.TOP);
        if (log.isDebugEnabled()) {
            log.debug("cmd={}", Arrays.toString(cmd));
        }

        CommandUtil.ExecReturn execReturn = null;
        try {
            execReturn = CommandUtil.exec(cmd);

            if (execReturn.exitCode != 0) {
                throw new BizException(CodeEnum.SHELL_EXEC_FAIL);
            }

            processInfoService.saveBatch(execReturn.stdout);
        } catch (Exception e) {
            log.error("cmd={}, execReturn={}, processInfo save fail={}", cmd, execReturn, ExceptionUtils.getStackTrace(e));
        }
    }
}