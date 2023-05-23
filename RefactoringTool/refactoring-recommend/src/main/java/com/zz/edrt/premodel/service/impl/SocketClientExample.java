package com.zz.edrt.premodel.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class SocketClientExample {

    public static void main(String[] args) throws IOException {
        // 创建连接
        Socket socket = new Socket("localhost", 10000);

        // 准备请求数据
        String params = "/Users/zzsnowy/StudyDiary/MSA/graduationPro/experiment/features的副本.csv";

        // 发送请求数据
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(params.getBytes(StandardCharsets.UTF_8));

        // 接收响应数据
        InputStream inputStream = socket.getInputStream();
        byte[] buffer = new byte[1024];
        int read = inputStream.read(buffer);
        String result = new String(Arrays.copyOf(buffer, read), StandardCharsets.UTF_8);
        System.out.println(result);
    }
}


