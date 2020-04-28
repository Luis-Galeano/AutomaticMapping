
package pol.una.py.main;


import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

/**
 *
 * @author kpdevila
 */
public class MainResultados {
    public static void main(String[] args) {
        try {
            System.out.println("Leyendo XML...");
            List<Resultado> result = new ArrayList<>();
            
            File file = new File("/home/konecta/Vídeos/result/resultado.txt");
            String line;
            String r;
            double tp;
            double tn;
            double fp;
            double fn;
            double contLinea=0;
            double sensibilidad;
            double exactitud;
            double precision;
            double b = 2;
            double fScore;
            Resultado resultado =null;
            LineIterator it = FileUtils.lineIterator(file, "UTF-8");
            try {
                while (it.hasNext()) {
                    line = it.nextLine();
                    r = line.split("-")[0];
                    tp = Double.valueOf(r.split(" ")[0]);
                    tn = Double.valueOf(r.split(" ")[1]);
                    fp = Double.valueOf(r.split(" ")[2]);
                    fn = Double.valueOf(r.split(" ")[3]);
                    sensibilidad = tp /( tp + fn);
                    precision = tp /(tp + fp);
                    exactitud = (tp+tn)/(tp+tn+fp+fn);
//                    if (sensibilidad > 0  && precision>0 && line.contains("greedyComparerWNLin")){
//                        fScore = (1+b*b)*(precision*sensibilidad)/((b*b*precision)+sensibilidad);
//                        resultado = new Resultado(line, fScore);
//                        result.add(resultado);
//                    }
                    if (precision>0.2 && tp>2 && tp > 16){
                        resultado = new Resultado();
                        resultado.setConfiguracion(line);
                        resultado.setPrecision(precision);
                        resultado.setExactitud(exactitud);
                        result.add(resultado);
                    }
                    
                }           
            } finally {
                LineIterator.closeQuietly(it);
            }
            Collections.sort(result,Collections.reverseOrder());
            
            for (int i=0 ; i < result.size() ; i++)
                System.out.println(result.get(i));
            
            
            System.out.println("FIN!!!...");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
