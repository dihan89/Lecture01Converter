import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.StringBuilder;
import java.nio.file.Files;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


abstract class TemperatureScale {
    String name;
    String shortName;

    double a;
    double b;
    public double fromCelciy(double value) {
        return a * value + b;
    }
    public String sFromCelciy(double value) {
        double result = a * value + b;
        return "result: " + value + " C = " + 
                String.format("%.2f", result).replace(',', '.') + " " + shortName;
    }

    TemperatureScale(String name, String shortName, double a, double b) {
        this.name = name;
        this.shortName = shortName;
        this.a = a;
        this.b = b;
    }
}

class Kelvin extends TemperatureScale{
    Kelvin() {
        super("Kelvin", "K", 1.0, 273.15);
    }
}
class Fahrenheit extends TemperatureScale{
    Fahrenheit() {
        super("Fahrenheit", "F", (double)9.0/5.0, 32.0);
    }
}
class Newton extends TemperatureScale{
    Newton() {
        super("Newton", "N", (double)33.0/100.0, 0.0);
    }
}
class Rankin extends TemperatureScale{
    Rankin() {
        super("Rankin", "Ra", (double)9.0/5.0, 491.67);
    }
}

public class Lecture1Converter
{
    public static void help() {
        System.out.print("  Input string format is <scale><space><value>.");
        System.out.println(" The symbols corresponding to the scales are as follows:");
        System.out.println("        K: Kelvin");
        System.out.println("        F: Fahrenheit");
        System.out.println("        N: Newton");
        System.out.println("        R: Rankin");
    }

    public static void main(String[] args)  {

        System.out.println("--------------------------------------------------");

        Map <String, TemperatureScale>scales = new HashMap<>(){{
            put ("K", new Kelvin());
            put ("F", new Fahrenheit());
            put ("N", new Newton());
            put ("R", new Rankin());
        }};
        
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the symbol of result temperature scale and value:");
            String strScale = (scanner.next()).trim().toUpperCase();
            String strValue = scanner.next();
            try {double value = Double.parseDouble(strValue);
                if (scales.containsKey(strScale)) {
                    System.out.println(scales.get(strScale).sFromCelciy(value));
                } else {
                    System.out.println(" This scale is not supported! ");
                    help();
                    break;
                }
            } catch(NumberFormatException exc) {
                System.out.println("Incorrect number value is entered!");
                break;
            }     
        }
        scanner.close();
    }
}