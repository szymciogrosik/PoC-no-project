package model;

public class JelinskiMorandyModel {

    private double N;
    private double[] t;
    private double n;

    private double leftSide;
    private double rightSide;

    private double previousDifference;
    private double currentDifference;

    private double precision;

    private double fi;
    private double expectedValue;

    public JelinskiMorandyModel(double[] t, double precision) {
        this.t = t;
        this.N = t.length+1;
        this.n = t.length;
        this.precision = precision;
        this.fi = 0.0;
    }

    public void calculateModel() {
        leftSide = leftSideCalculate();
        rightSide = rightSideCalculate();
        currentDifference = Math.abs(leftSide - rightSide);

        calculateN();
        calculateFi();
        calculateExpectedValue();

        System.out.println("This is N: " + N);
        System.out.println("This is currentDifference: " + currentDifference);
        System.out.println("Fi: " + fi);
        System.out.println("Ex("+ n+1 +"): " + expectedValue);
    }

    private void calculateN() {
        while(currentDifference > precision) {
            previousDifference = Math.abs(currentDifference);
            N++;
            leftSide = leftSideCalculate();
            rightSide = rightSideCalculate();

            currentDifference = Math.abs(leftSide - rightSide);

            if(previousDifference < Math.abs(currentDifference))
                break;
        }
    }

    private double leftSideCalculate() {
        double sumToReturn = 0.0;

        for (int i = 1; i <= this.n; i++)
            sumToReturn += 1.0/(this.N - (i-1));

        return sumToReturn;
    }

    private double rightSideCalculate() {
        double sumToReturnTop = 0.0;

        for (int i = 1; i <= this.n; i++)
            sumToReturnTop += t[i-1];

        return (sumToReturnTop * this.n) / calculateBottomRightSide();
    }

    private double calculateBottomRightSide() {
        double sumToReturnBottom1 = 0.0;
        double sumToReturnBottom2 = 0.0;

        for (int i = 1; i <= this.n; i++)
            sumToReturnBottom1 += t[i-1];

        for (int i = 1; i <= this.n; i++)
            sumToReturnBottom2 += ((i-1) * t[i-1]);

        return (sumToReturnBottom1 * this.N) - (sumToReturnBottom2);
    }

    private void calculateFi() {
        this.fi = this.n / calculateBottomRightSide();
    }

    private void calculateExpectedValue() {
        this.expectedValue = 1.0 / (this.fi*(this.N-(this.n)));
    }
}
