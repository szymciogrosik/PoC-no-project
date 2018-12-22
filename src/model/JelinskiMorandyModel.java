package model;

public class JelinskiMorandyModel {

    private int N;
    private int[] t;
    private double n;

    private double leftSide;
    private double rightSide;

    private double currentDifference;
    private double precision;
    private double fi;
    private double expectedValue;

    public JelinskiMorandyModel(int[] t, double precision) {
        this.t = t;
        this.n = t.length;
        this.precision = precision;
    }

    public void calculateModel() {
        this.N = t.length+1;
        this.fi = 0.0;

        leftSide = leftSideCalculate();
        rightSide = rightSideCalculate();
        currentDifference = Math.abs(leftSide - rightSide);

        calculateN();
        calculateFi();
        calculateExpectedValue();
    }

    private void calculateN() {
        while(currentDifference > precision) {
            N++;

            leftSide = leftSideCalculate();
            rightSide = rightSideCalculate();
            currentDifference = Math.abs(leftSide - rightSide);
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

    public String getNString() {
        return N + "";
    }

    public String getFiString() {
        return fi + "";
    }

    public String getExpectedValueString() {
        return expectedValue + "";
    }
}
