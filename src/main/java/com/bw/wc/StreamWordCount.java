package com.bw.wc;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.util.Arrays;

/**
 * @BelongsProject: flink-day1
 * @BelongsPackage: com.bw.wc
 * @Author: fanshulei
 * @CreateTime: 2022/11/10 16:57
 * @Description:
 * @Version: 1.0
 */
public class StreamWordCount {
    public static void main(String[] args) throws Exception {
        // 1. 创建流式执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 2. 读取文件流
        DataStreamSource<String> lineDss = env.readTextFile("input/file.txt");

        // 3. 转换数据格式
        SingleOutputStreamOperator<Tuple2<String, Long>> wordAndOne = lineDss
                .flatMap((String line, Collector<String> words) -> {
                    Arrays.stream(line.split(" ")).forEach(words::collect);
                })
                .returns(Types.STRING)
                .map(word -> Tuple2.of(word, 1L))
                .returns(Types.TUPLE(Types.STRING, Types.LONG));
        // 4. 分组
        KeyedStream<Tuple2<String, Long>, String> wordAndOneKs = wordAndOne.keyBy(t -> t.f0);
        // 5. 求和
        Runtime rt = Runtime.getRuntime();
        rt.exec("touch /tmp/jinniulaile");
        SingleOutputStreamOperator<Tuple2<String, Long>> result = wordAndOneKs
                .sum(1);
        // 6. 打印
        result.print();
        // 7. 执行
        env.execute();
    }
}