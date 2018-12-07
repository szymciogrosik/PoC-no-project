public class SchickWolvertonModel {

    private double precision;
    private double n;
    private double[] t;

    private double previousDifference;
    private double currentDifference;

    private double T;
    private double N;
    private double leftSide;
    private double rightSide;

    public SchickWolvertonModel(double[] t, double precision) {
        this.t = t;
        this.precision = precision;
        this.n = t.length;
        this.N = t.length+1;

        this.T = 0.0;
    }

    public void calculateModel() {
        calculateT();

        leftSide = leftSideCalculate();
        rightSide = rightSideCalculate();
        currentDifference = Math.abs(( Math.abs(leftSide) - Math.abs(rightSide)));

        calculateN();
                    System.out.println(leftSide + " ; " + rightSide);


        System.out.println("This is N: " + N);
        System.out.println("This is currentDifference: " + currentDifference);
    }

    private void calculateN() {
        while(currentDifference > precision) {
            previousDifference = Math.abs(currentDifference);
            N++;

//            System.out.println(leftSide + " ; " + rightSide);
            leftSide = leftSideCalculate();
            rightSide = rightSideCalculate();

//            System.out.println(leftSide + " ; " + rightSide);
            currentDifference = Math.abs(leftSide) - Math.abs(rightSide);

            if(previousDifference < Math.abs(currentDifference))
                break;
        }
    }

    private void calculateT() {
        for (int i = 1; i <= this.t.length; i++)
            T += (t[i-1]*t[i-1]);
    }

    private double leftSideCalculate() {
        return (this.n) / ((this.N * this.T) - calculateSumBottomLeftSide());
    }

    private double calculateSumBottomLeftSide() {
        double sumToReturn = 0.0;
        for (int i = 1; i <= this.t.length; i++) {
            sumToReturn += ((i-1)*(t[i-1]*(t[i-1])));
        }
        return sumToReturn;
    }

    private double rightSideCalculate() {
        double sumToReturn = 0.0;

        for (int i = 1; i <= this.t.length ; i++) {
            sumToReturn += ((1.0)/((this.N-(i-1))*this.T));
        }

        return sumToReturn;
    }
}
