package App;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Busid
 */
public class Utils {

    private static final String JSON_FILE_STRING = "src/App/BaseDeDatos.json";
    private static final String IMAGES_FOLDER = "src/Images/InterfazGame/Miniaturas/";

    public static String NombreGrado(int indiceGrado) {
        // Parsear el archivo JSON
        try (BufferedReader br = new BufferedReader(new FileReader(JSON_FILE_STRING))) {
            // Obtener la lista de grados
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            JSONObject grado = new JSONObject(sb.toString())
                    .getJSONArray("grados")
                    .getJSONObject(indiceGrado);

            return grado.getString("nombre");
        } catch (IOException | JSONException e) {
            return "Error al leer el JSON";
        }
    }

    public static String TituloGrado(int indiceGrado) {
        // Parsear el archivo JSON
        try (BufferedReader br = new BufferedReader(new FileReader(JSON_FILE_STRING))) {
            // Obtener la lista de grados
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            JSONObject grado = new JSONObject(sb.toString())
                    .getJSONArray("grados")
                    .getJSONObject(indiceGrado)
                    .getJSONArray("juegos")
                    .getJSONObject(indiceGrado);

            return grado.getString("titulo");
        } catch (IOException | JSONException e) {
            return "Error al leer el JSON";
        }
    }

    public static List<String> ImagenesCarrusel(int indiceGrado) {
        List<String> imagenes = new ArrayList<>();

        // Parsear el archivo JSON
        try (BufferedReader br = new BufferedReader(new FileReader(JSON_FILE_STRING))) {
            // Obtener la lista de grados
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            JSONObject grado = new JSONObject(sb.toString())
                    .getJSONArray("grados")
                    .getJSONObject(indiceGrado);

            JSONArray juegos = grado.getJSONArray("juegos");
            for (int i = 0; i < 5; i++) {
                JSONObject juego = juegos.getJSONObject(indiceGrado);
                String nombreImagen = juego.getString("imagen");
                String nombreImagenes = nombreImagen + i;
                String rutaImagen = IMAGES_FOLDER + nombreImagen + "/" + nombreImagenes + ".png"; // Suponiendo que las imágenes son archivos PNG
                imagenes.add(rutaImagen);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return imagenes;
    }
}
