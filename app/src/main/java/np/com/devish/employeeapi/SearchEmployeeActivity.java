package np.com.devish.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import np.com.devish.employeeapi.API.EmployeeAPI;
import np.com.devish.employeeapi.model.Employee;
import np.com.devish.employeeapi.url.URL;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchEmployeeActivity extends AppCompatActivity {

    EditText etEmployeeId;
    Button btnSearchEmployeeId;
    TextView tvSearchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_employee);

        etEmployeeId = findViewById(R.id.etEmployeeId);
        btnSearchEmployeeId = findViewById(R.id.btnEmployeeId);
        tvSearchResult = findViewById(R.id.tvSearchOutput);

        btnSearchEmployeeId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // Validation
            if(TextUtils.isEmpty(etEmployeeId.getText())){
                etEmployeeId.setError("Please Enter employee id");
                return;
            }
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL.base_url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
                Call<Employee>  listCall = employeeAPI.getEmployeeByID(Integer.parseInt(etEmployeeId.getText().toString()));

                listCall.enqueue(new Callback<Employee>() {
                    @Override
                    public void onResponse(Call<Employee> call, Response<Employee> response) {
                        Toast.makeText(SearchEmployeeActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                        String content = "";
                        content += "ID: " + response.body().getId() + "\n";
                        content += "Name: " + response.body().getEmployee_name() + "\n";
                        content += "Salary: " + response.body().getEmployee_salary() + "\n";
                        content += "Age: " + response.body().getEmployee_age() + "\n";
                        tvSearchResult.setText(content);
                    }

                    @Override
                    public void onFailure(Call<Employee> call, Throwable t) {
                        Toast.makeText(SearchEmployeeActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
