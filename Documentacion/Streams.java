import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Streams implements Serializable {
    private String atributoString;
    private int atributoInt;

    public Streams(String atributoString, int atributoInt) {
        this.atributoString = atributoString;
        this.atributoInt = atributoInt;
    }

    @Override
    public String toString() {
        return "Streams [atributoInt=" + atributoInt + ", atributoString=" + atributoString + "]";
    }

    public static void main(String[] args) {
        
        try { // Cambiar y/o quitar a los archivos de cada persona
            File file1 = new File("C:/Users/?/Desktop/Prueba.txt");
            File file2 = new File("C:/Users/?/Desktop/Prueba2.txt");
            File imagen1 = new File("C:/Users/?/Desktop/Prueba.jpg");
            File imagen2 = new File("C:/Users/?/Desktop/Prueba2.jpg");

// =====================================================================================================================================================================
// ======== SE DEBE DESCOMENTAR LA PRUEBA QUE SE QUIERA REALIZAR =======================================================================================================
// =====================================================================================================================================================================
            // STREAMS DE TEXTO
            /*
            leerCaracter(file1);
            String texto = "Nuevo texto";
            escribirCaracter(file2, texto);
            añadirCaracter(file2, texto);
            */
// =====================================================================================================================================================================
            // STREAMS DE TEXTO MEDIANTE BUFFERS
            /*
            String texto = "Nuevo texto";
            leerBufferTexto(file1);
            escribirBufferTexto(file2, texto);
            añadirBufferTexto(file2, texto);
            */
// =====================================================================================================================================================================
            // STREAMS DE BYTES
            /*
            ArrayList listaDeBytes = leerByte(file1);
            escribirByte(file2, listaDeBytes);
            añadirByte(file2, listaDeBytes);
            */
// =====================================================================================================================================================================
            // STREAMS DE BYTES MEDIANTE BUFFERS
            /*
            ArrayList listaDeBytes = leerBufferByte(file1);
            escribirBufferByte(file2, listaDeBytes);
            añadirBufferByte(file2, listaDeBytes);
            */
// =====================================================================================================================================================================
            // STREAMS DE OBJETOS
            /*
            ArrayList<Streams> streams = new ArrayList<>();
            streams.add(new Streams("Marcos", 19));
            streams.add(new Streams("Maria", 20));
            streams.add(new Streams("Pepe", 21));

            escribirObjeto(file2, streams);

            ArrayList<Streams> listaObjetos = leerObjeto(file2);

            for (int i = 0; i < listaObjetos.size(); i++) {
                System.out.println(listaObjetos.get(i));
            }
            */
// =====================================================================================================================================================================
            // STREAMS DE OBJETOS MEDIANTE BUFFERS
            /*
            ArrayList<Streams> streams = new ArrayList<>();
            streams.add(new Streams("Marcos", 19));
            streams.add(new Streams("Maria", 20));
            streams.add(new Streams("Pepe", 21));

            escribirBufferObjeto(file2, streams);

            ArrayList<Streams> listaObjetos = leerBufferObjeto(file2);

            for (int i = 0; i < listaObjetos.size(); i++) {
                System.out.println(listaObjetos.get(i));
            }
            */
// =====================================================================================================================================================================

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


// ========================================================================================================================================================
// CARACTERES
// ========================================================================================================================================================

    // LEER CARACTERES
    public static void leerCaracter(File file) throws Exception {
        // Usamos la clase FileReader
        FileReader fileReader = new FileReader(file);

        int codigoUnicode = 0; // En esta variable guardaremos el valor de cada carácter
        while (codigoUnicode != -1) { // Cuando se acaben los carácteres la variable se establecerá en "-1"

            // Usamos el método "read()" que asigna a la variable el próximo carácter
            codigoUnicode = fileReader.read();
            if (codigoUnicode != -1) { // De esta forma no se imprimirá el "-1" como carácter

                // Para pasar los "int" de Unicode a carácteres se hace un "casting" a "char"
                System.out.print((char) codigoUnicode);
            }
        }
        fileReader.close(); // Cerramos el Stream
    }


    // ESCRIBIR CARACTERES | ESTE MÉTODO SOBREESCRIVIRÁ EL ARCHIVO ENTERO
    public static void escribirCaracter(File file, String texto) throws Exception {
        FileWriter fileWriter = new FileWriter(file);

        // El texto se tendrá que imprimir carácter a carácter, por ello recorremos la longitud de "texto"
        for (int i = 0; i < texto.length(); i++) {
            // Se tendrá que escribir carácter a carácter (IMPORTANTE usar el "charAt()" y no meter un String directo)
            fileWriter.write(texto.charAt(i));
        }
        fileWriter.close();
    }


    // AÑADIR CARACTERES | ESTE MÉTODO AÑADIRÁ EL NUEVO TEXTO DESPUÉS DEL TEXTO QUE YA EXISTA EN EL ARCHIVO
    public static void añadirCaracter(File file, String texto) throws Exception {
        FileWriter fileWriter = new FileWriter(file, true); // El "true" es para indicarle que vamos a *Añadir* texto

        // El texto se tendrá que imprimir carácter a carácter, por ello recorremos la longitud de "texto"
        for (int i = 0; i < texto.length(); i++) {
            // Se tendrá que escribir carácter a carácter (IMPORTANTE usar el "charAt()" y no meter un String directo)
            fileWriter.write(texto.charAt(i));
        }
        fileWriter.close();
    }





// ========================================================================================================================================================
// BUFFERS DE CARÁCTERES
// ========================================================================================================================================================

    // LEER TEXTO MEDIANTE BUFFERS
    public static void leerBufferTexto(File file) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String linea="";
        // Cuando el método "readLine()" no encuentre más líneas, la variable se establecerá en "null" 
        while (linea != null) {
            // Método para asignar la nueva línea a la variable
            linea = bufferedReader.readLine();

            // Con este "if" consegimos que no imprima el valor "null" en el último ciclo
            if (linea !=  null) {
                System.out.println(linea);
            }
        }
        bufferedReader.close();
    }


    // ESCRIBIR TEXTO MEDIANTE BUFFERS | ESTE MÉTODO SOBREESCRIVIRÁ EL ARCHIVO ENTERO
    public static void escribirBufferTexto(File file, String texto) throws Exception {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

        /* Bastante simple de aplicar, lo importante es realizar los saltos de línea
        correctamente mediante "\n" para que la clase BufferedWriter lo entienda */
        bufferedWriter.write(texto);

        bufferedWriter.close();
    }


    // AÑADIR TEXTO MEDIANTE BUFFERS | ESTE MÉTODO AÑADIRÁ EL NUEVO TEXTO DESPUÉS DEL TEXTO QUE YA EXISTA EN EL ARCHIVO
    public static void añadirBufferTexto(File file, String texto) throws Exception {
        // Lo mismo que el de arriba aplicando el "true" en el segundo parámetro del constructor "FileWriter()"
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));

        bufferedWriter.write(texto);

        bufferedWriter.close();
    }





// ========================================================================================================================================================
// BYTES
// ========================================================================================================================================================

    // LEER BYTES
    public static ArrayList leerByte(File file) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(file);
        // En este método devuelvo un "ArrayList" donde guardo los BYTES 
        ArrayList datosEntrada = new ArrayList();

        // Esta variable solo es necesaria en caso de que se quiera utilizar el print de abajo
        int contador = 0;

        // Mientras que no encontremos el final del archivo
        boolean finalArchivo = false;
        while (!finalArchivo) {

            // Utilizamos el método "read()" que devuelve el próximo byte
            int byteLectura = fileInputStream.read();

            // Cuando el método "read()" devuelva "-1" habrá acabado el archivo
            if (byteLectura != -1) {
                datosEntrada.add(byteLectura); // Se añade el byte al ArrayList

                // En caso de querer imprimir el texto en este mismo método se puede usar el siguiente print:
                //System.out.print((char)(int)datosEntrada.get(contador));

                contador++;
            } else {
                finalArchivo = true;
            }
        }
        fileInputStream.close();
        return datosEntrada;
    }


    // ESCRIBIR BYTES | ESTE MÉTODO SOBREESCRIVIRÁ EL ARCHIVO ENTERO
    // Para este ejemplo le pasaré por parámetro el ArrayList de Bytes que obtenemos en el método de arriba
    public static void escribirByte(File file, ArrayList datosSalida) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        // Recorremos todas las posiciónes (bytes) del ArrayList
        for (int index = 0; index < datosSalida.size(); index++) {
            /* El método "get()" devuelve un tipo "Object".
            Para poder imprimirlo como texto debemos aplicar un Casting a "int" (Para transformarlo a un carácter Unicode),
            y después utilizar un Casting a "char" (Para transformar el carácter Unicode a "char") */
            fileOutputStream.write((char)(int)datosSalida.get(index));
        }

        fileOutputStream.close();
    }


    // AÑADIR BYTES | ESTE MÉTODO AÑADIRÁ LOS NUEVOS BYTES DESPUÉS DEL TEXTO QUE YA EXISTA EN EL ARCHIVO
    public static void añadirByte(File file, ArrayList datosSalida) throws Exception {
        // Lo mismo que el anterior método, solo le añadimos el segundo parámetro "true" al constructor
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);

        for (int index = 0; index < datosSalida.size(); index++) {
            fileOutputStream.write((char)(int)datosSalida.get(index));
        }

        fileOutputStream.close();
    }





// ========================================================================================================================================================
// BUFFERS DE BYTES
// ========================================================================================================================================================

    // LEER BYTES MEDIANTE BUFFERS
    public static ArrayList leerBufferByte(File file) throws Exception {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        // En este método devuelvo un "ArrayList" donde guardo los BYTES
        ArrayList datosEntrada = new ArrayList();

        // Esta variable solo es necesaria en caso de que se quiera utilizar el print de abajo
        int contador = 0;

        // Mientras que no encontremos el final del archivo
        boolean finalArchivo = false;
        while (!finalArchivo) {

            // Utilizamos el método "read()" que devuelve el próximo byte
            int byteLectura = bufferedInputStream.read();

            // Cuando el método "read()" devuelva "-1" habrá acabado el archivo
            if (byteLectura != -1) {
                datosEntrada.add(byteLectura); // Se añade el byte al ArrayList

                // En caso de querer imprimir el texto en este mismo método se puede usar el siguiente print:
                //System.out.print((char)(int)datosEntrada.get(contador));

                contador++;
            } else {
                finalArchivo = true;
            }
        }
        bufferedInputStream.close();
        return datosEntrada;
    }


    // ESCRIBIR BYTES MEDIANTE BUFFERS | ESTE MÉTODO SOBREESCRIVIRÁ EL ARCHIVO ENTERO
    // Para este ejemplo le pasaré por parámetro el ArrayList de Bytes que obtenemos en el método de arriba
    public static void escribirBufferByte(File file, ArrayList datosSalida) throws Exception {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));

        // Recorremos todas las posiciónes (bytes) del ArrayList
        for (int index = 0; index < datosSalida.size(); index++) {
            /* El método "get()" devuelve un tipo "Object".
            Para poder imprimirlo como texto debemos aplicar un Casting a "int" (Para transformarlo a un carácter Unicode),
            y después utilizar un Casting a "char" (Para transformar el carácter Unicode a "char") */
            bufferedOutputStream.write((char)(int)datosSalida.get(index));
        }
        bufferedOutputStream.close();
    }


    // AÑADIR BYTES MEDIANTE BUFFERS | ESTE MÉTODO AÑADIRÁ LOS NUEVOS BYTES DESPUÉS DEL YA EXISTENTE EN EL ARCHIVO
    public static void añadirBufferByte(File file, ArrayList datosSalida) throws Exception {
        // Lo mismo que el anterior método, solo le añadimos el segundo parámetro "true" al constructor
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file, true));

        // Recorremos todas las posiciónes (bytes) del ArrayList
        for (int index = 0; index < datosSalida.size(); index++) {
            /* El método "get()" devuelve un tipo "Object".
            Para poder imprimirlo como texto debemos aplicar un Casting a "int" (Para transformarlo a un carácter Unicode),
            y después utilizar un Casting a "char" (Para transformar el carácter Unicode a "char") */
            bufferedOutputStream.write((char)(int)datosSalida.get(index));
        }
        bufferedOutputStream.close();
    }





// ========================================================================================================================================================
// OBJETOS
// ========================================================================================================================================================
    // LOS 2 PRIMEROS EJEMPLOS SE PODRÍAN CAMBIAR A LA CLASE EN CUESTIÓN

    // LEER OBJETOS
    public static ArrayList<Streams> leerObjeto(File file) throws Exception {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
        // Es necesario realizar un Casting ya que el método "readObject()" devuelve un tipo "Object"
        ArrayList<Streams> respuesta = (ArrayList<Streams>) objectInputStream.readObject();
        objectInputStream.close();
        return respuesta;
    }


    // ESCRIBIR BYTES
    public static void escribirObjeto(File file, ArrayList<Streams> streams) throws Exception {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeObject(streams);
        objectOutputStream.close();
    }






// ========================================================================================================================================================
// BUFFERS DE OBJETOS
// ========================================================================================================================================================

    public static ArrayList<Streams> leerBufferObjeto(File file) throws Exception {
        ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
        // Es necesario realizar un Casting ya que el método "readObject()" devuelve un tipo "Object"
        ArrayList<Streams> respuesta = (ArrayList<Streams>) objectInputStream.readObject();
        objectInputStream.close();
        return respuesta;
    }


    public static void escribirBufferObjeto(File file, ArrayList<Streams> streams) throws Exception {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        objectOutputStream.writeObject(streams);
        objectOutputStream.close();
    }



}