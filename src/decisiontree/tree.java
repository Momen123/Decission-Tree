/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decisiontree;

import java.util.ArrayList;

/**
 *
 * @author F 16
 */
public class tree {

    public ArrayList<tree> Tree = new ArrayList<tree>();
    public ArrayList<String> sortedData = new ArrayList<String>();
    public ArrayList<String> classLables = new ArrayList<String>();
    public String finalResult;
    public String name;
    public boolean isLeaf;

    public tree(ArrayList<String> sortedData, ArrayList<String> classLables) {
        this.Tree.clear();
        this.sortedData = sortedData;
        this.classLables = classLables;
        this.name = "Root";
        this.finalResult = "noResult";
        this.isLeaf = false;
    }

    public tree(ArrayList<String> sortedData, ArrayList<String> classLables, String name, String finalResult) {
        this.Tree.clear();
        this.sortedData = sortedData;
        this.classLables = classLables;
        this.name = name;
        if (finalResult.equals("yes") || finalResult.equals("no")) {
            this.isLeaf = true;
        } else {
            this.isLeaf = false;
        }
        this.finalResult = finalResult;
    }

}
