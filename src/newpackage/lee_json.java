/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.io.FileReader;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class lee_json {

    public static void main(String args[]) throws java.io.IOException {
        JsonParser jsonParser = new JsonParser();
        FileReader fileReader = new FileReader("ejemplo.json");
        JsonElement jsonElement = jsonParser.parse(fileReader);
        dumpJSONElement(jsonElement);
    }

    public static void dumpJSONElement(JsonElement pJsonElement) {
        if (pJsonElement.isJsonObject()) {
            System.out.println("\nEs objeto");
            JsonObject jsonObject = pJsonElement.getAsJsonObject();
            Set<Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
            Iterator<java.util.Map.Entry<String, JsonElement>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Entry<String, JsonElement> entrada = iterator.next();
                System.out.println("\tClave: " + entrada.getKey());
                System.out.println("\tValor:");
                dumpJSONElement(entrada.getValue());
            }

        } else if (pJsonElement.isJsonArray()) {
            JsonArray array = pJsonElement.getAsJsonArray();
            //System.out.println("Es array. Numero de elementos: " + array.size());
            Iterator<JsonElement> iter = array.iterator();
            while (iter.hasNext()) {
                JsonElement entrada = iter.next();
                dumpJSONElement(entrada);
            }
        } else if (pJsonElement.isJsonPrimitive()) {
            //System.out.println("Es primitiva");
            JsonPrimitive valor = pJsonElement.getAsJsonPrimitive();
            if (valor.isBoolean()) {
                System.out.print(/*"Es booleano: " + */valor.getAsBoolean());
            } else if (valor.isNumber()) {
                System.out.print(/*"Es numero: " + */valor.getAsNumber());
            } else if (valor.isString()) {
                System.out.print(/*"Es texto: " + */valor.getAsString());
            }
        } else if (pJsonElement.isJsonNull()) {
            System.out.println("Es NULL");
        } else {
            System.out.println("Es otra cosa");
        }
    }
}
