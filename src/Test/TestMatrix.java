package Test;
import org.junit.jupiter.api.Test;
import Matrix.ImmutableMatrix;
import Matrix.MutableMatrix;

public class TestMatrix {

    /*
    * створення та заповнення матриць різних типів різними методами та виведення їх
    * */
    @Test
    public void test1(){
        ImmutableMatrix<Integer> emptyMatrix = new ImmutableMatrix<Integer>();
        emptyMatrix.printMatrix();

        System.out.println();
        MutableMatrix<Integer> intMatrix = new MutableMatrix<Integer>(2,2);
        intMatrix.printMatrix();

        System.out.println();
        Double[][] tempArr = {{1.1, 1.1},{2.2, 2.2}};
        MutableMatrix<Double> doubleMatrix = new MutableMatrix<>(tempArr);
        doubleMatrix.printMatrix();

        System.out.println();
        ImmutableMatrix<Double> copyMatrix = new ImmutableMatrix<>(doubleMatrix);
        copyMatrix.printMatrix();

        System.out.println();
        MutableMatrix<Integer> randMatrix = MutableMatrix.createRandomLineOfInteger(5);
        randMatrix.printMatrix();

    }

    /*
        заповнення матриць елементами за допомогою різних методів
     */
    @Test
    public void test2(){
        ImmutableMatrix<String> strMatrix = new ImmutableMatrix<String>(2,2);
        strMatrix.printMatrix();

        System.out.println();
        strMatrix = strMatrix.setElement(0,0,"a");
        strMatrix = strMatrix.setLine(1, new String[]{"1"});
        strMatrix.printMatrix();
    }

    @Test
    public void test3(){
        Boolean[][] temp = {{true, true},{false, false}};
        Boolean[][] temp2 = {{true,false},{true,false}};

        ImmutableMatrix<Boolean> tempM = new ImmutableMatrix<>(temp);
        ImmutableMatrix<Boolean> temp2M = new ImmutableMatrix<>(temp2);

        tempM.printMatrix();
        temp2M.printMatrix();

        System.out.println();

        ImmutableMatrix<Boolean> res = tempM.addMatrix(temp2M);
        res.printMatrix();
    }
    @Test
    public void test4(){
        Integer[][] temp = {{1, 2},{3, 4}};
        ImmutableMatrix<Integer> tempM = new ImmutableMatrix<>(temp);
        tempM.printMatrix();


        ImmutableMatrix<Integer> temp2 = tempM.multScal(3);
        temp2.printMatrix();
    }
}

