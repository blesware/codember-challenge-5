import java.util.Scanner;

public class Challenge_5 {
    
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        
        String banner = """
        
            ██████╗ ██╗     ███████╗███████╗██╗    ██╗ █████╗ ██████╗ ███████╗
            ██╔══██╗██║     ██╔════╝██╔════╝██║    ██║██╔══██╗██╔══██╗██╔════╝
            ██████╔╝██║     █████╗  ███████╗██║ █╗ ██║███████║██████╔╝█████╗  
            ██╔══██╗██║     ██╔══╝  ╚════██║██║███╗██║██╔══██║██╔══██╗██╔══╝  
            ██████╔╝███████╗███████╗███████║╚███╔███╔╝██║  ██║██║  ██║███████╗
            ╚═════╝ ╚══════╝╚══════╝╚══════╝ ╚══╝╚══╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝

            Desafio semana 5 de Codember v1.0

            (En Windows presiona Ctrl + Z en una nueva línea para terminar de leer)
            (En Linux Ctrl + D en una nueva línea para terminar de leer)

            Ingresa un texto:""";

        System.out.println(banner);

        //Se lee el texto línea por línea hasta que se ingrese Ctrl + Z para finalizar
        StringBuilder texto = new StringBuilder();
        
        while (input.hasNextLine()) {

            texto.append(input.nextLine());
            texto.append("\n"); // Agregar un salto de línea después de cada línea ingresada
        }

        String textKeys = texto.toString().trim();        

        //Dividir el texto por saltos de línea
        String[] lineas = textKeys.split("\n");

        String invalidsUsersFinal = "";                        

        //Recorrer cada linea para encontrar cada dato
        for (int i = 0; i < lineas.length; i++) {            

            int contC = 0;
            int c1 = 0;
            int c2 = 0;
            int c3 = 0;
            int c4 = 0;

            String id = "";
            String user = "";
            String email = "";
            String age = "";
            
            for (int j = 0; j < lineas[i].length(); j++) {

                if (lineas[i].charAt(j) == ',') {

                    contC++;

                    switch (contC) {

                        case 1 ->

                            c1 = j;

                        case 2 ->

                            c2 = j;

                        case 3 ->

                            c3 = j;

                        case 4 ->

                            c4 = j;
                    }
                }                
            }            

            id = encontrarDatos("id", lineas, c1, c1, i);
            user = encontrarDatos("", lineas, c1, c2, i);                    
            email = encontrarDatos("", lineas, c2, c3, i);            
            age = encontrarDatos("", lineas, c3, c4, i);                              

            if (!(esAlfanumerico(id) && esAlfanumerico(user) && emailValido(email) && edadValida(age))) {
                
                invalidsUsersFinal = invalidsUsersFinal + String.valueOf(user.charAt(0));
            }
        }

        System.out.println("");
        System.out.println("\nEl submit es: " + invalidsUsersFinal);
    }

    //Metodo para encontrar los datos de los usuarios
    private static String encontrarDatos(String ref, String[] lineas, int v1, int v2, int i) {

        String dato = "";

        if (ref.equals("id")) {

            if (v1 == 0) {

                dato = "";

            } else if (0 == v1 - 1) {

                dato = lineas[i].substring(0);

            } else {

                dato = lineas[i].substring(0, v1 - 1);
            }

        } else {

            if (v1 + 1 == v2) {

                dato = "";
    
            } else if (v1 + 1 == v2 - 1) {
    
                dato = lineas[i].substring(v1 + 1);
    
            } else {
    
                dato = lineas[i].substring(v1 + 1, v2 - 1);
            }
        }
        
        return dato;
    }

    //Metodo para evaluar si una cadena contiene caracteres que no sean alfanumericos
    private static boolean esAlfanumerico(String cadena) {        

        if (cadena.equals("")) {

            return false;
        }

        for (int i = 0; i < cadena.length(); i++) {

            char x = cadena.charAt(i);

            if (!Character.isLetterOrDigit(x)) {

                return false;
            }
        }

        return true;
    }

    //Metodo para evaluar si un email es valido
    private static boolean emailValido(String email) {
        
        if (email.equals("")) {

            return false;
        }

        int arroba = contarCaracter(email, '@');
        int punto = contarCaracter(email, '.');

        if (arroba != 1 || punto != 1) {

            return false;
        }

        String primerCaracter = "";
        int y = 0;

        for (int i = 0; i < email.length(); i++) {

            char x = email.charAt(i);

            if (primerCaracter.equals("")) {

                if (x == '@') {

                    primerCaracter = "@";
                    y = i;
                    break;
                }

                if (x == '.') {

                    return false;            
                }
            }
        }

        if (y == 0 || email.charAt(email.length() - 1) == '.' || email.charAt(y + 1) == '.') {

            return false;
        } 

        return true;
    }

    //Metodo para saber si la edad es valida
    private static boolean edadValida(String edad) {        

        if (edad.equals("")) {

            return true;

        } else {

            try {
            
                int age = Integer.parseInt(edad);
                
                boolean edadValida = age > 0 ? true : false;

                return edadValida;

            } catch (Exception e) {

                return false;
            }
        }        
    }    

    //Metodo para contar un caracter en especifico de una cadena de texto
    private static int contarCaracter(String cadena, char caracter) {

        int cont = 0;

        for (int i = 0; i < cadena.length(); i++) {

            char x = cadena.charAt(i);

            if (x == caracter) {

                cont++;
            }
        }

        return cont;
    }
}