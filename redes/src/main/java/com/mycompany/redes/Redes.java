package com.mycompany.redes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Collections;

public class Redes {

    public static void main(String[] args) {
        Scanner scan= new Scanner(System.in);
        ArrayList<Integer> binarios= new ArrayList<>();
        
        Result validacion= new Result();
        Result resultadoClase= new Result();
        
        boolean error= true;
        boolean response;
        
        do {
            //Pide al usuario ingresar la red a validar
            System.out.println("Ingresa la red");
            String red= scan.next();
            //Valida si la red ingresada es correcta
            response= validacion.valid(red);
            if (response) {
                System.out.println("\tERROR. Red incorrecta");
            }
        } while (response);//
        
        System.out.println("La clase por default es: \t"+ validacion.resultadoClase);
    }
}

//Funcion Red de la IP, Retorna a que red pertenece la direccion IP, incluso si la red esta segmentada
public static String redDeIp(String Ip, String mascara){
        //Separa los bytes 
        String[] bytesMasc = mascara.split("\\.");
        String[] bytesIp = Ip.split("\\.");
        String byte1 = "";
        String red = "";
        String numeroDecimal = "";
        
        for (int i = 0; i <= bytesMasc.length-1; i++) {
            //Masc
            int numeroEntero = Integer.parseInt(bytesMasc[i]);
            String numeroBinario = Integer.toBinaryString(numeroEntero);
            while (numeroBinario.length() != 8) {
                //Se agrega un 0 al inicoi de la cadena para completar los o byts y hacer el AND correctamente
                numeroBinario = 0 +numeroBinario;
            }
            //IP
            int numeroEnteroIp = Integer.parseInt(bytesIp[i]);
            String numeroBinarioIP = Integer.toBinaryString(numeroEnteroIp);
            while (numeroBinarioIP.length() != 8) {
                //Se agrega un 0 al inicoi de la cadena para completar los o byts y hacer el AND correctamente
                numeroBinarioIP = 0 + numeroBinarioIP;
            }
            //AND
            for (int x = 0; x < numeroBinario.length(); x++) {
                char bytMasc = numeroBinario.charAt(x);
                char bytIp = numeroBinarioIP.charAt(x);
                if (bytMasc == '1' && bytIp == '1') {
                    byte1 += "1";
                }else
                    byte1 +="0";
            }
            
            BigInteger numeroBigInteger = new BigInteger(byte1, 2); // El segundo argumento 2 indica base 2 (binario)
            numeroDecimal = numeroBigInteger.toString(10); // El segundo argumento 10 indica base 10 (decimal)
            red += numeroDecimal+".";
            byte1 = "";
        }
        //Se toma el valor del ultimo Byte de la red para sumarlo con el resultado de la funcion nodosYBroadcast ¨para 
        //sacar el resultado del broadcast
        int ultimoByteRed = Integer.parseInt(numeroDecimal);
        //Imprime el numero de IPs disponibles
        System.out.println("Numero de IPs disponibles en la red: " +noNodos(Ip, mascara));
        //Suma el numero de IPs disponibles y el ultimo Byte para sacar el broadcast +1
        System.out.println("BroadCast: "+(ultimoByteRed+noNodos(Ip, mascara)+1));
        //Se muestra si la red esta segmentada con la funcion redSegmentada
        System.out.println(redSegmentada(mascara));
        //Retorna la red a la que pertenece la IP
        return "La red es: "+red.substring(0, red.length()-1);
    }

    //Funcoin para detectar si la red esta segmentada
    public static String redSegmentada(String mascara){
        String[] bytesMasc = mascara.split("\\.");
        String mensaje = "La red no esta segmentada";
        for (int i = 0; i < bytesMasc.length; i++) {
            int numeroEntero = Integer.parseInt(bytesMasc[i]);
            if (numeroEntero != 0 && numeroEntero != 255) {
                mensaje = "La red esta segmentada";
            }
        }
        return mensaje;
    }
    //Funcion para sacar el numero de nodos disponibles en la red
    public static int noNodos(String Ip, String mascara){
        int bits = 0;
        int noIps = 0;
        String[] bites = mascara.split("\\.");
        
        for (int i = 3; i >= 0; i--) {
            if (bites[i].equals("0")) {
                bits += 8;
            }
            else{
                switch (bites[i]) {
                    case "128" -> bits += 7;
                    case "192" -> bits += 6;
                    case "224" -> bits += 5;
                    case "240" -> bits += 4;
                    case "248" -> bits += 3;
                    case "252" -> bits += 2;
                    case "254" -> bits += 1;
                    case "255" -> {
                    }
                    default -> throw new AssertionError();     
                }
            }
        }
        noIps = (int) Math.pow(2, bits) -2;
        return noIps;
    }


//Clase Result Made by DANIELA ---------------------------------------------------------------
class Result {
    // Declara global para usarla en el main
    public String resultadoClase;
    public String idClase (String red){
        return "retorno cadena 1";
    }
    
    // validar si una cada subcadena es valida
    public Boolean valid(String red){
        boolean error= false;
        //Se divide la el parametro red que se pasa a la funcion mediante .
        //Ejemplo = "10.20.160.192" --> [0] = 10, [1] = 20, [2] = 160 y [3] = 192 
        String[] subcadena= red.split("\\.");
        
        // Valida que la red cuente con los 4 bytes 
        if (subcadena.length>4 || subcadena.length<1) {
            System.out.println("error 1");
            //Retorna error si la red no cuenta con los 4 bytes
            error= true;
        }else{
            //For que toma el valor de cada byte 
            for (int i = 0; i <subcadena.length; i++) {
                Integer scad= Integer.parseInt(subcadena[i]);
                //Valida que el numero sea mayo o igual a 0 y meno a 253
                if (scad<=0 || scad>253) {
                    //Retorna error
                    error= true;
                    break;
                }else {
                    if (i==0) {
                        resultadoClase= clase(scad);
                    }
                }
            }
        }
        return error;
    }

    //Valida que clase de red es.
    public String clase(Integer sCad0){
        if (sCad0>=1 && sCad0<=127) {
            return "Red de clase A";
        }else if (sCad0>=128 && sCad0<=191) {
            return "Red de clase B";
        }else if (sCad0>=192 && sCad0<=253) {
            return "Red de clase C";
        }
    }
    
}
