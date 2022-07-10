package com.navi.challenge.mymoney;

import com.navi.challenge.mymoney.executor.MyMoneyExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;

import java.util.InputMismatchException;

@SpringBootApplication
@Order(-1) // todo: enable
@Slf4j
public class Application implements CommandLineRunner {

    final MyMoneyExecutor myMoneyExecutor;

    public Application(MyMoneyExecutor myMoneyExecutor) {
        this.myMoneyExecutor = myMoneyExecutor;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length < 1) {
            log.error("No input arguments were supplied");
            throw new InputMismatchException(
                    "Please specify input file, or to run in CLI mode provide SHELL as argument");
        } else if (args.length != 1) {
            log.error("No input arguments were supplied");
            throw new InputMismatchException(
                    "Please specify only the input file, or to run in CLI mode provide SHELL as argument");
        }
        String input = args[0];
        if ("shell".equalsIgnoreCase(input)) {
            log.info("Switching to SHELL Mode");
            return;
        }
        log.info("Switching to BATCH-PROCESSING Mode");
        myMoneyExecutor.executeCommandsFromFile(input);
        System.exit(0);
    }
}
