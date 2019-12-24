package np.com.devish.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {

    Button btnShowEmployee, btnRegisterEmployee, btnSearchEmployee, btnUpdateDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Binding
        btnShowEmployee = findViewById(R.id.btnShowEmployee);
        btnRegisterEmployee = findViewById(R.id.btnRegisterEmployee);
        btnSearchEmployee = findViewById(R.id.btnSearchEmployee);
        btnUpdateDelete = findViewById(R.id.btnUpdateDelete);

        // Button for Opening Showing Employee Activity
        btnShowEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ShowAllEmployeeActivity.class);
                startActivity(intent);
            }
        });

        // Button for Opening Register Employee Activity
        btnRegisterEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, RegisterEmployeeActivity.class);
                startActivity(intent);
            }
        });

        // Button for Opening Searching Employee Activity
        btnSearchEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, SearchEmployeeActivity.class);
                startActivity(intent);
            }
        });

        // Button for Opening Update/Delete Employee Activity
        btnUpdateDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, UpdateDeleteEmployeeActivity.class);
                startActivity(intent);
            }
        });
    }
}
