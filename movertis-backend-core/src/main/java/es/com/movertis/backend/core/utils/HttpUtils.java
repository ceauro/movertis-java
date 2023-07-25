package es.com.movertis.backend.core.utils;

import es.com.movertis.backend.core.api.drivers.DriverAPIImpl;
import es.com.movertis.backend.lib.model.units.service.response.SummaryTripDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import java.util.Map;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpUtils {

    private static final Logger logger = LogManager.getLogger(HttpUtils.class);
    private static final String AUTHORIZATION = "authorization";
    private static final MediaType JSON = MediaType.parse("application/json");

    public static String post(String url, String token, String json) {
        OkHttpClient client = new OkHttpClient();
        Headers headers = new Headers.Builder().add(AUTHORIZATION, token).build();
        RequestBody requestBody = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(Objects.requireNonNull(HttpUrl.parse(url)))
                .headers(headers)
                .post(requestBody)
                .build();

        try(Response response = client.newCall(request).execute()){
            if(response.isSuccessful()) {
                return response.body().string();
            }
            logger.error("Ocurrió un error haciendo la petición POST y retornó el código {} Body -> {}", response.code(), response.body().string());
            return Strings.EMPTY;
        } catch (Exception ex){
            logger.error("Error haciendo la petición POST", ex);
            return Strings.EMPTY;
        }
    }

    public static String get(String url, String token, Map<String, Object> parameters){
        OkHttpClient client = new OkHttpClient();
        Headers headers = new Headers.Builder().add(AUTHORIZATION, token).build();
        HttpUrl.Builder httpUrl = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        parameters.forEach((key, value) -> httpUrl.addQueryParameter(key, String.valueOf(value)));
        Request request = new Request.Builder()
                .url(httpUrl.build())
                .headers(headers)
                .get()
                .build();

        try(Response response = client.newCall(request).execute()){
            if(response.isSuccessful()) {
                return response.body().string();
            }
            logger.error("Ocurrió un error haciendo la petición GET y retornó el código {}", response.code());
            return Strings.EMPTY;
        } catch (Exception ex){
            logger.error("Error haciendo la petición GET", ex);
            return Strings.EMPTY;
        }
    }
}
