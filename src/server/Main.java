package server;

import com.sun.org.apache.xml.internal.resolver.helpers.Debug;
import server.Judge;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Arrays;



public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, NoSuchFieldException, IllegalAccessException {

        Judge judge = new Judge("tests", "alligator_os_n2.cpp", 256*1024*1024, 1);

//        String status = judge.getStatus();

//        System.out.println(status);
    }
}
