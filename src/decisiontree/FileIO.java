/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decisiontree;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author F 16
 */
public class FileIO {
    private String fileData;
    public boolean readFile(String fileName)
    {
        String word;
        File file = new File(fileName);
        Scanner in = new Scanner(System.in);
        if (file.exists()) {
            try {
                Scanner ch = new Scanner(new File(fileName));
                this.fileData="";
                while (ch.hasNext()) {
                    word= ch.next();
                     this.fileData += word;
                        this.fileData+= " ";
                }
                ch.close();
                return true;
            } catch (Exception exc) {
                return false;
            }

        } else {
            return false;
        }
    }
    public String getData()
    {
        return this.fileData;
    }
}
