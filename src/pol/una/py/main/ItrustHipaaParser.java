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
public class ItrustHipaaParser {
    
    public static void main(String[] args) {
        try {
            ConfigManager.setSemilarDataRootFolder(Constants.DATA_DIR);
            

            File iTrustFile = new File(Constants.ITRUST_PARSER_DIR);
            byte [] iTrustBytes;
            String iTrustString;

            File hippaFile = new File(Constants.HIPAA_PARSER_DIR);
            String hippaString;
            byte [] hippaBytes;


            iTrustBytes = FileUtils.readFileToByteArray(iTrustFile);
            iTrustString= new String (iTrustBytes,"UTF-8");
            iTrustString = Util.removeSpecialWords(iTrustString);

            hippaBytes = FileUtils.readFileToByteArray(hippaFile);
            hippaString= new String (hippaBytes,"UTF-8");
            hippaString = Util.removeSpecialWords(hippaString);

            String[] hipaa = hippaString.split("\n");
            String[] itrust = iTrustString.split("\n");
            
                        
            
                
            float [][] matriz = new float [itrust.length][hipaa.length];
            for(int i=0;i<itrust.length;i++){
                for(int j=0;j<hipaa.length;j++){
                    matriz [i][j]= calcularSimilitudParser(itrust[i], hipaa[j]);
                }
            }
            
            for(int it=0;it<itrust.length;it++){
                for(int hi=0;hi<hipaa.length;hi++){
                    System.out.print(String.format("%.6f", matriz[it][hi])+" ");
                }
                System.out.println();
            }

            System.out.println();
//            for (float i=0;i<=1.05f;i=i+0.05f){
//                Calculo.cacluclarPrecision(matriz,Util.HIPAA_ITRUST_VALUES,i);
//            }
            
            for (float j=0;j<=12f;j=j+1f){
                Calculo.cacluclarPrecisionN(matriz,Util.HIPAA_ITRUST_VALUES,j);
            }

            System.out.println("----------------------------------");
            
            System.out.println("\nDone!");
        } catch (IOException ex) {
            Logger.getLogger(ItrustHipaaParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static float calcularSimilitudParser(String iTrust, String hipaa){
        Float resp = null;
        
        try {
            SentencePreprocessor preprocessor = new SentencePreprocessor(SentencePreprocessor.TokenizerType.OPENNLP, SentencePreprocessor.TaggerType.OPENNLP_PERCEPTRON, SentencePreprocessor.StemmerType.STANFORD, SentencePreprocessor.ParserType.NONE);
            
            Comparer comparer = new Comparer();

            Sentence[] hipaaSentence = new Sentence[3];
            Sentence[] iTrustSentence = new Sentence[3];
            
            String[] iTrustEntidades = iTrust.split("-");
            String[] hipaaEntidades = hipaa.split("-");
            
            String iTrustTopic = iTrustEntidades[0];
            String iTrustSubject = iTrustEntidades[1];
            String iTrustAction = iTrustEntidades[2];
            
            String hipaaTopic = hipaaEntidades[0];
            String hipaaSubject = hipaaEntidades[1];
            String hipaaAction = hipaaEntidades[2];
            
            Float topicScore = null;
            Float subjectScore = null;
            Float actionScore = null;
            
            iTrustSentence[0] = preprocessor.preprocessSentence(iTrustTopic);
            iTrustSentence[1] = preprocessor.preprocessSentence(iTrustSubject);
            iTrustSentence[2] = preprocessor.preprocessSentence(iTrustAction);
            
            hipaaSentence[0] = preprocessor.preprocessSentence(hipaaTopic);
            hipaaSentence[1] = preprocessor.preprocessSentence(hipaaSubject);
            hipaaSentence[2] = preprocessor.preprocessSentence(hipaaAction);
            
            topicScore = comparer.getSimilarities(iTrustSentence[0], hipaaSentence[0]);
            subjectScore = comparer.getSimilarities(iTrustSentence[1], hipaaSentence[1]);
            actionScore = comparer.getSimilarities(iTrustSentence[2], hipaaSentence[2]);
            
            if (Float.isNaN(topicScore)){
                topicScore = 0f;
            }
            
            if(Float.isNaN(subjectScore)){
                subjectScore=0f;
            }
            if(Float.isNaN(actionScore)){
                actionScore=0f;
            }
            System.out.println(topicScore+"|"+subjectScore+"|"+actionScore);
            if (topicScore >= subjectScore & topicScore >= actionScore){
                resp= topicScore;
            }
            else if (subjectScore >= topicScore & subjectScore >= actionScore){
                resp= actionScore;
            }
            else if(actionScore >= subjectScore & actionScore >= topicScore){
                resp= actionScore;   
            }
            
            
            
        } catch (Exception e) {
            System.err.println(e);
        }
        return resp;
    }
}
