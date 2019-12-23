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

    private String rootPath;

    private LinkedList<File> fileList;

    public Converter(String root){
        this.rootPath = root;
    }

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
        for(File file : this.files) {
            String newPath = file.getAbsolutePath().replace('\\','/').replace(this.rootPath,path);
            System.out.println(newPath);
            if (file.isDirectory()) {
                new File(path + file.getPath()).mkdirs();
            } else {
                try {
                    ImageIO.write(ImageIO.read(file),
                            format,
                            new File(
                                    //path + file.getName()
                                    newPath.split("\\.(?=[^\\.]+$)")[0] + "." + format));
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }
}