package com.TrisNol;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Source path:");
        Scanner in = new Scanner(System.in);
        String root = in.nextLine();
        System.out.println("target path:");
        String out = in.nextLine();
        System.out.println("target format:");
        String format = in.nextLine();
        Converter converter = new Converter(root,out);
        if(converter.readFolderRecursive(root)){
            System.out.println("Folder has been read ");
        }else{
            System.out.println("No Files found");
            return;
        }
        if(converter.convertPictures(format,out)){
            System.out.println("Images have been converted");
        }else{
            System.out.println("Conversion failed");
        }
    }
}