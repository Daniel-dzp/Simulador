package pruebas.pruebas;

import java.util.ArrayList;

/**
 * @author dzp
 */
public class Huecos {
    int numeros[];
    public String procedimiento;
    public String hipotesis;
    public Huecos(double numeros[], boolean esDecimal,int noDigitos){
        int temp;
        char a;
        
        procedimiento = "";
        hipotesis = "";
        
        if(esDecimal)
        {
            this.numeros = new int[numeros.length*noDigitos];
        
            for(int i=0;i<numeros.length;i++)
            {
                temp =  (int) (numeros[i]*Math.pow(10,noDigitos));
                for(int j=0;j<noDigitos;j++)
                {
                    this.numeros[i*noDigitos+j] = j < noDigitos-String.valueOf(temp).length()
                            ? 0 
                            : Integer.parseInt(String.valueOf(temp).charAt(String.valueOf(temp).length()+j-noDigitos)+"");                    
                }
            }
        }
    }
    
    public void metodo()
    {
        int n;
        ArrayList<Integer> listaRepetidos = new ArrayList();
        ArrayList<Integer[]> listaTablas = new ArrayList();
        Integer tablaHuecos[];
        int posicionInicial;
        int longitud;
        boolean encontrado;
        boolean salir;
        int noDeHuecosTotales = 0;
        int tablaHuecosTotales[][];
        int dato=0, noHuecos=0, contadorN=0;
        double tabla[][] = new double[12][7];
        int a, b;
        double max = -1;
        double D, Dalfa;
        
        // mostrar numeros
        procedimiento += "Numero de datos(N) = "+numeros.length+"\n";
        procedimiento += "Datos"+"\n";
        for(int i=0;i<numeros.length/30+1;i++){
            for(int j=0;j<30;j++)
                if(numeros.length> i*30+j)
                    procedimiento += numeros[i*30+j]+"  ";
            procedimiento += "\n";
        }
        
        //Buscar numeros repetidos
        n = numeros.length;
        
        for(int i=0;i<n;i++)
            if(!existeLista(numeros[i], listaRepetidos))
                listaRepetidos.add(numeros[i]);
        
        // ordenar numeros
        listaRepetidos.sort((o1, o2) -> o1.compareTo(o2));
        
        // mostrar numeros
        procedimiento += "\nNumeros: ";
        for(int i=0;i<listaRepetidos.size();i++)
            procedimiento += listaRepetidos.get(i)+"  ";
        procedimiento += "\n";
        
        // crear tabla de huecos y logitud
        tablaHuecosTotales = new int[2][listaRepetidos.size()];
        for(int i=0;i<listaRepetidos.size();i++)
        {
            dato = listaRepetidos.get(i);
            
            contadorN = contarNumero(dato,numeros);
            noHuecos = contadorN-1;
            noDeHuecosTotales += noHuecos;
            
            tablaHuecos = new Integer[noHuecos];
            for(int k=0;k<noHuecos;k++)
                tablaHuecos[k] = new Integer(0);
            
            posicionInicial = 0;
            encontrado = false;
            while(!encontrado)
            {
                if(numeros[posicionInicial] == dato)
                    encontrado = true;
                else
                    posicionInicial++;
            }
            
            for(int j=0;j<noHuecos;j++)
            {
                salir = false;
                do{
                    posicionInicial ++;
                    if(numeros[posicionInicial] != dato)
                        tablaHuecos[j]++;
                    else
                        salir = true;
                }while(!salir);
            }        
            
            // calcular no huecos por tabla
            tablaHuecosTotales[0][i] = listaRepetidos.get(i);
            tablaHuecosTotales[1][i] = noHuecos;
            
            listaTablas.add(tablaHuecos);
            
            // mostrar la tabla de huecos y logitud por cada digito
            procedimiento += "\nTABLA DE DIGITO: "+dato+"\n";
            procedimiento += String.format("%10s","Huecos:");
            for(int t=0;t<tablaHuecos.length;t++)
                procedimiento += String.format(" %3d",(t+1));
            procedimiento += "\n";
            procedimiento += String.format("%10s","Longitud:");
            for(int t=0;t<tablaHuecos.length;t++)
                procedimiento += String.format(" %3d",tablaHuecos[t]);
            procedimiento += "\n";
        }
        
        // mostrar tabla de no. huecos totales
        procedimiento += String.format("\n%-20s", "Digitos");
        for(int i=0;i<listaRepetidos.size();i++)
        {
            procedimiento += String.format("%3d ", tablaHuecosTotales[0][i]);
        }
        procedimiento += String.format("\n%-20s", "Numero de huecos");
        for(int i=0;i<listaRepetidos.size();i++)
        {
            procedimiento += String.format("%3d ", tablaHuecosTotales[1][i]);
        }
        
        
        // calcular la tabla
        // longitud hueco = i*4, i*4+3
        tabla = new double[12][7];
        
        for(int i=0;i<tabla.length;i++)
        {
            // calcular la Frecuencia
            a = i*4;
            b = i*4+3;
            
            // calcular la Frecuencia
            tabla[i][0] = contarFrecuencias(a,b, listaTablas);
            
            // calcular Frecuancia relativa
            tabla[i][1] = tabla[i][0] / noDeHuecosTotales;
            
            // calcular frecuencia relativa acomulada S(x)
            if(i==0)
                tabla[i][2] = tabla[i][1];
            else
                tabla[i][2] = tabla[i][1] +tabla[i-1][2];
            
            // calcular  x+1
            tabla[i][3] = b+1;
            
            // calcular (0.9)^(x+1)
            tabla[i][4] = Math.pow(0.9, tabla[i][3]);
            
            // calcular F(x) = 1 - (0.9)^(x+1)
            tabla[i][5] = 1 -tabla[i][4];
            
            // calcular |F(x)-S(x)|
            tabla[i][6] = Math.abs(tabla[i][5] - tabla[i][2]);
            
            // max
            max = Math.max(max, tabla[i][6]);
        }
        
        // mostar tabla
        procedimiento += "\n\nTabla\n";
        procedimiento += String.format("%-12s %-12s %-13s %-20s %-5s %-13s %-20s %-15s\n","Long Hueco","Frecuencia","F. Relativa","F.R. Acomulada S(x)","x+1","(0.9)^(x+1)","F(x)=1-(0.9)^(x+1)","|F(x)-S(x)|");
        for(int i=0;i<tabla.length;i++)
        {
            a = i*4;
            b = i*4+3;
            procedimiento += String.format("%2d-%2d%7s ",a,b,"");
            procedimiento += String.format("%3.0f%9s ",tabla[i][0],"");
            procedimiento += String.format("%1.2f%9s ",tabla[i][1],"");
            procedimiento += String.format("%1.2f%16s ",tabla[i][2],"");
            procedimiento += String.format("%3.0f%2s ",tabla[i][3],"");
            procedimiento += String.format("%1.4f%7s ",tabla[i][4],"");
            procedimiento += String.format("%1.4f%14s ",tabla[i][5],"");
            procedimiento += String.format("%1.4f%9s ",tabla[i][6],"");
            
            procedimiento += "\n";
        }
        
        max = (double)((int)(max*10000))/10000;
        
        D = max;
        Dalfa = (1.6/Math.sqrt(noDeHuecosTotales));
        hipotesis += String.format("\nD = max |F(x)-S(x)| = "+ D+"\n");
        hipotesis += String.format("Dα = 1.36/√(N) = "+"1.36/√("+noDeHuecosTotales+") = "+Dalfa+"\n");
        
        if(D<Dalfa)
            hipotesis += String.format("Ho: los datos son estadisticamente independientes"+"\n");
        else
            hipotesis += String.format("H1: los datos no son estadisticamente independientes"+"\n");
        
        
    }
    
    public int contarFrecuencias(int a, int b, ArrayList<Integer[]> listaTablas)
    {
        int frecuencias=0;
        Integer tablaHuecos[];
        
        for(int i=0;i<listaTablas.size();i++)
        {
            tablaHuecos = listaTablas.get(i);
            
            for(int j=0;j<tablaHuecos.length;j++)
                if(a<=tablaHuecos[j] && b>=tablaHuecos[j])
                    frecuencias++;
        }
        
        return frecuencias;
    }
    
    public int contarNumero(int dato, int[] numeros)
    {
        int cont = 0;
        
        for(int i=0;i<numeros.length;i++)
            if(numeros[i] == dato)
                cont++;
        
        return cont;
    }
    
    public boolean existeLista(Integer numero, ArrayList numeros)
    {
        for(int i=0;i<numeros.size();i++)
            if(numeros.get(i) == numero)
                return true;
        
        return false;
    }
}
