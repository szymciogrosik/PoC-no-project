public class JelinskiMorandyModel {

    private double N;
    private double[] t;
    private double n;
    private double step;

    private double leftSide;
    private double rightSide;

    private double previousDifference;
    private double currentDifference;

    private double precision;

    public JelinskiMorandyModel(double N, double[] t, double step, double precision) {
        this.N = N;
        this.t = t;
        this.n = t.length;
        this.step = step;
        this.precision = precision;
        leftSide = leftSideCalculate();
        rightSide = rightSideCalculate();
        currentDifference = Math.abs(( Math.abs(leftSide) - Math.abs(rightSide)));
    }

    public void calculateModel() {
        calculateN();

        System.out.println("This is N: " + N);
        System.out.println("This is currentDifference: " + currentDifference);
    }

    private void calculateN() {
        while(currentDifference > precision) {
            previousDifference = Math.abs(currentDifference);
            N += step;
            leftSide = leftSideCalculate();
            rightSide = rightSideCalculate();

            currentDifference = Math.abs(leftSide) - Math.abs(rightSide);

            if(previousDifference < Math.abs(currentDifference))
                step *= -(1.0/2.0);
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
        double sumToReturnBottom1 = 0.0;
        double sumToReturnBottom2 = 0.0;

        for (int i = 1; i <= this.n; i++)
            sumToReturnTop += t[i-1];

        for (int i = 1; i <= this.n; i++)
            sumToReturnBottom1 += t[i-1];

        for (int i = 1; i <= this.n; i++)
            sumToReturnBottom2 += ((i-1) * t[i-1]);

        return (sumToReturnTop * this.n) / ((sumToReturnBottom1 * this.N) - (sumToReturnBottom2));
    }
}
