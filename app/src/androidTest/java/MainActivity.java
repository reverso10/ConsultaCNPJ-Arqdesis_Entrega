/**
 * Leandro Reverso
 *
 */

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.usjt.arqdsis.consultacnpj.R;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    EditText editTextCNPJ;
    Callback<ConsultaCNPJ> callbackCNPJ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        configurarCallbackCNPJ();

        editTextCNPJ = (EditText)findViewById(R.id.editTextCNPJ);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new APIClient().getRestService().getConsultaCNPJ(editTextCNPJ.getText().toString(),callbackCNPJ);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void configurarCallbackCNPJ() {

        callbackCNPJ = new Callback<ConsultaCNPJ>() {

            @Override public void success(ConsultaCNPJ consultaCNPJ, Response response) {

                if(response.getStatus()==200) {
                    Log.d("RESPOSTA",consultaCNPJ.toString());
                }else{
                    Toast.makeText(MainActivity.this,"Falha na comunicação !",Toast.LENGTH_LONG).show();
                }

            }

            @Override public void failure(RetrofitError error) {

                Toast.makeText(MainActivity.this,"Erro Servidor!",Toast.LENGTH_LONG).show();

                Log.e("RETROFIT", "Error:"+error.getMessage());
            }
        };
    }

}
