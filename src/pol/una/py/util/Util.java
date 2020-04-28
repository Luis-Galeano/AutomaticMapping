package pol.una.py.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author kpdevila
 */
public class Util {
    
    public static final boolean[][] HIPAA_ITRUST_VALUES=
        {{true,true,false,false,false,false,false,false,true,false,false,false},
        {false,false,false,true,false,false,false,false,true,false,false,false},
        {true,false,false,true,false,false,false,false,true,false,false,false},
        {true,false,false,false,false,true,false,false,true,false,false,false},
        {true,false,false,false,false,true,false,false,true,false,false,false},
        {true,false,false,false,false,false,false,false,true,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false,false},
        {false,false,false,false,false,false,true,true,false,false,false,false},
        {false,false,false,false,false,false,true,true,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false,false},
        {false,false,false,false,false,false,true,true,false,false,false,false},
        {false,false,false,false,false,false,true,true,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false,false},
        {true,false,false,false,false,false,false,false,false,false,false,false},
        {true,false,false,false,false,false,false,false,false,false,false,false},
        {true,false,false,false,false,false,false,false,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false,false},
        {true,false,false,false,false,false,true,false,false,false,false,false},
        {true,false,false,false,false,false,true,false,false,false,false,false},
        {true,false,false,false,false,false,true,false,false,false,false,false},
        {false,false,true,false,false,false,false,false,false,false,false,false},
        {false,false,true,false,false,false,false,false,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false,false},
        {false,false,false,false,false,false,false,false,false,true,false,false},
        {false,false,false,false,false,false,false,false,false,false,false,true},
        {false,false,false,false,false,false,false,false,false,false,false,true},
        {false,false,false,false,false,false,false,false,false,false,false,true}};
    
   public static final boolean[][] HIPAA_ISO_VALUES=
        {{true,false,false,false,false,false,true,false,false,false,false,false},
        {true,false,false,false,false,false,false,true,false,true,true,true},
        {true,false,false,false,false,true,false,false,false,false,false,false},
        {true,false,false,false,false,false,false,false,false,false,false,false},
        {true,true,true,false,true,false,false,false,false,false,false,false},
        {true,true,true,false,false,false,false,false,false,false,false,false},
        {true,false,false,false,false,false,false,false,false,false,false,false},
        {true,true,true,false,true,false,false,false,true,false,false,false},
        {true,false,false,false,false,true,true,false,false,false,false,false},
        {false,true,true,false,false,false,false,false,false,false,false,false},
        {false,true,true,false,false,false,false,false,false,false,false,false},
        {false,true,true,false,false,false,false,false,false,false,false,false},
        {false,false,true,false,false,false,false,false,false,false,false,false},
        {false,false,true,false,false,false,false,false,false,false,false,false},
        {false,false,true,false,false,false,false,false,false,false,false,false},
        {false,true,true,false,false,false,false,false,false,false,false,false},
        {false,true,true,false,false,false,false,false,false,false,false,false},
        {false,false,true,false,false,false,false,false,false,false,false,false},
        {false,false,true,false,false,false,false,false,false,false,false,false},
        {false,false,false,true,false,false,false,false,false,false,false,false},
        {false,false,false,true,false,false,false,false,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false,false},
        {false,false,false,false,false,true,true,false,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false,false},
        {false,false,false,false,false,false,true,false,false,false,false,false},
        {false,false,false,false,false,false,false,false,false,false,false,false},
        {false,false,false,false,false,false,true,true,false,true,true,true},
        {false,false,false,false,false,false,true,false,false,false,false,false},
        {false,false,false,false,false,false,true,true,false,true,true,true},
        {false,true,false,false,false,false,true,false,true,false,false,false},
        {false,false,false,false,false,false,true,true,false,false,true,false},
        {false,false,false,false,false,false,true,true,false,false,true,false},
        {false,false,false,false,false,false,true,true,false,false,true,false},
        {false,true,false,false,false,false,false,false,true,false,false,false},
        {false,true,false,false,false,false,false,false,true,false,false,false},
        {false,true,false,false,false,false,false,false,true,false,false,false},
        {false,true,false,false,false,false,false,false,true,false,false,false},
        {false,false,false,false,false,false,false,false,false,false,false,false},
        {false,false,false,false,false,false,false,false,false,false,false,true},
        {false,false,false,false,false,false,false,false,false,false,false,true}};
   
   
    public static String removeSpecialWords(String instring) throws IOException{
        File stopWordsFile = new File(Constants.STOP_WORDS_DIR);
        String stopwords = new String(FileUtils.readFileToByteArray(stopWordsFile));
        BufferedReader stopBr = new BufferedReader(new StringReader(stopwords));
        String stopWord;
        while((stopWord = stopBr.readLine()) != null){
            if (instring.contains(stopWord)){
                instring = instring.replaceAll("\\b".concat(stopWord).concat("\\b"), "");     
            }
        }
        instring = instring.replaceAll("and\\/or", "");
        instring = instring.toLowerCase();
        return instring;
    }

public static boolean[] booleans={true,false};

    public static void generarModeloSent2Vect(int dimension, int minCount, double hyperParam, int epoch){
        try {
            String exec = "/opt/s2v/fasttext sent2vec -input /opt/s2v/trainS2V.txt -output /opt/s2v/my_model -dim <dimension> -minCount <minCount> -t <hyper-parameter> -epoch <epoch>";
            
            exec = exec.replaceAll("<dimension>", String.valueOf(dimension));
            exec = exec.replaceAll("<minCount>", String.valueOf(minCount));
            exec = exec.replaceAll("<hyper-parameter>", String.valueOf(hyperParam));
            exec = exec.replaceAll("<epoch>", String.valueOf(epoch));
            
            Runtime rt = Runtime.getRuntime();
            
            Process pr = rt.exec(exec);
           // System.out.println(exec);
            BufferedReader reader = new BufferedReader(new InputStreamReader(pr.getInputStream()));
//            String line = null;
            System.out.println("Inicia creacion de modelo");
            while (reader.readLine() != null){
                
            }
            System.out.println("Modelo creado");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public static void generarEspacioVectorial(){
        try {
            
            Runtime rt = Runtime.getRuntime();
            String[] cmd = { "sh", "/opt/s2v/espacioVectorial.sh"};
            Process p = rt.exec(cmd);
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
           
            while (reader.readLine() != null){}
            
            
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
}
