package controller;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.ClassStruct;
import models.Exercise;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandling {

    public static void saveToFile(String className, String code, String identifier, boolean isTest) {
        try {
            File sf = new File("saves/"+identifier+"/"+ (isTest?"tests/":"src/") + className + ".java");
            sf.getParentFile().mkdirs();
            sf.createNewFile();
            BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sf)));
            fw.write(code);
            fw.flush();
            fw.close();
        }catch(IOException e) {}
    }

    public static String loadFromFile(String className, String identifier, boolean isTest) {
        String code = "";
        try{
            File sf = new File("saves/"+identifier+"/"+ (isTest?"tests/":"src/") + className + ".java");
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(sf)));
            for(Object t: br.lines().toArray()) {
                code += (String)t + "\n";
            }
        }catch(IOException e) {}
        return code;
    }

    public static ClassStruct[] loadAllFiles(String identifier) {
        ArrayList<ClassStruct> cs = new ArrayList();
        File dir = new File("saves/"+identifier+"/");
        for(File f: dir.listFiles()){
            if(f.getName().endsWith(".java")) {
                String className = f.getName().substring(0,f.getName().length()-5);
                boolean isTest = f.getParentFile().getName().equals("tests");
                cs.add(new ClassStruct(className, loadFromFile(className,identifier,isTest), isTest));
            }
        }
        return cs.toArray(new ClassStruct[0]);
    }

    public static List<Exercise> loadCatalog() {
        FileChooser catalog = new FileChooser();
        catalog.setTitle("Choose Catalog-File");
        System.out.println(new File("res/catalogs/").toURI());
        catalog.setInitialDirectory(new File("res/catalogs/"));
		            /*catalog.getExtensionFilters().add(
				    new FileChooser.ExtensionFilter("XML files (.xml)", ".xml")
		             );*/ //Something here does not work with Windows
        File file = catalog.showOpenDialog(new Stage());

        if(file != null && file.getName().endsWith(".xml")) {
            System.out.println(file.getAbsoluteFile());
            XMLLoader xmlLoader = new XMLLoader();
            return xmlLoader.getExercises(file);
        }
        System.out.println("The file you selected is not a valid catalog.");
        return null;
    }

    public static List<String> getHelpFiles(){
        try{
            File file = new File("res/help/move.txt");
            String move = "";
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                move += scanner.nextLine()+"\n";
            }
            file = new File("res/help/tddt.txt");
            String tddt = "";
            scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                tddt += scanner.nextLine()+"\n";
            }
            file = new File("res/help/xmlguide.txt");
            String xmlguide = "";
            scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                xmlguide += scanner.nextLine()+"\n";
            }
            List<String> list = new ArrayList<>();
            list.add(move);
            list.add(tddt);
            list.add(xmlguide);
            return list;
        } catch (Exception e){
            return new ArrayList<>();
        }
    }
}
