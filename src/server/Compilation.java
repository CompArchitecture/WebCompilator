package server;

import java.io.*;
import java.lang.reflect.Field;



public class Compilation {
    int memory;
    int time;

    public Compilation(int memory, int time) {
        this.memory = memory;
        this.time = time;
    }


    public static boolean compile(String file) throws IOException, InterruptedException {

        Process process = new ProcessBuilder(
                "g++", file,"-w","-o","result_out").start();
        process.waitFor();
        return process.exitValue()==0;
    }


    public String test(String input, String output) throws IOException, NoSuchFieldException, IllegalAccessException {
        StatusResult res = new StatusResult();
        Process process = new ProcessBuilder(
                "./result_out").start();
        Field f = process.getClass().getDeclaredField("pid");
        f.setAccessible(true);
        long pid = f.getLong(process);


        Checker checker = new Checker(pid, res, memory, time, process);
        checker.start();
        InputStream is = process.getInputStream();
        OutputStream os = process.getOutputStream();
        InputStreamReader isr = new InputStreamReader(is);
        OutputStreamWriter osr = new OutputStreamWriter(os);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter writer = new BufferedWriter(osr);

        try {
            writer.write(input);
            writer.flush();
            writer.close();
        } catch (Exception e) {

        }

        try {
            process.waitFor();
        } catch (Exception e) {

        }

        String expected = "";
        while (true) {
            try{

                char ch = (char) br.read();
                if((int)ch == 65535){

                    break;
                }
                expected += ch;
            }catch (Exception e){
                break;
            }
        }

        if(res.getStatus() == Status.NONE && process.exitValue() != 0){
            res.setStatus(Status.RUNTIME_ERROR);
        }

        if(res.getStatus() != Status.NONE){
            checker.interrupt();
            return res.getMessage();
        }

        if(!expected.equals(output)){
//            System.out.println("Expected: "+expected+ " REAL: "+output);
            res.setStatus(Status.WRONG_ANSWER);
        }


        checker.interrupt();
        return res.getMessage();
    }
}
