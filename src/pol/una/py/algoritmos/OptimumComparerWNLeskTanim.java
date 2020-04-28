package pol.una.py.algoritmos;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import pol.una.py.main.Comparer;
import pol.una.py.main.ItrustHipaa;
import pol.una.py.util.Calculo;
import pol.una.py.util.Constants;
import pol.una.py.util.Util;
import semilar.config.ConfigManager;
import semilar.data.Sentence;
import semilar.sentencemetrics.OptimumComparer;
import semilar.sentencemetrics.PairwiseComparer;
import semilar.sentencemetrics.PairwiseComparer.NormalizeType;
import semilar.sentencemetrics.PairwiseComparer.WordWeightType;
import semilar.tools.preprocessing.SentencePreprocessor;
import semilar.tools.semantic.WordNetSimilarity;
import semilar.tools.semantic.WordNetSimilarity.WNSimMeasure;
import semilar.wordmetrics.WNWordMetric;

/**
 *
 * @author kpdevila
 */
public class OptimumComparerWNLeskTanim {
    public static void main(String[] args) {
        try {
            ConfigManager.setSemilarDataRootFolder(Constants.DATA_DIR);
            PrintWriter docWriter = new PrintWriter(Constants.RESULT_DIR);
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

            String[] hipaa = hippaString.split("\\.");
            String[] itrust = iTrustString.split("\\.");
            
            SentencePreprocessor preprocessor = new SentencePreprocessor(SentencePreprocessor.TokenizerType.OPENNLP,  SentencePreprocessor.TaggerType.OPENNLP_ENTROPY, SentencePreprocessor.StemmerType.WORDNET, SentencePreprocessor.ParserType.NONE);
            
            float [][] matriz = new float [itrust.length][hipaa.length];
            String r=null;
            String s=null;
            String a = "OptimumComparerWNLeskTanim";
            String p=null;
            for(WNSimMeasure wNSimMeasure : WordNetSimilarity.WNSimMeasure.values()){
                for (boolean wnFirstSenseOnly : Util.booleans){
                    WNWordMetric wnMetricLin = new WNWordMetric(wNSimMeasure, wnFirstSenseOnly);
                    for (float wSim=0;wSim<=1.05f;wSim=wSim+0.1f){
                        for (boolean baseForm : Util.booleans){
                            for (WordWeightType wordWeightType : PairwiseComparer.WordWeightType.values()){
                                for(NormalizeType normalizeType: PairwiseComparer.NormalizeType.values()){
                                    comparer.setOptimumComparerWNLeskTanim(new OptimumComparer(wnMetricLin, wSim, baseForm, wordWeightType, normalizeType));
                                    try{
                                  
                                        for(int i=0;i<itrust.length;i++){
                                            text1 = preprocessor.preprocessSentence(itrust[i]);
                                            for(int j=0;j<hipaa.length;j++){
                                                text2 = preprocessor.preprocessSentence(hipaa[j]);
                                                matriz [i][j]= comparer.getSimilarities(text1, text2);
                                            }
                                        }

                                        
                                        p = wNSimMeasure.toString()+" "+wnFirstSenseOnly+" "+wSim+" "+baseForm+" "+wordWeightType.toString()+" "+normalizeType.toString();

                                        for (float i=0.0f;i<=1.05f;i=i+0.05f){
                                            r = Calculo.cacluclarPrecision(matriz,Util.HIPAA_ITRUST_VALUES,i);
                                            s =(r+String.format("%.2f", i)+" "+p+" "+a);
                                            docWriter.write(s.concat("\n"));
                                        }

                                        for (float j=0.0f;j<=12.0f;j=j+1.0f){
                                            r = Calculo.cacluclarPrecisionN(matriz,Util.HIPAA_ITRUST_VALUES,j);
                                            s =(r+String.format("%.0f", j)+" "+p+" "+a);
                                            docWriter.write(s.concat("\n"));
                                        }
                                    }catch (Exception e){
                                        System.err.println(e.getMessage());
                                    }
                                }
                            }
                        }
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
