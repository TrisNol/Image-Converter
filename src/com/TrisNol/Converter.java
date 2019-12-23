package com.TrisNol;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Converter {
    private File folder;
    private File[] files;

    private String rootPath;
    private String target;

    private List<File> fileList;

    public Converter(String root, String target){
        this.rootPath = root;
        this.fileList = new LinkedList<>();
        this.target = target;
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
                if(!Files.isRegularFile(item.toPath())){
                    System.out.println("Create Folder");
                    new File(item.getAbsolutePath().replace('\\','/').replace(this.rootPath,this.target)).mkdirs();
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
        for(File file : this.fileList) {
            String newPath = file.getAbsolutePath().replace('\\','/').replace(this.rootPath,path);
            System.out.println(newPath);
            if (file.isDirectory()) {
                //System.out.println("Create Folder");
                //new File(path + file.getPath()).mkdirs();
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