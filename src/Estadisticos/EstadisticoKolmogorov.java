
package Estadisticos;

/**
 * @author dzp
 */
public class EstadisticoKolmogorov {
                            // 10%    5%     1%
    double tabla[][] = {{  1, 0.950, 0.975, 0.995},
                        {  2, 0.776, 0.842, 0.929},
                        {  3, 0.642, 0.708, 0.829},
                        {  4, 0.564, 0.624, 0.734},
                        {  5, 0.510, 0.563, 0.669},
                        {  6, 0.470, 0.521, 0.618},
                        {  7, 0.438, 0.486, 0.577},
                        {  8, 0.411, 0.457, 0.543},
                        {  9, 0.388, 0.432, 0.514},
                        { 10, 0.368, 0.409, 0.486},
                        { 11, 0.352, 0.391, 0.468},
                        { 12, 0.338, 0.375, 0.450},
                        { 13, 0.352, 0.361, 0.433},
                        { 14, 0.314, 0.349, 0.418},
                        
                        { 15, 0.304, 0.338, 0.404},
                        { 16, 0.295, 0.328, 0.392},
                        { 17, 0.286, 0.318, 0.381},
                        { 18, 0.278, 0.309, 0.371},
                        { 19, 0.272, 0.301, 0.363},
                        { 20, 0.264, 0.294, 0.352},
                        { 25, 0.240, 0.264, 0.317},
                        { 30, 0.220, 0.242, 0.290},
                        { 35, 0.210, 0.230, 0.270},
                        { 40,     0, 0.210, 0.252},
                        { 50,     0, 0.188, 0.226},
                        { 60,     0, 0.172, 0.207},
                        { 70,     0, 0.160, 0.192},
                        { 80,     0, 0.150, 0.180},
                        { 90,     0,     0, 0.141},
                        {100,     0, 0.134,     0}};
    
    public double estadistico(int alfa, int n){
        if(n>100)
        {
            switch(alfa)
            {
                case 10:return 1.22/Math.sqrt(n);
                case 5:return 1.36/Math.sqrt(n);
                case 1:return 1.63/Math.sqrt(n);
            }
        }
        else
        {
            for(int i=0;i<tabla.length;i++)
                if((int)tabla[i][0] == n)
                {
                    switch(alfa)
                    {
                        case 10: return tabla[i][1];
                        case 5: return tabla[i][2];
                        case 1: return tabla[i][3];
                    }
                }
        }
        return 0;
    }
    
    public String getTabla()
    {
        String salida = "";
        
        salida += String.format(" +--------------------------------+\n");
        salida += String.format(" | %3s | %6s | %6s | %6s |\n", "N","10%","5%","1%");
        salida += String.format(" +--------------------------------+\n");
        for(int i=0;i<tabla.length;i++)
        {
            salida += String.format(" | %3.0f | ", tabla[i][0]);
            salida += String.format("%1.4f | ", tabla[i][1]);
            salida += String.format("%1.4f | ", tabla[i][2]);
            salida += String.format("%1.4f | \n", tabla[i][3]);
        }
        salida += String.format(" +--------------------------------+\n\n");
        
        salida += " Formulas para N<100:\n";
        salida += "  10%  =>  1.22/√N\n";
        salida += "   5%  =>  1.36/√N\n";
        salida += "   1%  =>  1.63/√N\n\n";
        
        return salida;
    }
}
