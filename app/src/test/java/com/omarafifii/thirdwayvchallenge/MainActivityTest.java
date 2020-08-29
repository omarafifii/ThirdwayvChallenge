package com.omarafifii.thirdwayvchallenge;

import android.app.Application;

import com.omarafifii.thirdwayvchallenge.databinding.ActivityMainBinding;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class MainActivityTest {

    MainActivity mMain;

    @Before
    public void setUp(){
        MainActivity main = new MainActivity();
        mMain = main;
        mMain.setLastResult(5);
        mMain.setOperation("+");
        ActivityMainBinding binding = mMain.getBinding();
        binding.edNumber.setText("5");

    }

    @Test
    public void redoClicked() {
    }

    @Test
    public void recyclerUndo() {
    }

    @Test
    public void undoClicked() {
    }

    @Test
    public void equalsBtnClicked() {
        mMain.equalsBtnClicked();
        assertEquals(10, mMain.getLastResult());

    }
}