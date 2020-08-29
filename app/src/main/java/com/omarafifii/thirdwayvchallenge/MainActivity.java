package com.omarafifii.thirdwayvchallenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.omarafifii.thirdwayvchallenge.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<Integer> pastNumbers = new ArrayList<Integer>();
    private ArrayList<Integer> redoNumbers = new ArrayList<Integer>();
    private ArrayList<String> pastOperations = new ArrayList<String>();
    private ArrayList<String> redoOperations = new ArrayList<String>();
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private String operation;
    private int lastResult = 0;
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {

        /**
         *
         * Is called when an item in the recyclerView is clicked
         *
         * @param view a viewHolder object representing an item in the recyclerView
         */
        @Override
        public void onClick(View view) {
            recyclerUndo(view);
        }
    };


    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getLastResult() {
        return lastResult;
    }

    public void setLastResult(int lastResult) {
        this.lastResult = lastResult;
    }

    public ActivityMainBinding getBinding() {
        return binding;
    }

    public void setBinding(ActivityMainBinding binding) {
        this.binding = binding;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        binding.btEquals.setEnabled(false);
        setContentView(view);

        recyclerView = binding.rvHistory;
        mAdapter = new MyAdapter(pastOperations,pastNumbers);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(onItemClickListener);

        binding.btPlus.setOnClickListener(new View.OnClickListener() {

            /**
             * Is called when the button is clicked and it adjusts which buttons are enabled and
             * which are disabled.
             * It also updates the value of the operation variable
             *
             * @param view btn on which the user clicked
             */
            @Override
            public void onClick(View view) {
                binding.btDivide.setEnabled(false);
                binding.btMinus.setEnabled(false);
                binding.btMultiply.setEnabled(false);
                binding.btEquals.setEnabled(true);
                operation = "+";
            }
        });

        binding.btMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * Is called when the button is clicked and it adjusts which buttons are enabled and
             * which are disabled.
             * It also updates the value of the operation variable
             *
             * @param view btn on which the user clicked
             */
            public void onClick(View view) {
                binding.btDivide.setEnabled(false);
                binding.btPlus.setEnabled(false);
                binding.btMultiply.setEnabled(false);
                binding.btEquals.setEnabled(true);
                operation = "-";
            }
        });

        binding.btMultiply.setOnClickListener(new View.OnClickListener() {
            /**
             * Is called when the button is clicked and it adjusts which buttons are enabled and
             * which are disabled.
             * It also updates the value of the operation variable
             *
             * @param view btn on which the user clicked
             */
            @Override
            public void onClick(View view) {
                binding.btDivide.setEnabled(false);
                binding.btPlus.setEnabled(false);
                binding.btMinus.setEnabled(false);
                binding.btEquals.setEnabled(true);
                operation = "*";
            }
        });

        binding.btDivide.setOnClickListener(new View.OnClickListener() {
            /**
             * Is called when the button is clicked and it adjusts which buttons are enabled and
             * which are disabled.
             * It also updates the value of the operation variable
             *
             * @param view btn on which the user clicked
             */
            @Override
            public void onClick(View view) {
                binding.btMinus.setEnabled(false);
                binding.btPlus.setEnabled(false);
                binding.btMultiply.setEnabled(false);
                binding.btEquals.setEnabled(true);
                operation = "/";
            }
        });

        binding.btEquals.setOnClickListener(new View.OnClickListener() {
            /**
             * Execute the equalsBtnClicked method when the button is clicked.
             *
             * @param view btn on which the user clicked
             */
            @Override
            public void onClick(View view) {
                equalsBtnClicked();
            }
        });

        binding.btUndo.setOnClickListener(new View.OnClickListener() {
            /**
             * Execute the undoClicked method when the button is clicked.
             *
             * @param view btn on which the user clicked
             */
            @Override
            public void onClick(View view) {
                undoClicked();
            }
        });

        binding.btRedo.setOnClickListener(new View.OnClickListener() {
            /**
             * Execute the redoClicked method when the button is clicked.
             *
             * @param view btn on which the user clicked
             */
            @Override
            public void onClick(View view) {
                redoClicked();
            }
        });




    }

    /**
     * Executes the redo operation if there is an operationto redo
     */
    public void redoClicked() {
        if(redoNumbers.size() > 0){
            String pastOperation = redoOperations.get(redoOperations.size() - 1);
            int pastNumber = redoNumbers.get(redoNumbers.size() - 1);
            int result = 0;
            if (pastOperation == "/"){
                result = lastResult / pastNumber;
            }else if (pastOperation == "-"){
                result = lastResult - pastNumber;
            }else if (pastOperation == "*"){
                result = lastResult * pastNumber;
            }else {
                result = lastResult + pastNumber;
            }
            binding.tvResult.setText("Result = " + result);
            binding.edNumber.setText("");
            lastResult = result;
            pastNumbers.add(pastNumber);
            pastOperations.add(pastOperation);
            redoNumbers.remove(redoNumbers.size()-1);
            redoOperations.remove(redoOperations.size()-1);
            mAdapter.notifyDataSetChanged();
        }
        else{
            Toast.makeText(this,"No operations to redo!",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * It creates the undo function for the item that was clicked on from the list of past operations.
     *
     * @param view the viewHolder object that was clicked on by the user
     */
    public void recyclerUndo(View view) {
        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
        int position = viewHolder.getAdapterPosition();
        String pastOperation = pastOperations.get(position);
        int pastNumber = pastNumbers.get(position);
        int result = 0;
        if (pastOperation == "/"){
            result = lastResult * pastNumber;
        }else if (pastOperation == "-"){
            result = lastResult + pastNumber;
        }else if (pastOperation == "*"){
            result = lastResult / pastNumber;
        }else {
            result = lastResult - pastNumber;
        }
        binding.tvResult.setText("Result = " + result);
        binding.edNumber.setText("");
        lastResult = result;
        redoNumbers.add(pastNumber);
        redoOperations.add(pastOperation);
        pastNumbers.remove(position);
        pastOperations.remove(position);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * Checks if an undo operation is possible and if it is then it undoes the last operation
     */
    public void undoClicked() {
        if(pastNumbers.size() > 0){
            String pastOperation = pastOperations.get(pastOperations.size() - 1);
            int pastNumber = pastNumbers.get(pastNumbers.size() - 1);
            int result = 0;
            if (pastOperation == "/"){
                result = lastResult * pastNumber;
            }else if (pastOperation == "-"){
                result = lastResult + pastNumber;
            }else if (pastOperation == "*"){
                result = lastResult / pastNumber;
            }else {
                result = lastResult - pastNumber;
            }
            binding.tvResult.setText("Result = " + result);
            binding.edNumber.setText("");
            lastResult = result;
            redoNumbers.add(pastNumber);
            redoOperations.add(pastOperation);
            pastNumbers.remove(pastNumbers.size()-1);
            pastOperations.remove(pastOperations.size()-1);
            mAdapter.notifyDataSetChanged();
        }
        else{
            Toast.makeText(this,"No operations to undo!",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * When the equals button is clicked it checks if the user has input a number in the text field
     * and if so it executes the operation and adds it to the list in the bottom.
     *
     * It also makes the operation buttons enabled once again.
     */
    public void equalsBtnClicked(){
        int result = 0;
        int input = 0;
        if (binding.edNumber.getText().toString().trim().length() == 0 || binding.edNumber.getText().toString().matches("")
        || binding.edNumber.getText().equals(null)){
            Toast.makeText(this,"Please enter a number!",Toast.LENGTH_SHORT).show();
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
        pastNumbers.add(input);
        pastOperations.add(operation);
        redoNumbers.clear();
        redoOperations.clear();
        mAdapter.notifyDataSetChanged();
        binding.btEquals.setEnabled(false);
        binding.btMinus.setEnabled(true);
        binding.btPlus.setEnabled(true);
        binding.btMultiply.setEnabled(true);
        binding.btDivide.setEnabled(true);
    }


}