package org.example;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        System.out.println("\t//Apartado1//");
        ObjectMapper mapper = seriesTotales();
        System.out.println();
        System.out.println("\t//Apartado2//");
        FirstSerie1995(mapper);
        System.out.println();
        System.out.println("\t//Apartado3//");
        titleArgumentRedPatrulla();
        System.out.println();
        System.out.println("\t//Apartado4//");
        actor();
        System.out.println();
        System.out.println("\t//Apartado5//");
        peliculasByYear();
        System.out.println();
        System.out.println("\t//Apartado6//");
        awardNomine();


    }

    private static void FirstSerie1995(ObjectMapper mapper) {
        HttpClient httpClient1 = HttpClient.newHttpClient();
        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(URI.create("https://moviesminidatabase.p.rapidapi.com/series/id/tt0280249/"))
                .header("X-RapidAPI-Key", "b05468486bmshfcd890ee8c29ed1p1f727ejsn109bb3dfc29e")
                .header("X-RapidAPI-Host", "moviesminidatabase.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> response = httpClient1.newHttpClient().send(request1, HttpResponse.BodyHandlers.ofString());

            JsonNode jsonNode = mapper.readTree(response.body());
            String año = "";

            año = jsonNode.get("results").get("start_year").asText();
            if (año.equals("1995")) {
                System.out.println("La URL de la serie Ejercito Patrulla Roja es : \n"+jsonNode.get("results").get("image_url"));
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static ObjectMapper seriesTotales() {
        ObjectMapper mapper = new ObjectMapper();
        List<Data> dataLista = null;
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://moviesminidatabase.p.rapidapi.com/series/idbyTitle/Dragon%20Ball/"))
                .header("X-RapidAPI-Key", "b05468486bmshfcd890ee8c29ed1p1f727ejsn109bb3dfc29e")
                .header("X-RapidAPI-Host", "moviesminidatabase.p.rapidapi.com")
                .header("Accept", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());


            String texto = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.body());


            ResultadoApi resultadoApi = mapper.readValue(response.body(), ResultadoApi.class);


            dataLista = resultadoApi.getResults();
            System.out.println("Numero de series de Dragon Ball: " + dataLista.size());

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return mapper;
    }

    private static ObjectMapper titleArgumentRedPatrulla() {
        ObjectMapper mapper = new ObjectMapper();
        HttpClient httpClient1 = HttpClient.newHttpClient();
        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(URI.create("https://moviesminidatabase.p.rapidapi.com/series/id/tt0280249/season/3/episode/1/"))
                .header("X-RapidAPI-Key", "b05468486bmshfcd890ee8c29ed1p1f727ejsn109bb3dfc29e")
                .header("X-RapidAPI-Host", "moviesminidatabase.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> response = httpClient1.newHttpClient().send(request1, HttpResponse.BodyHandlers.ofString());

            JsonNode jsonNode = mapper.readTree(response.body());
            String titulo = "";
            String argmento="";

            titulo = jsonNode.get("results").get("title").asText();
            argmento=jsonNode.get("results").get("description").asText();
                System.out.println("Titulo: "+titulo+"\n"+"Argumento: "+argmento);



        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return mapper;
    }
    private static ObjectMapper actor() {
        ObjectMapper mapper = new ObjectMapper();
        HttpClient httpClient1 = HttpClient.newHttpClient();
        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(URI.create("https://moviesminidatabase.p.rapidapi.com/actor/id/nm0154226/"))
                .header("X-RapidAPI-Key", "b05468486bmshfcd890ee8c29ed1p1f727ejsn109bb3dfc29e")
                .header("X-RapidAPI-Host", "moviesminidatabase.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> response = httpClient1.newHttpClient().send(request1, HttpResponse.BodyHandlers.ofString());

            JsonNode jsonNode = mapper.readTree(response.body());
            String titulo = "";
            String argmento="";

            titulo = jsonNode.get("results").get("star_sign").asText();
            argmento=jsonNode.get("results").get("birth_place").asText();
            System.out.println("Signo del zodiaco: "+titulo+"\n"+"Lugar de nacimiento: "+argmento);



        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return mapper;
    }
    private static ObjectMapper peliculasByYear() {
        ObjectMapper mapper = new ObjectMapper();
        HttpClient httpClient1 = HttpClient.newHttpClient();
        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(URI.create("https://moviesminidatabase.p.rapidapi.com/movie/byYear/1982/"))
                .header("X-RapidAPI-Key", "b05468486bmshfcd890ee8c29ed1p1f727ejsn109bb3dfc29e")
                .header("X-RapidAPI-Host", "moviesminidatabase.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> response = httpClient1.newHttpClient().send(request1, HttpResponse.BodyHandlers.ofString());

            JsonNode jsonNode = mapper.readTree(response.body());
            Integer contador=0;
           for(int i=0;i<jsonNode.get("results").size();i++){
               contador++;
           }
            System.out.println("Peliculas Registradas: "+contador);



        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return mapper;
    }
    private static ObjectMapper awardNomine() {
        ObjectMapper mapper = new ObjectMapper();
        HttpClient httpClient1 = HttpClient.newHttpClient();
        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(URI.create("https://moviesminidatabase.p.rapidapi.com/movie/id/tt0084516/awards/"))
                .header("X-RapidAPI-Key", "b05468486bmshfcd890ee8c29ed1p1f727ejsn109bb3dfc29e")
                .header("X-RapidAPI-Host", "moviesminidatabase.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> response = httpClient1.newHttpClient().send(request1, HttpResponse.BodyHandlers.ofString());

            JsonNode jsonNode = mapper.readTree(response.body());
            Integer contador=0;
            String concatenacionNominados="";
            String concatenacionGanados="";


            JsonNode awars=jsonNode.get("results");


            for(int i=0;i<jsonNode.get("results").size();i++){
              String tipo=awars.get(i).get("type").asText();

              if(tipo.equals("Nominee")){
                  concatenacionNominados+=awars.get(i).get("award_name")+" ";
              }else if(tipo.equals("Winner")){
                  concatenacionGanados+=awars.get(i).get("award")+" ";
              }
            }
            System.out.println("Nominados:\n"+concatenacionNominados+"\nGanados:\n"+concatenacionGanados);



        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return mapper;
    }
}
