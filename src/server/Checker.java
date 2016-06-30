package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Checker extends Thread {
    StatusResult res;
    int memory_max;
    int time_max;
    Process target;
    long pid;

    public Checker(long pid, StatusResult res, int memory_max, int time_max, Process target) {
        this.pid = pid;
        this.res = res;
        this.memory_max = memory_max;
        this.time_max = time_max;
        this.target = target;
    }

    public static int convert_to_seconds(String str) {
        String[] arr = str.split(":");
        int hour = Integer.parseInt(arr[0]);
        int minutes = Integer.parseInt(arr[1]);
        int seconds = Integer.parseInt(arr[2]);

        return hour * 60 * 60 + minutes * 60 + seconds;
    }


    @Override
    public void run() {
        while (true) {
            Process process = null;
            try {
                process = new ProcessBuilder(
                        "ps", "-p", pid+"", "-o", "vsz=MEMORY", "-o", "time").start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            long memory = 0;
            long time = 0;
            assert process != null;
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            if(interrupted()){
                return;
            }
            try {
                while ((line = br.readLine()) != null) {
                    if(interrupted()){
                        return;
                    }
                    String[] arrFirst = line.split(" ");
                   ArrayList<String> arr = new ArrayList<String>();
                    for (String s : arrFirst) {
                        if(!s.equals("")){
                            arr.add(s);
                        }
                    }

                    if (arr.size() == 2) {
                        try{
                            memory = Integer.parseInt(arr.get(0));
                        }catch (Exception ex){
                            continue;
                        }

                        time = convert_to_seconds(arr.get(1));
//                        System.out.println("Memory: " + memory + " Time: " + time);

                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (memory > memory_max) {
                res.setStatus(Status.MEMORY_LIMIT);
//                System.out.println("killed");
                target.destroy();
                break;
            }
            if (time > time_max) {
                res.setStatus(Status.TIME_LIMIT);
//                System.out.println("killed");
                target.destroy();
                break;
            }

        }


    }
}
