package pol.una.py.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kpdevila
 */
public class Calculo {
    
    public static final int N = 3;
    
    public static String cacluclarPrecision(float[][] matriz, boolean[][] evaluacion, float cota){
        boolean [][] puntuacion = calcularPuntuacionCota(matriz,cota);
        
//        if (puntuacion != null){
//            int tp =0;
//            int tn =0;
//            int fp =0;
//            int fn =0;
//            int x =matriz.length;
//            int y =matriz[0].length;
//            for(int i=0;i<x;i++){
//                for(int j=0;j<y;j++){
//                    if(puntuacion[i][j] == evaluacion[i][j] && puntuacion[i][j]==true){
//                        tp++;
//                    }
//                    if(puntuacion[i][j] == evaluacion[i][j] && puntuacion[i][j]==false){
//                        tn++;
//                    }
//                    if(puntuacion[i][j] != evaluacion[i][j] && puntuacion[i][j]==false){
//                        fn++;
//                    }
//                    if(puntuacion[i][j] != evaluacion[i][j] && puntuacion[i][j]==true){
//                        fp++;
//                    }
//                }
//            }
//            
//            //System.out.println(String.format("%.2f", cota)+";"+tp+";"+tn+";"+fp+";"+fn);
//            return (tp+" "+tn+" "+fp+" "+fn+"-");
//        }
        return null;
    }
    
    public static String cacluclarPrecisionN(float[][] matriz, boolean[][] evaluacion, float cota){
        boolean [][] puntuacion = calcularPuntuacionN(matriz,cota);
        //boolean [][] aux = calcularPuntuacionN(matriz,cota);
        if (puntuacion != null){
            
//            for(int it=0;it<puntuacion.length;it++){
//               //System.out.print();
//               for(int hi=0;hi<puntuacion[0].length;hi++){
//                   if(puntuacion[it][hi]){
//                       System.out.print("X ");
//                   }
//                   else{
//                       System.out.print("- ");
//                   }
//               }
//               System.out.println("");
//            }
            
            //for(float umbral=(float) 0.1;umbral<=1;umbral=(float) (umbral+0.05)){
                //puntuacion = calcularPuntuacionCotaAndN(matriz,umbral,4);
                Integer tp =0;
                Integer tn =0;
                Integer fp =0;
                Integer fn =0;
                int x =matriz.length;
                int y =matriz[0].length;
                int req=1;
                int k = 0;
                float truePositive=1f;
                //int predictedPositive=0;
               // Float [] precision=new Float[31];
               // Float [] averagePrecision = new Float[31];
                //Integer [] relevantDocument = {3,2,3,3,3,2,1,1,2,2,1,2,2,1,1,1,1,1,1,1,2,2,2,1,1,1,1,1,1,1,1};

                for(int i=0;i<x;i++){
                    for(int j=0;j<y;j++){
                        if(puntuacion[i][j] == evaluacion[i][j] && puntuacion[i][j]==true){
                           tp++;
                        }
                        if(puntuacion[i][j] == evaluacion[i][j] && puntuacion[i][j]==false){
                           tn++;
                        }
                        if(puntuacion[i][j] != evaluacion[i][j] && puntuacion[i][j]==true){
                           fp++;
                        }
                        if(puntuacion[i][j] != evaluacion[i][j] && puntuacion[i][j]==false){
                           fn++;
                        }
                    }


                }
                // imprimir matriz de trazabilidad
    /*            
                for(int i=0;i<x;i++){
                    System.out.print("\\textbf{R-"+(i+1)+"} & ");
                    for(int j=0;j<y;j++){
                        if(puntuacion[i][j] == evaluacion[i][j] && puntuacion[i][j]==true){
                           //tp++;
                           if(j==y-1){
                               System.out.print("\\cellcolor{green} 1 ");
                           }
                           else{
                               System.out.print("\\cellcolor{green} 1 & ");
                           }
                           
                        }
                        if(puntuacion[i][j] == evaluacion[i][j] && puntuacion[i][j]==false){
                           //tn++;
                           if(j==y-1){
                               System.out.print("  ");
                           }
                           else{
                               System.out.print(" & ");
                           }
                           
                        }
                        if(puntuacion[i][j] != evaluacion[i][j] && puntuacion[i][j]==true){
                           //fp++;
                           if(j==y-1){
                               System.out.print("\\cellcolor{red} ");
                           }
                           else{
                               System.out.print("\\cellcolor{red} & ");
                           }
                           
                        }
                        if(puntuacion[i][j] != evaluacion[i][j] && puntuacion[i][j]==false){
                           //fn++;
                           if(j==y-1){
                               System.out.print("1 ");
                           }
                           else{
                               System.out.print("1 & ");
                               
                           }
                            
                        }
                    }
                    System.out.print("\\\\ \\hline");
                    System.out.println("");
                }
        */        
                
                
                // imprimir matriz de similitud ordenada
                Map<String,Object>[][] aux = new Map[x][y];
                for(int i=0;i<x;i++){ 
                    for(int j=0;j<y;j++){
                        aux[i][j] = new HashMap<>();
                    }
                }
                for(int i=0;i<x;i++){
                    //System.out.print("\\textbf{R-"+(i+1)+"} & ");
                    for(int j=0;j<y;j++){
                        if(evaluacion[i][j]){
                            aux[i][j].put("valor", matriz[i][j]);
                            aux[i][j].put("enlace", true);
                           //tp++;
//                           if(j==y-1){
//                               System.out.print("\\cellcolor{green} "+String.format("%.5f", matriz[i][j])+" ");
//                           }
//                           else{
//                               System.out.print("\\cellcolor{green} "+String.format("%.5f", matriz[i][j])+" & ");
//                           }
                           
                        }
                        else{
                            aux[i][j].put("valor", matriz[i][j]);
                            aux[i][j].put("enlace", false);
//                           if(j==y-1){
//                               System.out.print(String.format("%.5f", matriz[i][j])+" ");
//                           }
//                           else{
//                               System.out.print(String.format("%.5f", matriz[i][j])+" & ");
//                           }
                        }
                    }
                   // System.out.print("\\\\ \\hline");
                   // System.out.println("");
                }
                
                for(int i=0;i<x;i++){
                    for(int j=0;j<y;j++){
                        for (int p = 0; p < y - j - 1; p++) { 
                            if ((float)aux[i][p].get("valor") < (float)aux[i][p + 1].get("valor")) { 
  
                                // swapping of elements 
                                float t = (float)aux[i][p].get("valor");
                                boolean b = (boolean)aux[i][p].get("enlace");
                                aux[i][p].put("valor",(float)aux[i][p + 1].get("valor"));
                                aux[i][p].put("enlace",(boolean)aux[i][p + 1].get("enlace")); 
                                aux[i][p + 1].put("valor", t);
                                aux[i][p + 1].put("enlace", b);
                            }
                        } 
                    } 
                }
                
//                for(int i=0;i<x;i++){
//                    System.out.print("\\textbf{R-"+(i+1)+"} & ");
//                    for(int j=0;j<y;j++){
//                        if((boolean)aux[i][j].get("enlace")){
//                            if(j==y-1){
//                               System.out.print("\\cellcolor{green} "+String.format("%.5f", (float)aux[i][j].get("valor"))+" ");
//                           }
//                           else{
//                               System.out.print("\\cellcolor{green} "+String.format("%.5f", (float)aux[i][j].get("valor"))+" & ");
//                           }
//                        }
//                        else{
//                            if(j==y-1){
//                               System.out.print(String.format("%.5f", (float)aux[i][j].get("valor"))+" ");
//                           }
//                           else{
//                               System.out.print(String.format("%.5f", (float)aux[i][j].get("valor"))+" & ");
//                           }
//                        }
//                    }
//                    System.out.print("\\\\ \\hline");
//                    System.out.println("");
//                }
                
                System.out.println(cota+";"+tp+";"+tn+";"+fp+";"+fn);
                Double beneficio = tp.doubleValue()/(tp.doubleValue()+fn.doubleValue());
                Double costo = 1-(tn.doubleValue()/(tn.doubleValue()+fp.doubleValue()));
                System.out.println("Beneficio: "+beneficio);
                System.out.println("Costo: "+costo);
                //return (tp+" "+tn+" "+fp+" "+fn+"-");
                
            //}
            
            return null;
        }
        return null;
    }
    
    
    //Toma como acierto todos los valores mayores a la cota
    private static boolean[][] calcularPuntuacionCota(float[][] matriz,float cota) {
        int x =matriz.length;
        int y =matriz[0].length;
        boolean[][] resp= new boolean[x][y];
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                resp[i][j] = matriz[i][j] >= cota;
            }
        }
        return resp;
    }
    
    
    //Toma como acierto todos los N mayores valores
    private static boolean[][] calcularPuntuacionN(float[][] matriz,float cota) {
        
//        if(!isInteger(String.valueOf(cota*10).substring(0,3)))
//            return null;
        
//        int n= (int)(cota*10);
        int n= (int)(cota);
        int x =matriz.length;
        int y =matriz[0].length;
        boolean[][] resp= new boolean[x][y];
        List<Float> aux = new ArrayList<>();
        
        for(int i=0;i<x;i++){
            aux.clear();
            for(int j=0;j<y;j++){
                if(matriz[i][j] > 0f){
                    aux.add(matriz[i][j]);
                }
            }
            
            Collections.sort(aux);
            
            int max = aux.size();
            int min = aux.size() > n ? aux.size()-n : 0; 
            aux=aux.subList(min, max);
            
            for(int j=0;j<y;j++){
                resp[i][j] = aux.contains(matriz[i][j]);
            }
        }
        
        return resp;
    }
    
    private static boolean[][] calcularPuntuacionCotaAndN(float[][] matriz,float cota, int n) {
        
        //if(!isInteger(String.valueOf(cota*10).substring(0,3)))
           // return null;
        
        //int n= (int)(cota*10);
        int x =matriz.length;
        int y =matriz[0].length;
        boolean[][] resp= new boolean[x][y];
        List<Float> aux = new ArrayList<>();
        
        for(int i=0;i<x;i++){
            aux.clear();
            for(int j=0;j<y;j++){
                if(matriz[i][j] > 0f){
                    aux.add(matriz[i][j]);
                }
            }
            
            Collections.sort(aux);
            
            int max = aux.size();
            int min = aux.size() > n ? aux.size()-n : 0; 
            aux=aux.subList(min, max);
            
            for(int j=0;j<y;j++){
                if(matriz[i][j] >= cota){
                    resp[i][j] = aux.contains(matriz[i][j]);
                }    
            }
        }
        
        return resp;
    }
    
    public static boolean isInteger(String s) {
        if(s.contains(".0") || s.contains("10."))    
            return true;
        else     
            return false;
    }
    
}
