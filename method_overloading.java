public class method_overloading {
    static int push(int x, int y){
        return x+y;
    }
    static double push(double x, double y){
        return x-y;
    }
    public static void main(String[] args) {
      int number1= push(20,40 );
      double number2= push(20.90,40.09 );
      System.out.println(number1);
      System.out.println(number2);
    }
}
