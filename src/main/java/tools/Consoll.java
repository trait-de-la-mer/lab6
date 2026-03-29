package tools;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

public class Consoll {
    static ArrayList<String> files = new ArrayList<>(); //показывает вложенность
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<InputStream> readers = new ArrayList<>();
    static boolean scriptFlag;
    MiddleManager mm;

    public static void addReader(InputStream reader) {
        readers.add(reader);
    }


    public static ArrayList<String> getFiles() {
        return files;
    }

    public static void addFile(String file){
        files.add(file);
    }

    public static void setScriptFlag(boolean scriptFlag) {
        Consoll.scriptFlag = scriptFlag;
    }

    public static void setReader(InputStream newReader) {
        reader = (new BufferedReader(new InputStreamReader(newReader)));
    }


    public Consoll(MiddleManager middleManagerMannager){
        mm = middleManagerMannager;
    }

    public static String askSmt(String textOrFile){
        System.out.printf("Введите %s: ", textOrFile);
        return generateNextLine().trim();
    }

    public void startConsole(){
        String line;
        do{
            line = generateNextLine().toLowerCase(Locale.ENGLISH).trim().replaceAll("\\s+", " ");
            String[] comAndArgs = line.split(" ");
            mm.sendCom(comAndArgs);
        } while (true);
    }

    public static void printSmt(String str){
        System.out.println(str);
    }

    public static String generateNextLine() {
        try {
            String line = reader.readLine();
            if (line == null && scriptFlag){
                if (files.size() == 1) {
                    setReader(System.in);
                    scriptFlag = false;
                }
                else {
                    setReader(readers.getLast());
                }
                files.removeLast();
                readers.removeLast();
                return generateNextLine();
            }
            else if (line == null){throw new IllegalArgumentException("Вводить null или ^D - не круто");}
            return line;
        } catch (IllegalArgumentException e){
            System.err.println("Некорректный символ, критическая ошибка");
            System.exit(0);
            return "";
        } catch (IOException e) {
            throw new RuntimeException("Непредвиденная ошибка ввода");
        }
    }
}
