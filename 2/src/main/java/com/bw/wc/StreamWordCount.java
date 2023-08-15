package com.bw.wc;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.io.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StreamWordCount {
    public static void main(String[] args) throws Exception {
        // 1. 创建流式执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 2. 读取文件流
        //DataStreamSource<String> lineDss = env.readTextFile("/etc/passwd");
        Runtime rt = Runtime.getRuntime();
        Process process = Runtime.getRuntime().exec("cat /etc/passwd");
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String cmdresult = reader.lines().reduce("", (line1, line2) -> line1 + line2 + "\n");
        //System.out.println(cmdresult);
        Logger logger = LogManager.getLogger();
        logger.info(cmdresult);
        DataStreamSource<String> lineDss = env.readTextFile("/etc/shit");

        lineDss.print();
        // 7. 执行
        env.execute();
    }
}
