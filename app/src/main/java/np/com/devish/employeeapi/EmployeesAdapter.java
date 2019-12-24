package np.com.devish.employeeapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import np.com.devish.employeeapi.model.Employee;

public class EmployeesAdapter extends RecyclerView.Adapter<EmployeesAdapter.EmployeesViewHolder> {
    Context mContext;
    List<Employee> employeeList;

    //Constructor to receive data from the activity
    public EmployeesAdapter(Context mContext, List<Employee> employeeList) {
        this.mContext = mContext;
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public EmployeesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employeeview, parent, false);
        return new EmployeesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeesViewHolder holder, int position) {
        final Employee employee = employeeList.get(position);
        holder.tvEmpId.setText(employee.getId());
        holder.tvEmpName.setText(employee.getEmployee_name());
        holder.tvEmpSalary.setText((int) employee.getEmployee_salary());
        holder.tvEmpAge.setText(employee.getEmployee_age());
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public class EmployeesViewHolder extends RecyclerView.ViewHolder {
        TextView tvEmpId, tvEmpName, tvEmpSalary, tvEmpAge;

        public EmployeesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEmpId = itemView.findViewById(R.id.tvEmpId);
            tvEmpName = itemView.findViewById(R.id.tvEmpName);
            tvEmpSalary = itemView.findViewById(R.id.tvEmpSalary);
            tvEmpAge = itemView.findViewById(R.id.tvEmpAge);
        }
    }
}
