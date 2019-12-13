package com.TrisNol;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Converter {
    private File folder;
    private File[] files;

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

    public boolean convertPictures(String format, String path){
        for(File file : this.files){
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