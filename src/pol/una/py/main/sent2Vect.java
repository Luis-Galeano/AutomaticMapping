/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pol.una.py.main;

import java.io.File;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import pol.una.py.util.Calculo;
import pol.una.py.util.Constants;
import pol.una.py.util.Util;

/**
 *
 * @author kpdevila
 */
public class sent2Vect {
    
    public static void main(String[] args) {
        
        try {
            PrintWriter docWriter = new PrintWriter(Constants.S2V_DIR);
            int dim = 600;
            float [][] matriz2 = new float [31][12];
           //for(int dim =100;dim<=700;dim=dim+100){
//                for(int mintCount=5;mintCount<=20;mintCount++){
//                    for(double hyperParam=0.000006d; hyperParam<=0.0001d;hyperParam=hyperParam+0.00001d){
//                        for(int epoch =1;epoch<=12;epoch++){
                            
                            //Util.generarModeloSent2Vect(dim, 5, 0.0001, 5);
                            //Util.generarEspacioVectorial();
                            File hippaFile = new File("/opt/s2v/espacio_vectorial.txt");
                            String hippaString;
                            byte [] hippaBytes;

                            hippaBytes = FileUtils.readFileToByteArray(hippaFile);
                            hippaString= new String (hippaBytes,"UTF-8");

                            String[] lineas = hippaString.split("\n");
                            double[] req = new double[dim];
                            double[] sec = new double[dim];

                            for(int r=0;r<31;r++){
                                req = new double[dim];
                                for(int i=0;i<dim;i++){
                                    req[i]= Double.parseDouble(lineas[r].split(" ")[i]);
                                }

                                for(int s=31;s<43;s++){
                                    sec = new double[dim];
                                    for(int i=0;i<dim;i++){
                                        sec[i]= Double.parseDouble(lineas[s].split(" ")[i]);
                                    }
                                    matriz2[r][s-31]=(float)cosineSimilarity(req,sec);

                                }

                            }


                            for (float j=0;j<=12f;j=j+1f){
                                String r = Calculo.cacluclarPrecisionN(matriz2,Util.HIPAA_ITRUST_VALUES,j);
                                String s =(r+String.format("%.0f", j)+" "+dim+" "+5+" "+0.0001+" "+10);
                                docWriter.write(s.concat("\n"));
                            }
//                            for (float i=0;i<=1.05f;i=i+0.05f){
//                                String r = Calculo.cacluclarPrecision(matriz2,Util.HIPAA_ITRUST_VALUES,i);
//                                String s =(r+String.format("%.2f", i));
//                                docWriter.write(s.concat("\n"));
//                            }
                            

//                        }
//                   }
//                }
            //}
            docWriter.close();
        } catch (Exception ex) {
            Logger.getLogger(TFHubUniversalEncoder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    public static double cosineSimilarity(double[] vectorA, double[] vectorB) {
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            normA += Math.pow(vectorA[i], 2);
            normB += Math.pow(vectorB[i], 2);
        }   
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }
    
}
