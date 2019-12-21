package com.TrisNol;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Converter converter = new Converter();
        System.out.println("Source path:");
        Scanner in = new Scanner(System.in);
        if(converter.readFolder(in.nextLine())){
            System.out.println("Folder has been read ");
        }else{
            System.out.println("No Files found");
            return;
        }
        System.out.println("target format:");
        String format = in.nextLine();
        System.out.println("target path:");
        String path = in.next();
        if(converter.convertPictures(format,path)){
            System.out.println("Images have been converted");
        }else{
            System.out.println("Conversion failed");
        }
    }
}
