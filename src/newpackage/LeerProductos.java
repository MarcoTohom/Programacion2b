/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Marco
 */
public class LeerProductos {

    String nombre;
    int[] fechaCaducidad = new int[3];
    int numeroLote;
    String paisOrigen;
    int[] fechaEnvasado = new int[3];

    public static void main(String args[]) throws java.io.IOException {
        JsonParser jsonParser = new JsonParser();
        FileReader fileReader = new FileReader("ejemplo.json");
        JsonElement jsonElement = jsonParser.parse(fileReader);
        obtenerObjetos(jsonElement);
    }

    public static void obtenerObjetos(JsonElement pJsonElement) {
        if (pJsonElement.isJsonObject()){
            System.out.print("\n\nObjeto: ");
            JsonObject jsonObject = pJsonElement.getAsJsonObject();
            Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
            Iterator<java.util.Map.Entry<String, JsonElement>> objectIterator = entrySet.iterator();
            while (objectIterator.hasNext()){
                Map.Entry<String, JsonElement> entrada = objectIterator.next();
                String entry = entrada.toString();
                if (pJsonElement.isJsonObject()) {
                    System.out.println("\n\n\tClave: " + entrada.getKey());
                    System.out.print("\tValor: ");
                } else {
                    System.out.println("\n\n\tClave: " + entrada.getKey());
                }
                
                
                obtenerObjetos(entrada.getValue());
            }
        } else if(pJsonElement.isJsonArray()){
            JsonArray array = pJsonElement.getAsJsonArray();
            Iterator<JsonElement> iter = array.iterator();
            while (iter.hasNext()) {
                JsonElement entrada = iter.next();
                obtenerObjetos(entrada);
            }
        } else {
            JsonPrimitive primitivo = pJsonElement.getAsJsonPrimitive();
            System.out.print(primitivo.getAsString());
        }
    }

}
