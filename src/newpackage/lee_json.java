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

public class lee_json {

    public static void main(String args[]) throws java.io.IOException {
        JsonParser jsonParser = new JsonParser();
        FileReader fileReader = new FileReader("datos.json");
        JsonElement jsonElement = jsonParser.parse(fileReader);
        dumpJSONElement(jsonElement);
    }

    public static void dumpJSONElement(JsonElement pJsonElement) {
        if (pJsonElement.isJsonObject()) {
            System.out.println("\nEs objeto");
            JsonObject jsonObject = pJsonElement.getAsJsonObject();
            java.util.Set<java.util.Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
            java.util.Iterator<java.util.Map.Entry<String, JsonElement>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                java.util.Map.Entry<String, JsonElement> entrada = iterator.next();
                System.out.println("\tClave: " + entrada.getKey());
                System.out.println("\tValor:");
                dumpJSONElement(entrada.getValue());
            }

        } else if (pJsonElement.isJsonArray()) {
            JsonArray array = pJsonElement.getAsJsonArray();
            System.out.println("Es array. Numero de elementos: " + array.size());
            java.util.Iterator<JsonElement> iter = array.iterator();
            while (iter.hasNext()) {
                JsonElement entrada = iter.next();
                dumpJSONElement(entrada);
            }
        } else if (pJsonElement.isJsonPrimitive()) {
            System.out.println("Es primitiva");
            JsonPrimitive valor = pJsonElement.getAsJsonPrimitive();
            if (valor.isBoolean()) {
                System.out.println("Es booleano: " + valor.getAsBoolean());
            } else if (valor.isNumber()) {
                System.out.println("Es numero: " + valor.getAsNumber());
            } else if (valor.isString()) {
                System.out.println("Es texto: " + valor.getAsString());
            }
        } else if (pJsonElement.isJsonNull()) {
            System.out.println("Es NULL");
        } else {
            System.out.println("Es otra cosa");
        }
    }
}
