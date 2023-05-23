package com.zz.edrt.premodel.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ShellUtil {

    public List<String> executeShell(String shpath, String var){

        //String shpath="/test/test.sh";   // sh 路径
        //String var="201102";             // sh 参数
        String shellVar = (var==null)?"":var;
        String command1 = "chmod 777 " + shpath; // 为 sh 添加权限
        String command2 = "/bin/sh " + shpath + " " + shellVar;
        final List<String> strList = new ArrayList<String>();
        Process process1 = null;
        BufferedReader in = null;
        try {
            process1 = Runtime.getRuntime().exec(command1); // 执行添加权限的命令
            process1.waitFor(); // 如果执行多个命令,必须加上
            final Process process2 = Runtime.getRuntime().exec(command2);

            //处理InputStream的线程
            new Thread() {
                @Override
                public void run() {
                    BufferedReader in = new BufferedReader(new InputStreamReader(process2.getInputStream()));
                    String line = null;
                    try{
                        while((line = in.readLine()) != null) {
                            strList.add(line);
                        }
                    }catch(IOException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            in.close();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();

            //处理ErrorStream的线程
            new Thread() {
                @Override
                public void run(){
                    BufferedReader err = new BufferedReader(new InputStreamReader(process2.getErrorStream()));
                    String line = null;
                    try{
                        while((line = err.readLine()) != null)
                        {
                            strList.add(line);
                        }
                    }catch(IOException e){
                        e.printStackTrace();
                    }finally{
                        try{
                            err.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }.start();

            process2.waitFor();

        } catch (IOException e) {

        } catch (InterruptedException e) {

        }finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {

                }
            }
            if(process1 != null){
                process1.destroy();
            }
        }
        return strList;
    }

    public void run(String command, File dir) throws IOException, InterruptedException {
        System.out.println("Running command: " + command + " in directory: " + dir.getAbsolutePath());
        Process process = new ProcessBuilder()
                .command(command.split(" "))
                .directory(dir)
                .redirectErrorStream(true)
                .start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        process.waitFor(); //等待子进程完成
        process.destroy();
    }

}
