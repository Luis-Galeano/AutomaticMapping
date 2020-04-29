package pol.una.py.main;

import java.io.File;
import java.io.IOException;
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

 * @author kpdevila
 */
public class ItrustHipaa {
    
    public static void main(String[] args) {
        try {
            ConfigManager.setSemilarDataRootFolder(Constants.SEMILAR_DATA);
            Comparer comparer = new Comparer();

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
            
            hippaString = hippaString.toLowerCase();
            iTrustString = iTrustString.toLowerCase();
            
            String[] hipaa = hippaString.split("\\.");
            String[] itrust = iTrustString.split("\\.");

                        
            SentencePreprocessor preprocessor = new SentencePreprocessor(SentencePreprocessor.TokenizerType.STANFORD, SentencePreprocessor.TaggerType.OPENNLP_PERCEPTRON, SentencePreprocessor.StemmerType.STANFORD, SentencePreprocessor.ParserType.NONE);
                
            float [][] matriz = new float [itrust.length][hipaa.length];
                for(int i=0;i<itrust.length;i++){
                    text1 = preprocessor.preprocessSentence(itrust[i]);
                    for(int j=0;j<hipaa.length;j++){
                        text2 = preprocessor.preprocessSentence(hipaa[j]);
                        matriz [i][j]= comparer.getSimilarities(text1, text2);
                    }
                }
            
            for(int it=0;it<itrust.length;it++){
               //System.out.print("textbf{R-"+(it+1)+"} ");
               for(int hi=0;hi<hipaa.length;hi++){
                   if(hi == hipaa.length-1){
                       System.out.print(String.format("%.5f", matriz[it][hi]));
                   }
                   else{
                       System.out.print(String.format("%.5f", matriz[it][hi])+";");
                   } 
               }
               System.out.println();
            }

//            System.out.println();
//            for (float i=0.05f;i<=1.05f;i=i+0.05f){
//                Calculo.cacluclarPrecisionN(matriz,Util.HIPAA_ITRUST_VALUES,i);
//            }
            
//            for (float j=0;j<=12f;j=j+1f){
//                Calculo.cacluclarPrecisionN(matriz,Util.HIPAA_ITRUST_VALUES,j);
//            }
              Calculo.cacluclarPrecisionN(matriz,Util.HIPAA_ITRUST_VALUES,4);

            System.out.println("----------------------------------");
            
            System.out.println("\nDone!");
        } catch (IOException ex) {
            Logger.getLogger(ItrustHipaa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
