/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg5.chuongtrinh.lib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tranq
 */
public class FileUtil {
    
    public static List<String> readFile(String fileName){
        
        List<String> fileData = new ArrayList<>();
        
        Path path = Path.of(fileName);
		
	try (BufferedReader reader = 
                Files.newBufferedReader(path, StandardCharsets.UTF_8)){
            
		String line = reader.readLine();
                
                if (line != null) fileData.add(line);
                
                while (line != null) {
                        
                        line = reader.readLine();
                        
                        if (line != null) fileData.add(line);
                }		

        } catch (IOException e) {

        }
        
        return fileData;
    }
    
    public static void writeFile(String fileName, List<String> data){
        
        Path path = Path.of(fileName);
        
        try(BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)){
            for (String line: data){
                writer.write(line + "\n");
            }
        } catch (IOException ex) {
            
        }
    }
    
}
