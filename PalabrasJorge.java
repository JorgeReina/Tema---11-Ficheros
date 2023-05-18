import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * @author Jorge Reina 
 * 
 */

public class PalabrasJorge{

    static final String Celeste = "\033[0;36m";
    static final String RESET = "\033[0m";
    public static void main (String [] args) {
        if(args.length != 2){
            System.out.println("Argumentos no validos");
            System.exit(0);
        }

        String nombreArchivo = args[0];
        ArrayList<String> texto = leerArchivo(nombreArchivo);
        
        String[] opciones = Opciones(args);
        String palabraBuscada = "";

        for( int i = 0; i < opciones.length; i++){
            switch(opciones[i]){
                case "-a":
                    contarPalabrasOrdenAparicion(texto);
                break;
                case "-A":
                    contarPalabrasOrdenAlfabeticam(texto);
                break;
                case "-R":
                    desordenaTextoAleator(texto);
                break;
                case "-m":
                    minuscula(texto);
                break;
                case "-M":
                    mayuscula(texto);
                break;
                
            }
        }
    }

    //Funcion que lee las opciones introducidas por teclado
    public static String[] Opciones(String[] args){
        return args;
    }

    //Funcion que lee el archivo
    private static ArrayList<String> leerArchivo(String nombreArchivo) {
        ArrayList<String> textoLeido = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(nombreArchivo)); //Lee archivo 
            String linea = br.readLine(); //Lo lee linea a linea
            while(linea != null){ //Mientras linea no sea null.
                String[] palabras = linea.split(" "); //Separa(con espacios) cada linea en palabras y las guarda en el array palabras.
                for(String palabra: palabras) { //Pasamos las palabras al array list antes creado (textoLeido)
                    textoLeido.add(palabra);
                    textoLeido.add(" "); //Separo las palabras con un espacio.
                }
                textoLeido.add("\n"); //Añado a la ultima posicion de la linea un salto de linea
                linea = br.readLine(); //Paso a la siguiente linea
                
            }
            br.close(); //Cerramos archivo
        } catch(IOException e){
            System.out.println("ERROR: "+ e.getMessage());
        }
        return textoLeido;
    }

    

    //Funcion que cuenta las palabras repetidas
    private static int contarPalabras (ArrayList<String> texto, String palabraBuscada){
        int contador = 0; //Variable para almacenar la repeticion de la palabra
        System.out.println(palabraBuscada);
        for(String palabra : texto){ //Bucle que va linea a linea buscando la palabra e incrementando el contador
            if(palabra.equalsIgnoreCase(palabraBuscada)){ //Si la palabra introducida es igual a la palabra del texto, aumenta contador
                contador++;
            }
        }
        System.out.print(contador);
        System.out.println();
        return contador;
    }
    //Funcion que cuenta cada palabra y las ordena por orden de aparicion
    public static void contarPalabrasOrdenAparicion(ArrayList<String> texto) {
        ArrayList<String> palabras = new ArrayList<String>();
        ArrayList<Integer> cantidad = new ArrayList<Integer>();
        for (int i = 0; i < texto.size(); i++) {
          if (!palabras.contains(texto.get(i).toLowerCase())) {
            cantidad.add(contarPalabras(texto, texto.get(i).toLowerCase()));
            palabras.add(texto.get(i).toLowerCase());
          }
        }
        
    }

    //  Cuenta cada palabra y las ordena por orden alfabetico
    public static void contarPalabrasOrdenAlfabeticam(ArrayList<String> texto) {
        ArrayList<String> palabras = new ArrayList<String>();
        Collections.sort(texto);
        for (int i = 0; i < texto.size(); i++) {
          if (!palabras.contains(texto.get(i).toLowerCase())) {
            System.out.println("La palabra " + texto.get(i) + " aparece " + contarPalabras(texto, texto.get(i).toLowerCase()) + " veces.");
            palabras.add(texto.get(i).toLowerCase());
          }
        }
    }

    //  Devuelve la posicion de cada aparicion de la palabra que queremos buscar
    public static void posicionPalabra(ArrayList<String> texto, String palabra) {
        System.out.println("Las posiciones en las que aparece " + palabra + " son:");
        for (int i = 0; i < texto.size(); i++) {
            if (palabra.toLowerCase().equals(texto.get(i).toLowerCase())) {
            System.out.print(i + ", ");
            }
        }
        System.out.println("");
    }

    //  Desordenar
    public static ArrayList<String> desordenaTextoAleator(ArrayList<String> texto) {
        Collections.shuffle(texto);
        return texto;
    }

    //  Pasar a minuscula el texto
    public static ArrayList<String> minuscula(ArrayList<String> texto) {
        ArrayList<String> textoMin = new ArrayList<String>();
        for (int i = 0; i < texto.size(); i++) {
            textoMin.add(texto.get(i).toLowerCase());
        }
        return textoMin;
    }

    //  Pasa a mayuscula el texto
    public static ArrayList<String> mayuscula(ArrayList<String> texto){
        ArrayList<String> textoMay = new ArrayList<String>();
        for (int i = 0; i < texto.size(); i++) {
          textoMay.add(texto.get(i).toUpperCase());
        }
        return textoMay;
    }

}