package com.example.crudtest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.MyViewHolder> {
    List<Employee> employeeList;


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Id, firtname, lastname;
        ToggleButton insured;

        MyViewHolder(View view) {
            super(view);
            Id = view.findViewById(R.id.textViewRVID);
            firtname = view.findViewById(R.id.textViewRVFirstname);
            lastname = view.findViewById(R.id.textViewRVLastname);
            insured = view.findViewById(R.id.toggleButtonRVInsured);
        }
    }

    EmployeeAdapter(List<Employee> bookList) {
        this.employeeList = bookList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.emprow, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final EmployeeAdapter.MyViewHolder viewHolder, final int i) {
        final Employee employee = employeeList.get(i);
        viewHolder.Id.setText(employee.getId() + "");
        viewHolder.firtname.setText(employee.getFirstName());
        viewHolder.lastname.setText(employee.getLastName());
        viewHolder.insured.setChecked(employee.isInsured());
    }

    @Override
    public int getItemCount() {
        return this.employeeList.size();
    }
}
