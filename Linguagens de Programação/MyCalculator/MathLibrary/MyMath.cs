using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MathLibrary
{
    public static class MyMath
    {
        //private double result = 0;

        public static double add(double x, double y){
            return x + y;
        }

        public static double sub(double x, double y){
            return x - y;
        }

        public static double mult(double x, double y){
            double min = Math.Min(x, y);
            double max = Math.Max(x, y);
            double res = 0;

            for(int i=0; i < min; i++)
            {
                res += max;
            }

            return res + (min - Math.Floor(min)) * max;

            //OU: return x * y;
        }

        public static double div(double x, double y){
            return x / y;
        }
    }
}
