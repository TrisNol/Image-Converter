package com.TrisNol;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Converter {
    private File folder;
    private File[] files;

    private LinkedList<File> fileList;

    public boolean readFolder(String path){
        try {
            folder = new File(path);
            this.files = folder.listFiles();
            if (this.files.length == 0) {
                return false;
            }
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public boolean readFolderRecursive(String path){
        try{
            folder = new File(path);
            List<File> node = Arrays.asList(folder.listFiles());
            node.forEach(item->{
                if(item.isDirectory()){
                    System.out.println("Folder ");
                    this.readFolderRecursive(item.getPath());
                }
            });
            this.fileList.addAll(node);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    public boolean convertPictures(String format, String path){
        for(File file : this.files){
            /*
            if (file.isDirectory()){
                new File(path + file.getName()).mkdirs();
                this.files = ArrayUtils.addAll(this.files, file.listFiles());
            }
            */
            try {
                ImageIO.write(ImageIO.read(file),
                        format,
                        new File(path + file.getName().split("\\.(?=[^\\.]+$)")[0]+"."+format));
            }catch(IOException e) {
                return false;
            }
        }
        return true;
    }
}