package np.com.devish.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import np.com.devish.employeeapi.API.EmployeeAPI;
import np.com.devish.employeeapi.model.Employee;
import np.com.devish.employeeapi.model.EmployeeCUD;
import np.com.devish.employeeapi.url.URL;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateDeleteEmployeeActivity extends AppCompatActivity {
    EditText etEmployeeId, etUpdateName, etUpdateSalary, etUpdateAge;
    Button btnEmployeeIdSearch, btnUpdate, btnDelete;
    Retrofit retrofit;
    EmployeeAPI employeeAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_employee);

        // BInding
        etEmployeeId = findViewById(R.id.etEmployeeId);
        etUpdateName = findViewById(R.id.etUpdateName);
        etUpdateSalary = findViewById(R.id.etUpdateSalary);
        etUpdateAge = findViewById(R.id.etUpdateAge);
        btnEmployeeIdSearch = findViewById(R.id.btnEmployeeIdSearch);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        btnEmployeeIdSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validation for searching employee id
                if (TextUtils.isEmpty(etEmployeeId.getText())){
                    etEmployeeId.setError("Please enter employee id");
                    return;
                }
                CreateInstance();
                Call<Employee> listCall = employeeAPI.getEmployeeByID(Integer.parseInt(etEmployeeId.getText().toString()));

                listCall.enqueue(new Callback<Employee>() {
                    @Override
                    public void onResponse(Call<Employee> call, Response<Employee> response) {
                        etUpdateName.setText(response.body().getEmployee_name());
                        etUpdateSalary.setText(Float.toString(response.body().getEmployee_salary()));
                        etUpdateAge.setText(Integer.toString(response.body().getEmployee_age()));
                    }

                    @Override
                    public void onFailure(Call<Employee> call, Throwable t) {
                        Toast.makeText(UpdateDeleteEmployeeActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etEmployeeId.getText())){
                    etEmployeeId.setError("Please enter employee id");
                    return;
                }
                if (TextUtils.isEmpty(etUpdateName.getText())){
                    etUpdateName.setError("Please enter employee name");
                    return;
                }
                if (TextUtils.isEmpty(etUpdateSalary.getText())){
                    etUpdateSalary.setError("Please enter employee salary");
                    return;
                }
                if (TextUtils.isEmpty(etUpdateAge.getText())){
                    etUpdateAge.setError("Please enter employee age");
                    return;
                }
                CreateInstance();
                EmployeeCUD employeeCUD = new EmployeeCUD(
                        etUpdateName.getText().toString(),
                        Float.parseFloat(etUpdateSalary.getText().toString()),
                        Integer.parseInt(etUpdateAge.getText().toString())
                );
                Call<Void> voidCall = employeeAPI.updateEmployee(Integer.parseInt(etEmployeeId.getText().toString()), employeeCUD);
                voidCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(UpdateDeleteEmployeeActivity.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(UpdateDeleteEmployeeActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etEmployeeId.getText())){
                    etEmployeeId.setError("Please enter employee id");
                    return;
                }
                CreateInstance();
                Call<Void> voidCall = employeeAPI.deleteEmployee(Integer.parseInt(etEmployeeId.getText().toString()));

                voidCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(UpdateDeleteEmployeeActivity.this, "Successfully Deleted!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(UpdateDeleteEmployeeActivity.this, "Cannot Delete!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    private void CreateInstance(){
        retrofit = new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        employeeAPI = retrofit.create(EmployeeAPI.class);
    }
}
