/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decisiontree;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author F 16
 */
public class classification {

    public ArrayList<String> data = new ArrayList<String>();
    public ArrayList<String> sortedData = new ArrayList<String>();
    public ArrayList<String> classLables = new ArrayList<String>();
    public ArrayList<String> finalRes = new ArrayList<String>();
    public ArrayList<String> finalClass = new ArrayList<String>();
    public int numOfAttributes, yes, no;

    public classification() {
        data.clear();
        sortedData.clear();
        classLables.clear();
        numOfAttributes = 0;
        yes = 0;
        no = 0;
    }

    public void splitData(String fileData) {
        String temp = "", allData = "";
        for (int i = 0; i < fileData.length(); i++) {
            if (fileData.charAt(i) != ',' && fileData.charAt(i) != '/') {
                temp += fileData.charAt(i);
                continue;
            }
            if (fileData.charAt(i) == ',') {
                allData += temp;
                sortedData.add(temp);
                allData += ',';
                temp = "";
            }
            if (fileData.charAt(i) == '/') {
                data.add(allData);
                classLables.add(temp);
                allData = "";
                temp = "";
            }
        }
        for (int i = 0; i < data.get(0).length(); i++) {
            if (data.get(0).charAt(i) == ',') {
                numOfAttributes++;
            }
        }

    }

    public double infoD(int yes, int no) {
        for (int i = 1; i < this.classLables.size(); i++) {
            if (this.classLables.get(i).equals("yes")) {
                yes++;
            } else {
                no++;
            }
        }
        return (-(double) this.info((double) no / (classLables.size() - 1)) - (double) this.info((double) yes / (classLables.size() - 1)));
    }

    public double info(double p) {
        return p != 0 ? p * Math.log(p) / Math.log(2.0) : 0.0;
    }

    public int indecateAttribute(ArrayList<String> data, ArrayList<String> lables, int numOfAttribute) {
        ArrayList<String> distValues = new ArrayList<String>();
        ArrayList<Double> infoValues = new ArrayList<Double>();
        int yesValues = 0, noValues = 0, numOfValues = 0, numOfLables = lables.size() - 1;
        double sum = 0.0;
        for (int i = 0; i < numOfAttribute; i++) {
            for (int j = i + numOfAttribute; j < data.size(); j += numOfAttribute) {
                if (distValues.contains(data.get(j))) {
                    continue;
                } else {
                    distValues.add(data.get(j));
                }
            }
            for (int k = 0; k < distValues.size(); k++) {
                for (int l = i + numOfAttribute; l < data.size(); l += numOfAttribute) {
                    if (distValues.get(k).equals(data.get(l))) {
                        numOfValues++;
                        if (lables.get((l - i) / numOfAttribute).equals("yes")) {
                            yesValues++;
                        } else {
                            noValues++;
                        }
                    }
                }
                sum += ((double) numOfValues / (double) numOfLables) * (-1 * (info((double) yesValues / (double) numOfValues) + info((double) noValues / (double) numOfValues)));
                numOfValues = 0;
                yesValues = 0;
                noValues = 0;
            }
            infoValues.add(sum);
            sum = 0.0;
            
            distValues.clear();
        }
        return infoValues.indexOf(Collections.min(infoValues));
    }

    public void splitArray(tree Tree, ArrayList<String> data, ArrayList<String> lables, int index, int numOfAttribute) {
        ArrayList<String> distValues = new ArrayList<String>();
        ArrayList<String> infoValues = new ArrayList<String>();
        ArrayList<String> lablesOfDist = new ArrayList<String>();
        int indexOfAttribute;
        for (int j = numOfAttribute + index; j < data.size(); j += numOfAttribute) {
            if (distValues.contains(data.get(j))) {
                continue;
            } else {
                distValues.add(data.get(j));
            }
        }        
        for (int i = 0; i < distValues.size(); i++) {
            infoValues.clear();
            lablesOfDist.clear();
            lablesOfDist.add(lables.get(0));
            for(int j=0;j<numOfAttribute;j++)
        {
            if(j==index)
                continue;
            else
                infoValues.add(data.get(j));
        }
            for (int j = numOfAttribute; j < data.size(); j += numOfAttribute) {
                if (data.get(j + index).equals(distValues.get(i))) {
                    for (int k = 0; k < numOfAttribute; k++) {
                        if (k != index) {
                            infoValues.add(data.get(j + k));
                        }
                    }
                    lablesOfDist.add(lables.get(j / numOfAttribute));
                }
            }
            if (lablesOfDist.contains("yes") && lablesOfDist.contains("no")) {
                tree newTree = new tree(infoValues, lablesOfDist, distValues.get(i), "noResult");
                Tree.Tree.add(newTree);
                indexOfAttribute = this.indecateAttribute(infoValues, lablesOfDist, numOfAttribute - 1);
                this.splitArray(newTree, infoValues, lablesOfDist, indexOfAttribute, numOfAttribute - 1);
            } else {
                
                if(lablesOfDist.contains("yes"))
                {
                    tree newTree = new tree(infoValues, lablesOfDist, distValues.get(i), "yes");
                    Tree.Tree.add(newTree);
                }
                else
                {
                    tree newTree = new tree(infoValues, lablesOfDist, distValues.get(i), "no");
                    Tree.Tree.add(newTree);
                }
            }           
        }
    }
    public void displayTree(tree Tree, String temp)
    {
        for(int i=0;i<Tree.Tree.size();i++)
        {
            if(Tree.Tree.get(i).isLeaf)
            {
                System.out.println(temp+Tree.Tree.get(i).name+"--"+Tree.Tree.get(i).finalResult);
                System.out.println("---------------------");
                this.finalRes.add(temp+Tree.Tree.get(i).name);
                finalClass.add(Tree.Tree.get(i).finalResult);
            }
            else
            {
                temp+=Tree.Tree.get(i).name+"--";
                this.displayTree(Tree.Tree.get(i), temp);
                temp="";
            }
        }
    }
}
