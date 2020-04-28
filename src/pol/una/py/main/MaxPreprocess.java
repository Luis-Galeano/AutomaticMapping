/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pol.una.py.main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import pol.una.py.util.Calculo;
import pol.una.py.util.Constants;
import pol.una.py.util.Util;
import semilar.config.ConfigManager;
import semilar.data.Sentence;
import semilar.tools.preprocessing.SentencePreprocessor;

/**
 *
 * @author kpdevila
 */
public class MaxPreprocess {
    public static void main(String[] args) {
        try {
            ConfigManager.setSemilarDataRootFolder(Constants.DATA_DIR);
            Comparer comparer = new Comparer();
            PrintWriter docWriter = new PrintWriter(Constants.RESULT_DIR);
            
            Sentence text1;
            Sentence text2;

            File iTrustFile = new File(Constants.ITRUST_DIR);
            byte [] iTrustBytes;
            String iTrustString;

            File hippaFile = new File(Constants.HIPAA_DIR);
            String hippaString;
            byte [] hippaBytes;


            iTrustBytes = FileUtils.readFileToByteArray(iTrustFile);
            iTrustString= new String (iTrustBytes,"UTF-8");
            iTrustString = Util.removeSpecialWords(iTrustString);

            hippaBytes = FileUtils.readFileToByteArray(hippaFile);
            hippaString= new String (hippaBytes,"UTF-8");
            hippaString = Util.removeSpecialWords(hippaString);

            String[] hipaa = hippaString.split("\\.");
            String[] itrust = iTrustString.split("\\.");
            
            String r=null;
            String s=null;
            String a = "bleuComparer";
            String p=null;
            for(SentencePreprocessor.TokenizerType tokenizerType : SentencePreprocessor.TokenizerType.values() ){
                for(SentencePreprocessor.TaggerType taggerType : SentencePreprocessor.TaggerType.values() ){
                    for(SentencePreprocessor.StemmerType stemmerType : SentencePreprocessor.StemmerType.values() ){
                        for(SentencePreprocessor.ParserType parserType : SentencePreprocessor.ParserType.values() ){
                            
                            SentencePreprocessor preprocessor = new SentencePreprocessor(tokenizerType, taggerType, stemmerType, parserType);
            
                            try{
                                float [][] matriz = new float [itrust.length][hipaa.length];
                                for(int i=0;i<itrust.length;i++){
                                    text1 = preprocessor.preprocessSentence(itrust[i]);
                                    for(int j=0;j<hipaa.length;j++){
                                        text2 = preprocessor.preprocessSentence(hipaa[j]);
                                        matriz [i][j]= comparer.getSimilarities(text1, text2);
                                    }
                                }
                                p = tokenizerType.toString()+" "+taggerType.toString()+" "+stemmerType.toString()+" "+parserType.toString();  
                                for (float i=0.0f;i<=1.05f;i=i+0.05f){
                                    r = Calculo.cacluclarPrecision(matriz,Util.HIPAA_ITRUST_VALUES,i);
                                    s =(r+String.format("%.2f", i)+" "+p+" "+a);
                                    docWriter.write(s.concat("\n"));
                                }
                                
                                for (float j=0.0f;j<=10.0f;j=j+1.0f){
                                    r = Calculo.cacluclarPrecisionN(matriz,Util.HIPAA_ITRUST_VALUES,j);
                                    s =(r+String.format("%.0f", j)+" "+p+" "+a);
                                    docWriter.write(s.concat("\n"));
                                }
                                
                            }catch (Exception e){
                                System.err.println(e.getMessage());
                            }
                        
                        }
                        r=null;
                        s=null;
                    }
                }
            } 
            docWriter.close();
            System.out.println("\nDone!");
        } catch (IOException ex) {
            Logger.getLogger(ItrustHipaa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}
