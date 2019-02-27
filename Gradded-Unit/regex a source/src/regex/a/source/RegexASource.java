/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex.a.source;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;

/**
 *
 * @author RandomlyFuzzy
 */
public class RegexASource {

    public static BufferedReader fileInput;
    public static Pattern pattern;
    public static Matcher matcher;
    public static Scanner scan;
    public static ArrayList<String> Mathches = new ArrayList<String>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File f = new File("./" + args[0]);
        String regex = "";
        for (int i = 1; i < args.length; i++) {
            String str = args[i];
            regex += " " + str;
        }
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(regex);
        System.out.println(pattern.pattern());
        DirectoryRecursion(f);
        Mathches.forEach((String a) -> {
            System.out.println(a);
        });
    }

    public static synchronized void DirectoryRecursion(File Start) {
        if (Start.isDirectory()) {
            for (File f2 : Start.listFiles()) {
                if (f2.isDirectory()) {
                    DirectoryRecursion(f2);
                } else {
                    AddFromFile(f2);
                }
            }
        } else {
            AddFromFile(Start);
        }
    }

    public static void AddFromFile(File f2) {
        try {
            System.out.println(f2.getName() + " is being read");
            fileInput = new BufferedReader(new FileReader(f2));
            scan = new Scanner(fileInput);
            Mathches.add(" file " + f2.getName());
            while (scan.hasNextLine()) {
                String s = scan.nextLine();
                matcher = pattern.matcher(s);
                while (matcher.find()) {
                    Mathches.add(s.substring(matcher.start(), matcher.end()));
                }
            };
            if (scan.hasNext(pattern)) {
                Mathches.add(scan.next(pattern));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RegexASource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
