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
 *
 * @author kpdevila
 */
public class IsoHipaa {
    
    public static void main(String[] args) {
        try {
            ConfigManager.setSemilarDataRootFolder(Constants.DATA_DIR);
            Comparer comparer = new Comparer();

            Sentence text1;
            Sentence text2;

            File isoFile = new File(Constants.ISO_DIR);
            byte [] isoBytes;
            String isoString;

            File hippaFile = new File(Constants.HIPAA_DIR);
            String hippaString;
            byte [] hippaBytes;


            isoBytes = FileUtils.readFileToByteArray(isoFile);
            isoString= new String (isoBytes,"UTF-8");
            isoString = Util.removeSpecialWords(isoString);

            hippaBytes = FileUtils.readFileToByteArray(hippaFile);
            hippaString= new String (hippaBytes,"UTF-8");
            hippaString = Util.removeSpecialWords(hippaString);

            String[] hipaa = hippaString.split("\\.");
            String[] iso = isoString.split("\\.");

            SentencePreprocessor preprocessor = new SentencePreprocessor(SentencePreprocessor.TokenizerType.STANFORD, SentencePreprocessor.TaggerType.OPENNLP_PERCEPTRON, SentencePreprocessor.StemmerType.STANFORD, SentencePreprocessor.ParserType.NONE);

            float [][] matriz = new float [iso.length][hipaa.length];
            for(int i=0;i<iso.length;i++){
                text1 = preprocessor.preprocessSentence(iso[i]);
                for(int j=0;j<hipaa.length;j++){
                    text2 = preprocessor.preprocessSentence(hipaa[j]);
                    matriz [i][j]= comparer.getSimilarities(text1, text2);
                }
            }
            System.out.println("\nHIPAA_ISO\n");
            
            
            for (float j=0;j<=12f;j=j+1f){
                Calculo.cacluclarPrecisionN(matriz,Util.HIPAA_ISO_VALUES,j);
            }

            System.out.println("\nDone!");
        } catch (IOException ex) {
            Logger.getLogger(ItrustHipaa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
