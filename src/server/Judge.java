package server;

import server.Compilation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by khrak on 6/27/16.
 */
public class Judge {
    private String path;
    private String fileName;
    private int memoryLimit;
    private int timeLimit;

    public Judge(String path, String fileName, int memoryLimit, int timeLimit) {
        this.path = path;
        this.fileName = fileName;
        this.memoryLimit = memoryLimit;
        this.timeLimit = timeLimit;
    }

    public String getStatus() throws IOException, InterruptedException, NoSuchFieldException, IllegalAccessException {

        boolean compilation_res = Compilation.compile(fileName);
        if(!compilation_res){
            return "Compilation Error";
        }



        File directory = new File(path);
        File[] contents = directory.listFiles();

        HashMap<String, File> filesMap = new HashMap<String, File>();

        int numTests = 0;

        for (File f : contents) {
            filesMap.put(f.getName(), f);

            if (!f.getName().contains(".")) numTests++;
        }


        int nameLength = getLength(numTests);

        for (int i = 1; i <= numTests; i++) {
            String inputFileName = getFileName(i, nameLength);
            String answerFileName = inputFileName + ".a";

            if (filesMap.containsKey(inputFileName)) {
                File input = filesMap.get(inputFileName);
                File answer = filesMap.get(answerFileName);

                StringBuilder inputString = new StringBuilder();
                StringBuilder answerString = new StringBuilder();

                Scanner inputScanner = new Scanner(input);

                while (inputScanner.hasNext()) {
                    inputString.append(inputScanner.nextLine() + "\n");
                }

                Scanner answerScanner = new Scanner(answer);

                while (answerScanner.hasNext()) {
                    answerString.append(answerScanner.nextLine() + "\n");
                }
                Compilation comp = new Compilation(memoryLimit, timeLimit);

                String result = comp.test(inputString.toString(), answerString.toString());

                if (!result.equals("OK!")) return result + " on test " + i;
            } else return "no such file found";
        }

        return "Accepted";
    }


    private static String getFileName(int num, int nameLength) {
        String name = "" + num;

        while(name.length() < nameLength) {
            name = "0" + name;
        }

        return name;
    }

    private static int getLength(int size) {
        int rest = 0;

        while(size > 0) {
            rest++;
            size /= 10;
        }

        return rest;
    }



}
