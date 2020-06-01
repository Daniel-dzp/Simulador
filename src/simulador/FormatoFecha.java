package simulador;

/**
 * @author dzp
 */
public class FormatoFecha {
    
    static String segAHora(int segs)
    {
        String cadena = "";
        int horas, min, seg;
        
        horas = segs/3600;
        segs = segs%3600;
        min = segs/60;
        segs = segs%60;
        seg = segs;
        
        cadena = String.valueOf(horas).length()>1 ? horas+"" : "0"+horas;
        cadena += ":";
        cadena += String.valueOf(min).length()>1 ? min+"" : "0"+min;
        cadena += ":";
        cadena += String.valueOf(seg).length()>1 ? seg+"" : "0"+seg;
        
        return cadena;
    }
    
    static int HoraASeg(String cadena)
    {
        String datos[];
        int segs=0;
        
        datos = cadena.split(":");
        if(datos.length == 3)
        {
            try{
                segs = Integer.parseInt(datos[0])*3600;
                segs += (Integer.parseInt(datos[1])*60);
                segs += Integer.parseInt(datos[2]);
                
                return segs;
            }catch(Exception e){
                return -2;
            }
        }
        else
            return -1;
    }
}
