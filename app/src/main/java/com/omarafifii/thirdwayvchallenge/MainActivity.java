package com.omarafifii.thirdwayvchallenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.omarafifii.thirdwayvchallenge.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<Integer> pastNumbers = new ArrayList<Integer>();
    private ArrayList<String> pastOperations = new ArrayList<String>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private String operation;
    private int lastResult = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        pastNumbers.add(5);
        pastNumbers.add(4);
        pastOperations.add("+");
        pastOperations.add("-");
        recyclerView = binding.rvHistory;
        mAdapter = new MyAdapter(pastOperations,pastNumbers);
        recyclerView.setAdapter(mAdapter);

        binding.btPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.btDivide.setEnabled(false);
                binding.btMinus.setEnabled(false);
                binding.btMultiply.setEnabled(false);
                operation = "+";
            }
        });

        binding.btMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.btDivide.setEnabled(false);
                binding.btPlus.setEnabled(false);
                binding.btMultiply.setEnabled(false);
                operation = "-";
            }
        });

        binding.btMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.btDivide.setEnabled(false);
                binding.btPlus.setEnabled(false);
                binding.btMinus.setEnabled(false);
                operation = "*";
            }
        });

        binding.btDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.btMinus.setEnabled(false);
                binding.btPlus.setEnabled(false);
                binding.btMultiply.setEnabled(false);
                operation = "/";
            }
        });

        binding.btEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equalsBtnClicked();
            }
        });

        binding.btUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                undoClicked();
            }
        });

        binding.btRedo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redoClicked();
            }
        });




    }

    private void redoClicked() {

    }

    private void undoClicked() {

    }

    public void equalsBtnClicked(){
        binding.btMinus.setEnabled(true);
        binding.btPlus.setEnabled(true);
        binding.btMultiply.setEnabled(true);
        binding.btMultiply.setEnabled(true);
        int result = 0;
        int input = 0;
        if (binding.edNumber.getText().toString().trim().length() == 0 || binding.edNumber.getText().toString().matches("")
        || binding.edNumber.getText().equals(null)){
            return;
        }else{
            input = Integer.parseInt(binding.edNumber.getText().toString());
        }
        if (operation == "/"){
            result = lastResult / input;
        }else if (operation == "-"){
            result = lastResult - input;
        }else if (operation == "*"){
            result = lastResult * input;
        }else {
            result = lastResult + input;
        }
        binding.tvResult.setText("Result = " + result);
        binding.edNumber.setText("");
        lastResult = result;
        pastNumbers.add(result);
        pastOperations.add(operation);
    }


}