package np.com.devish.employeeapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import np.com.devish.employeeapi.API.EmployeeAPI;
import np.com.devish.employeeapi.model.Employee;
import np.com.devish.employeeapi.url.URL;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowAllEmployeeActivity extends AppCompatActivity {

    TextView tvOutput;
    //private RecyclerView recyclerView;
    List<Employee> employeeList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_employee);

        tvOutput = findViewById(R.id.tvOutput);
        //recyclerView = findViewById(R.id.recyclerView);


        // Making object of interface
        EmployeeAPI employeeAPI = URL.createInstance().create(EmployeeAPI.class);
        Call<List<Employee>> listCall = employeeAPI.getAllEmployees();

        // Asynchronous Call
        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(ShowAllEmployeeActivity.this, "Error code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Employee> employeeList = response.body();
                for (Employee emp: employeeList){
                     String data = "";
                     data += "Name is: " +emp.getEmployee_name() + "\n";
                     data += "Salary is: " +emp.getEmployee_salary() + "\n";
                     data += "Age is: " +emp.getEmployee_age() + "\n";
                     data += "-----------------------------------" + "\n";
                    tvOutput.append(data);

                    //employeeList.add(new Employee(emp.getId(), emp.getEmployee_name(), emp.getEmployee_salary(), emp.getEmployee_age()));
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.d("Msg", "onFailure: " + t.getLocalizedMessage());
                Toast.makeText(ShowAllEmployeeActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
//        EmployeesAdapter employeesAdapter = new EmployeesAdapter(this, employeeList);
//        recyclerView.setAdapter(employeesAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}
