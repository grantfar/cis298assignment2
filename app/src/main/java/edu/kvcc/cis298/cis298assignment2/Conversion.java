package edu.kvcc.cis298.cis298assignment2;

/**
 * Created by grant on 9/28/16.
 */

public final class Conversion {

    public static double celsiusToFahrenheit(double degrees)
    {
        return degrees * 9/5 + 32;
    }
    public static double celsiusToKelvin(double degrees)
    {
        return degrees + 273.15;
    }
    public static double celsiusToRankine(double degrees)
    {
        return (degrees + 273.15) *9/5;
    }


    public static double fahrenheitToCelsius(double degrees)
    {
        return (degrees - 32) * 5/9;
    }
    public static double fahrenheitToKelvin(double degrees)
    {
        return (degrees + 459.67) * 5/9;
    }
    public static double fahrenheitToRankine(double degrees)
    {
        return degrees + 459.67;
    }


    public static double kelvinToCelsius(double degrees)
    {
        return degrees - 273.15;
    }
    public static double kelvinToFahrenheit(double degrees)
    {
        return degrees * 9/5 - 459.67;
    }
    public static double kelvinToRankine(double degrees)
    {
        return degrees * 9/5;
    }


    public static double rankineToFahrenheit(double degrees)
    {
        return degrees - 459.67;
    }
    public static double rankineToCelsius(double degrees)
    {
        return (degrees - 459.67) * 5/9;
    }
    public static double rankineToKelvin(double degrees)
    {
        return degrees * 5/9;
    }
}
