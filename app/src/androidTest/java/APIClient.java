

/**
 * Leandro Reverso
 *
 */


import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.GET;
import retrofit.http.Path;

public class APIClient {

    private static RestAdapter REST_ADAPTER;


    private static void createAdapterIfNeeded() {

        if (REST_ADAPTER == null) {
            REST_ADAPTER = new RestAdapter.Builder()
                    .setEndpoint("https://www.receitaws.com.br")
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setClient(new OkClient())
                    .build();
        }
    }

    public APIClient() {
        createAdapterIfNeeded();
    }

    public RestServices getRestService() {
        return REST_ADAPTER.create(RestServices.class);
    }


    public interface RestServices {

        @GET("/v1/cnpj/{cnpj}")
        void getConsultaCNPJ(@Path("cnpj") String cnpj, Callback<ConsultaCNPJ> callbackCliente);

    }


}