/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDeNegocio;

/**
 *
 * @author fernando
 */
public class Catastro {
    //Atributos

    VectorNBits V1;// mi primer vector
    VectorNBits Vservicios;// vector de servicios
    String nombre[]; //vector de nombres
    int cant; //cantidad de elementos que tengo

    //constructor
    public Catastro(int cantidad) {
        V1 = new VectorNBits(cantidad, 28);
        Vservicios = new VectorNBits(cantidad, 6);
        nombre = new String[cantidad + 1];
        cant = 0;
    }
    //      99,    40,      50,      500

    public void insertar(String Nombre, int uv, int mz, int lote, int sup,
            int Luz, int Agua, int Pavimnento, int Alcantarillado, int Telefonono, int Gas) {
        cant++;
        int mask = uv; //1100011
        // System.out.println(Integer.toBinaryString(mask));
        mask = mask << 6;//1100011|000000|
        // System.out.println(Integer.toBinaryString(mask));
        //7+6=13
        mask = mask | mz; //1100011|101000|   ////alt+124 |
        // System.out.println(Integer.toBinaryString(mask));
        mask = mask << 6;
        // System.out.println(Integer.toBinaryString(mask));
        //13+6=19
        mask = mask | lote;
        // System.out.println(Integer.toBinaryString(mask));
        int sup2 = sup - 150;
        mask = mask << 9;
        //System.out.println(Integer.toBinaryString(mask));
        //19+9=28
        mask = mask | sup2;
        //System.out.println(Integer.toBinaryString(mask));
        //---------------------------------------------------
        // System.out.println("-----------------------------------");
        int mask2 = Luz;
        //  System.out.println(Integer.toBinaryString(mask2));
        mask2 = mask2 << 1;
        //  System.out.println(Integer.toBinaryString(mask2));
        mask2 = mask2 | Agua;
        //  System.out.println(Integer.toBinaryString(mask2));
        mask2 = mask2 << 1;
        //   System.out.println(Integer.toBinaryString(mask2));
        mask2 = mask2 | Pavimnento;
        //  System.out.println(Integer.toBinaryString(mask2));
        mask2 = mask2 << 1;
        //   System.out.println(Integer.toBinaryString(mask2));
        mask2 = mask2 | Alcantarillado;
        //  System.out.println(Integer.toBinaryString(mask2));
        mask2 = mask2 << 1;
        //   System.out.println(Integer.toBinaryString(mask2));
        mask2 = mask2 | Telefonono;
        // System.out.println(Integer.toBinaryString(mask2));
        mask2 = mask2 << 1;
        //   System.out.println(Integer.toBinaryString(mask2));
        mask2 = mask2 | Gas;
        //  System.out.println(Integer.toBinaryString(mask2));
        V1.insertar(mask, cant);
       // System.out.println(V1.toString());
        Vservicios.insertar(mask2, cant);
        // System.out.println(Vservicios.toString());
        nombre[cant - 1] = Nombre;
        //System.out.println(nombre[0]);

    }

    public int getCantidad(){
        return cant;
    }
    
    ///get
    public String getNombre(int posicion) {
        return nombre[posicion - 1];
    }
    // 1

    public int getuv(int posicion) {//uv 1100011
        int dato = V1.sacar(posicion); //|1100011|101000110010101011110
        //28-7=21
        int mask = (int) (Math.pow(2, 7) - 1);
        // System.out.println(Integer.toBinaryString(mask));        
        dato = dato >>> 21;//00000000000000000|1100011|
        //System.out.println(Integer.toBinaryString(dato));
        dato = dato & mask;
        return dato;
    }

    public int getmz(int posicion) {
        int dato = V1.sacar(posicion);//1100011|101000|110010101011110
        //21-6=15
        int mask = (int) (Math.pow(2, 6) - 1);
        dato = dato >>> 15;//00000000001100011|101000|
       // System.out.println(Integer.toBinaryString(dato));
        dato = dato & mask;//00000000000000000|101000|
        //System.out.println(Integer.toBinaryString(dato));
        return dato;
    }

    public int getlote(int posicion) {
        int dato = V1.sacar(posicion);//1100011101000|110010|101011110
        //15-6=9;
        int mask = (int) (Math.pow(2, 6) - 1);
        dato = dato >>> 9;
        dato = dato & mask;
        return dato;
    }

    public int getsup(int posicion) {//1100011101000110010|101011110|
        int dato = V1.sacar(posicion);
        //9-9=0
        int mask = (int) (Math.pow(2, 9) - 1);
        //dato=dato>>>0;
        dato = dato & mask;
        return dato + 150;

    }

    ///----------------servicios basicos
    public String getLuz(int posicion) {//111111
        int dato = Vservicios.sacar(posicion);
        //6-1=5;
        int mask = (int) (Math.pow(2, 1) - 1);
        dato = dato >>> 5;
        dato = dato & mask;

        if (dato == 1) {
            return "si";
        } else {
            return "no";
        }

    }

    public String getAgua(int posicion) {//111111
        int dato = Vservicios.sacar(posicion);
        //5-1=4;
        int mask = (int) (Math.pow(2, 1) - 1);
        dato = dato >>> 4;
        dato = dato & mask;

        if (dato == 1) {
            return "si";
        } else {
            return "no";
        }

    }

    public String getPavimento(int posicion) {//111111
        int dato = Vservicios.sacar(posicion);
        //4-1=3;
        int mask = (int) (Math.pow(2, 1) - 1);
        dato = dato >>> 3;
        dato = dato & mask;

        if (dato == 1) {
            return "si";
        } else {
            return "no";
        }

    }

    public String getAlcantarillado(int posicion) {//111111
        int dato = Vservicios.sacar(posicion);
        //3-1=2;
        int mask = (int) (Math.pow(2, 1) - 1);
        dato = dato >>> 2;
        dato = dato & mask;

        if (dato == 1) {
            return "si";
        } else {
            return "no";
        }

    }

    public String getTelefonono(int posicion) {//111111
        int dato = Vservicios.sacar(posicion);
        //2-1=1;
        int mask = (int) (Math.pow(2, 1) - 1);
        dato = dato >>> 1;
        dato = dato & mask;

        if (dato == 1) {
            return "si";
        } else {
            return "no";
        }

    }

    public String getGas(int posicion) {//111111
        int dato = Vservicios.sacar(posicion);
        //1-1=0;
        int mask = (int) (Math.pow(2, 1) - 1);
        // dato=dato>>>0;
        dato = dato & mask;

        if (dato == 1) {
            return "si";
        } else {
            return "no";
        }

    }
    
    
    
    public String Mostrar(int posicion){
        String salida="Catastro \n";
        salida=salida+"Nro:"+posicion+"\n";
        salida=salida+"Nombre:"+getNombre(posicion)+"\n";
        salida=salida+"UV:"+getuv(posicion)+"\n";
        salida=salida+"MZ:"+getmz(posicion)+"\n";
        salida=salida+"Lote:"+getlote(posicion)+"\n";
        salida=salida+"Sup:"+getsup(posicion)+"\n";
        salida=salida+"Servicios Basicos"+"\n";
        salida=salida+"Luz:"+getLuz(posicion)+"\n";
        salida=salida+"Agua:"+getAgua(posicion)+"\n";
        salida=salida+"Pavimnento:"+getPavimento(posicion)+"\n";
        salida=salida+"Alcantarillado:"+getAlcantarillado(posicion)+"\n";
        salida=salida+"Telefonono:"+getTelefonono(posicion)+"\n";
        salida=salida+"Gas:"+getGas(posicion)+"\n";
        
        return salida;
    }
    

    public static void main(String[] args) {
        Catastro A = new Catastro(10);
        A.insertar("fernando", 99, 40, 50, 500, 1, 1, 1, 1, 1, 1);
        System.out.println(A.Mostrar(1));
    }

}
