package com.example.calculatorservice;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public class CalculatorWebServiceImpl implements CalculatorWebService {
    @Override
    @WebMethod
    public int add(int num1, int num2) {
        return num1 + num2;
    }
}
