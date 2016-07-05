/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decisiontree;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author F 16
 */
public class DecisionTree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileIO file = new FileIO();
        classification classifier = new classification();
        String allData = "";
        if (file.readFile("data.txt")) {
            allData = file.getData();
        } else {
            System.out.println("Wrong file name");
        }
        classifier.splitData(allData);
        int index=classifier.indecateAttribute(classifier.sortedData, classifier.classLables, classifier.numOfAttributes);
        tree Tree=new tree(classifier.sortedData, classifier.classLables);
        classifier.splitArray(Tree, classifier.sortedData, classifier.classLables, index, classifier.numOfAttributes);
        classifier.displayTree(Tree, "");
        int num;
        System.out.println("Enter the number of words you want to check on");
        Scanner in=new Scanner(System.in);
        num=in.nextInt();
        ArrayList<String> words = new ArrayList<String>();
        for(int i=0;i<num;i++)
        {
            words.add(in.next());
        }
        for(int i=0;i<classifier.finalRes.size();i++)
        {
            num=0;
            for(int j=0;j<words.size();j++)
            {
                if(classifier.finalRes.get(i).contains(words.get(j)))
                {
                    num++;
                } 
            }
            if(num>=words.size())
            {
                System.out.println(classifier.finalClass.get(i));
                break;
            }
            if(i==classifier.finalRes.size()-1)
            {
                System.out.println("there is no data with this names");
            }
        }
    }

}
