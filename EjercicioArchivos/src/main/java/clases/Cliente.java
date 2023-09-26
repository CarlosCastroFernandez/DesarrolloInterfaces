package clases;

import java.io.*;

public class Cliente {
    private String nombre;
    public Cliente(String nombre){
       this.nombre=nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String clienteOutput(String nombre){
        File datos=new File("./Carpeta");
        File principal=new File(datos.getParent()+File.separator+datos.getName()+File.separator+"Template.txt");
        File csv=new File(datos.getParent()+File.separator+datos.getName()+File.separator+"datosClientes.csv");
        String numeros="0123456789";
        Integer tamaño=100;
        Integer filas=0;
        String textitoCopy="";
        while(!(this.getNombre().contains(tamaño+""))){
            tamaño++;
        }
        String textito="";
        String[]filaGuardada;
        Integer contadorcito=0;
        try {
            BufferedReader leer2=new BufferedReader(new FileReader(datos.getParent()+File.separator+datos.getName()+File.separator+csv.getName()));
            String textazo= leer2.readLine();
            while(!textazo.contains(tamaño+"")) {
                textazo = leer2.readLine();
            }
                if(textazo.contains(tamaño+"")&&textazo!=null){
                    filaGuardada=textazo.split(",");
                    BufferedReader leer3=new BufferedReader(new FileReader(datos.getParent()+File.separator+datos.getName()+File.separator+principal.getName()));
                    while((textito=leer3.readLine())!=null){
                        contadorcito=0;
                        for(int i=0;i<textito.length();i++){
                            if(numeros.contains(textito.charAt(i)+"")){
                                contadorcito++;
                                Integer numeroG=Integer.parseInt(textito.charAt(i)+"");
                                textitoCopy+=textito.replace("%%"+numeroG+"%%",filaGuardada[numeroG-1])+"\n";


                            }else if(i==textito.length()-1&&contadorcito==0){
                                textitoCopy+=textito+"\n";
                            }

                        }
                    }

                }

            } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }catch(NullPointerException e){
            System.err.println("Este cliente no esta en la lista de datos de clientes");
        }

        return "(Texto que se supone que debe de ir en el template de cliente concreto):\n"+textitoCopy;
    }
}
