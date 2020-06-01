package pruebas.pruebas;
/**
 * @author dzp
 */
public class Poker {
    public double[] num;
    public String procedimiento;
    public String hipotesis;
    
    public Poker(double [] num){
        this.num = num;
    }
    
    public boolean metodo(){
        procedimiento = "";
        hipotesis = "";
        //Freuencia observada
        int td=0, unpar=0, dospar=0, tercia=0, poker=0;
        //Frecuancia esperada
        double fe_td=0.504*num.length, fe_unpar=0.432*num.length, fe_dospar=0.027*num.length, fe_tercia=0.036*num.length, fe_poker=0.001*num.length;

        //estadistico
        double x=0;

        String [] digito = new String[4];
        
        hipotesis = "N = "+num.length;
        // mostrar numeros
        procedimiento += "Numero de datos(N) = "+num.length+"\n";
        procedimiento += "Datos"+"\n";
        for(int i=0;i<num.length/15+1;i++){
            for(int j=0;j<15;j++)
                if(num.length> i*15+j)
                    procedimiento += String.format("%1.4f", num[i*15+j])+"  ";
            procedimiento += "\n";
        }

        for(int i=0; i<num.length; i++)
        {
            String numero=completar((int)(num[i]*10000));
            //System.out.println(numero);
            digito[0]=numero.substring(0, 1);
            digito[1]=numero.substring(1, 2);
            digito[2]=numero.substring(2, 3);
            digito[3]=numero.substring(3, 4);

            //Ordena los numeros para que sea facil reconocer las combinaciones
            digito = ordenar(digito);

            //Los cuatro digitos iguales
            if(digito[0].equals(digito[3])){poker++;}
            else
            {
                //Tres iguales
                if(digito[0].equals(digito[2]) || digito[1].equals(digito[3])) {tercia++; }
                else
                {
                    //Dos pares
                    if(digito[0].equals(digito[1]) && digito[2].equals(digito[3])){dospar++;}
                    else
                    {
                        //Un par
                        if(digito[0].equals(digito[1]) || digito[1].equals(digito[2]) || digito[2].equals(digito[3])){unpar++;}
                        //Todos diferentes
                        else{td++;}
                    }
                }
            }
        }
        
        procedimiento += "\n Poker = "+poker;
        procedimiento += "\n Tercia = "+tercia;
        procedimiento += "\n Dos pares = "+dospar;
        procedimiento += "\n Un par = "+unpar;
        procedimiento += "\n Todos diferentes = "+td;
        
        hipotesis += "\n Poker = "+poker;
        hipotesis += "\n Tercia = "+tercia;
        hipotesis += "\n Dos pares = "+dospar;
        hipotesis += "\n Un par = "+unpar;
        hipotesis += "\n Todos diferentes = "+td;

        //(Ei-Oi)^2/Ei
        double com1=(Math.pow((fe_td-td),2))/fe_td,
               com2=(Math.pow((fe_unpar-unpar),2))/fe_unpar,
               com3=(Math.pow((fe_dospar-dospar),2))/fe_dospar,
               com4=(Math.pow((fe_tercia-tercia),2))/fe_tercia,
               com5=(Math.pow((fe_poker-poker),2))/fe_poker;
        //Se calcula el estadístico x2
        x=com1+com2+com3+com4+com5;
        hipotesis += "\n x = Σ(Ei-Oi)^2/Ei = "+x;
        //Se comprueba la hipótesis nula con un nivel de significancia de 0.05
        if(x<9.49){        
            hipotesis += "\nx<9.49";
            hipotesis += "\nLos datos son estadísticamente independientes";
            return true;
        }
        else{
            hipotesis += "\nx>=9.49";
            hipotesis += "\nLos datos no son estadísticamente independientes";
            return false;
        }
    
    }
    
    String [] ordenar(String[]numero)
    {
        //Ordena los digitos del numero para que sea mas facil identificar la combinación
        String aux;
        for(int i=0; i<numero.length; i++)
        {
            for(int j=i+1; j<numero.length; j++)
            {
                if(Integer.parseInt(numero[i])>Integer.parseInt(numero[j]))
                {
                    aux=numero[j];
                    numero[j]=numero[i];
                    numero[i]=aux;
                }
            }
        }
        return numero;
    }

    String completar(int numero)
    {
        String num = String.valueOf(numero);

        while(num.length()<4)
        {
            num="0"+num;
        }

        return num;
    }
}
