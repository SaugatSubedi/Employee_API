package np.com.devish.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import np.com.devish.employeeapi.API.EmployeeAPI;
import np.com.devish.employeeapi.model.EmployeeCUD;
import np.com.devish.employeeapi.url.URL;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterEmployeeActivity extends AppCompatActivity {

    EditText etName, etSalary, etAge;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_employee);

        // Binding
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etSalary = findViewById(R.id.etSalary);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validation
                if(TextUtils.isEmpty(etName.getText())){
                    etName.setError("Please enter employee name");
                    return;
                }
                if (TextUtils.isEmpty(etSalary.getText())){
                    etSalary.setError("Please enter employee salary");
                    return;
                }
                if (TextUtils.isEmpty(etAge.getText())){
                    etAge.setError("Please enter employee age");
                    return;
                }
                String name = etName.getText().toString();
                Float salary = Float.parseFloat(etSalary.getText().toString());
                int age = Integer.parseInt(etAge.getText().toString());

                EmployeeCUD employeeCUD = new EmployeeCUD(name, salary, age);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL.base_url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
                Call<Void> voidCall = employeeAPI.registerEmployee(employeeCUD);

                voidCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(RegisterEmployeeActivity.this, "You have been successfully registered!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(RegisterEmployeeActivity.this, "Failed to register.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
