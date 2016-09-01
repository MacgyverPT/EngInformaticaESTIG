/**
 * Evaluates a given function
 * Based upon the Janino library example code 
 * at http://docs.codehaus.org/display/JANINO/Basic
 * @author Joao Paulo Barros
 * @version 2011/11/27
 */
public class FunctionEval
{
    private org.codehaus.janino.ExpressionEvaluator ee;
    
    public FunctionEval(String function)
    {
        // Compile the expression once; relatively slow.
        try
        {
            this.ee = new org.codehaus.janino.ExpressionEvaluator(
                function,                    // expression
                double.class,                // expressionType
                new String[] { "x"},         // parameterNames
                new Class[] { double.class } // parameterTypes
            );
        }
        catch (Exception e)
        {
            System.err.println("Error parsing expression");
        }

    }

    /**
     * Evaluates the function for the given x value
     * @param x the function parameter
     * @return the function value for the given x value
     */
    public double eval(double x)
    { 
        Double res = null; 
        try
        {
            res = (Double) ee.evaluate(new Object[] {new Double(x)});
        }
        catch (Exception e)
        {
            System.err.println("Error evaluating expression");
        }
        return res;
    }
}
