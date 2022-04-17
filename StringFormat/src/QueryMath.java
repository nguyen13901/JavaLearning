

class QueryMath{
    public static void main(String []argh){
        //Scanner in = new Scanner(System.in);
        //int t=in.nextInt();
        for(int i=0;i<1;i++){
            //int a = in.nextInt();
            //int b = in.nextInt();
            //int n = in.nextInt();
            display(0, 2, 10);
            System.out.println();
        }
        //in.close();
    }

    private static void display(int a, int b, int n) {
        int rs = a;
        for (int i = 0; i < n; i++) {
            rs = iQuery(rs, b, i);
            System.out.print(rs + " ");
        }
    }

    private static int iQuery(int a, int b, int i) {
        return a + b * (int)Math.pow(2, i);
    }
}