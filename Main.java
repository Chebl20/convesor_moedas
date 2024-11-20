import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int escolha = 10;
        while (escolha != 0) {
            System.out.println("""
                    Escolha o tipo de conversão que deseja fazer:
                    1. BRL to USD
                    2. BRL to EUR
                    3. BRL to GBP
                    4. BRL to JPY
                    5. USD to BRL
                    6. EUR to BRL
                    7. GBP to BRL
                    8. JPY to BRL
                    0. Sair
                    """);

            // Lendo a escolha do usuário
            escolha = scanner.nextInt();

            // URL da API para obter as taxas de câmbio
            String urlString = "https://v6.exchangerate-api.com/v6/5878567294355431051e1a6f/latest/BRL";

            try {
                // Criando a URL e a conexão
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                // Verificando o código de resposta
                int responseCode = connection.getResponseCode();

                if (responseCode == 200) {
                    // Lendo a resposta da API
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    // Convertendo a resposta para JsonObject usando Gson
                    Gson gson = new Gson();
                    JsonObject myResponse = gson.fromJson(response.toString(), JsonObject.class);
                    JsonObject conversionRates = myResponse.getAsJsonObject("conversion_rates");

                    // Realizando a conversão com base na escolha do usuário
                    switch (escolha) {
                        case 1:
                            System.out.println("Digite o valor em BRL para conversão para USD:");
                            float valorBRL = scanner.nextFloat();
                            float usd = valorBRL * conversionRates.get("USD").getAsFloat();
                            System.out.println("R$ " + valorBRL + " equivalem a US$ " + usd);
                            break;
                        case 2:
                            System.out.println("Digite o valor em BRL para conversão para EUR:");
                            valorBRL = scanner.nextFloat();
                            float eur = valorBRL * conversionRates.get("EUR").getAsFloat();
                            System.out.println("R$ " + valorBRL + " equivalem a € " + eur);
                            break;
                        case 3:
                            System.out.println("Digite o valor em BRL para conversão para GBP:");
                            valorBRL = scanner.nextFloat();
                            float gbp = valorBRL * conversionRates.get("GBP").getAsFloat();
                            System.out.println("R$ " + valorBRL + " equivalem a £ " + gbp);
                            break;
                        case 4:
                            System.out.println("Digite o valor em BRL para conversão para JPY:");
                            valorBRL = scanner.nextFloat();
                            float jpy = valorBRL * conversionRates.get("JPY").getAsFloat();
                            System.out.println("R$ " + valorBRL + " equivalem a ¥ " + jpy);
                            break;
                        case 5:
                            System.out.println("Digite o valor em USD para conversão para BRL:");
                            float valorUSD = scanner.nextFloat();
                            float brlFromUSD = valorUSD / conversionRates.get("USD").getAsFloat();
                            System.out.println("US$ " + valorUSD + " equivalem a R$ " + brlFromUSD);
                            break;
                        case 6:
                            System.out.println("Digite o valor em EUR para conversão para BRL:");
                            float valorEUR = scanner.nextFloat();
                            float brlFromEUR = valorEUR / conversionRates.get("EUR").getAsFloat();
                            System.out.println("€ " + valorEUR + " equivalem a R$ " + brlFromEUR);
                            break;
                        case 7:
                            System.out.println("Digite o valor em GBP para conversão para BRL:");
                            float valorGBP = scanner.nextFloat();
                            float brlFromGBP = valorGBP / conversionRates.get("GBP").getAsFloat();
                            System.out.println("£ " + valorGBP + " equivalem a R$ " + brlFromGBP);
                            break;
                        case 8:
                            System.out.println("Digite o valor em JPY para conversão para BRL:");
                            float valorJPY = scanner.nextFloat();
                            float brlFromJPY = valorJPY / conversionRates.get("JPY").getAsFloat();
                            System.out.println("¥ " + valorJPY + " equivalem a R$ " + brlFromJPY);
                            break;
                        case 0:
                            System.out.println("Saindo do programa.");
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                            break;
                    }
                } else {
                    System.out.println("Erro ao conectar à API de câmbio. Código de resposta: " + responseCode);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        scanner.close();
    }
}
