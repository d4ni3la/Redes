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
        String[] bitesMasc = mascara.split("\\.");
        String[] bitesIp = Ip.split("\\.");
        String bite = "";
        String red = "";
        
        for (int i = 0; i <= bitesMasc.length-1; i++) {
            //Masc
            int numeroEntero = Integer.parseInt(bitesMasc[i]);
            String numeroBinario = Integer.toBinaryString(numeroEntero);
            System.out.println("Marcara: "+numeroBinario);
            while (numeroBinario.length() != 8) {
                //Se agrega un 0 al inicoi de la cadena para completar los o byts y hacer el AND correctamente
                numeroBinario = 0 +numeroBinario;
            }
            System.out.println("Mascara: "+numeroBinario);
            //IP
            int numeroEnteroIp = Integer.parseInt(bitesIp[i]);
            String numeroBinarioIP = Integer.toBinaryString(numeroEnteroIp);
            System.out.println("IP: "+numeroBinarioIP);
            while (numeroBinarioIP.length() != 8) {
                //Se agrega un 0 al inicoi de la cadena para completar los o byts y hacer el AND correctamente
                numeroBinarioIP = 0 + numeroBinarioIP;
            }
            System.out.println("IP: "+numeroBinarioIP);
            //AND
            for (int x = 0; x < numeroBinario.length(); x++) {
                char bitMasc = numeroBinario.charAt(x);
                char bitIp = numeroBinarioIP.charAt(x);
                if (bitMasc == '1' && bitIp == '1') {
                    bite += "1";
                }else
                    bite +="0";
            }
            
            BigInteger numeroBigInteger = new BigInteger(bite, 2); // El segundo argumento 2 indica base 2 (binario)
            String numeroDecimal = numeroBigInteger.toString(10); // El segundo argumento 10 indica base 10 (decimal)
            red += numeroDecimal+".";
            bite = "";
        }
        return "La red es: "+red.substring(0, red.length()-1);
    }
    //Funcion nodos, retorna el numero de nodos que hay en la red
    public static void noNodosYBroadcast(String Ip, String mascara){
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
        System.out.println(bits);
        noIps = (int) Math.pow(2, bits) -2;
        System.out.println("Numero de nodos disponibles = "+noIps);
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
