package Persons;

import Services.FileAdd;

import java.io.*;
import java.util.Scanner;

public class Employee extends Person  implements FileAdd {
    private String function;
    private Double salary;


    @Override
    public void readData() {
        super.readData();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("function : ");
            function = reader.readLine();
            System.out.println("salary ");
            Scanner in = new Scanner(System.in);
            salary = in.nextDouble();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Employee(String[] values) {
        super(values[0],values[1],values[2],values[3]);
        this.function = values[4];
        this.salary = Double.parseDouble(values[5]);
    }
    @Override
    public  void printData() {
        super.printData();
        System.out.println("function : " + function);
        System.out.println("salary : " + salary);
    }
    public Employee() {
        super();
        function = "";
        salary = 0.0;
    }

    public Employee(String function, Double salary) {
        super();
        this.function = function;
        this.salary = salary;
    }

    public Employee(String firstName, String lastName, String birthday, String adress, String function, Double salary) {
        super(firstName, lastName, birthday, adress);
        this.function = function;
        this.salary = salary;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public void addToFile(String fileName) {
        File log = new File(fileName);
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(log, true));
            StringBuilder sb = new StringBuilder();

            sb.append(getFirstName());
            sb.append(",");
            sb.append(getLastName());
            sb.append(",");
            sb.append(getBirthday());
            sb.append(",");
            sb.append(getAdress());
            sb.append(",");
            sb.append(getFunction());
            sb.append(",");
            sb.append(salary.toString());
            pw.println(sb.toString());
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
