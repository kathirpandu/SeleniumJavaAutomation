package com.kathir.runners;

import com.kathir.programs.InputPrograms;

import static com.kathir.programs.InputPrograms.findDuplicates;
import static com.kathir.programs.InputPrograms.reverseString;

public class TestRunnerInd {

    public static void main(String[] args){
        String input = "kathiravan";

       findDuplicates(input);
       reverseString(input);
    }

}
