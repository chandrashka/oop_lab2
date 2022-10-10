package Matrix;

import java.util.Arrays;
import java.util.Random;


public final class ImmutableMatrix<T> implements Matrix<T> {
    private final T[][] matrix;


    public ImmutableMatrix(T[][] temp) {
        this.matrix =  Arrays.stream(temp)
                .map(arr -> arr.clone())
                .toArray(s -> temp.clone());
    }

    public ImmutableMatrix() {
        this.matrix = (T[][]) new Object[0][0];
    }

    public ImmutableMatrix(int i, int j) {
        this.matrix = (T[][]) new Object[i][j];
    }

    public ImmutableMatrix(ImmutableMatrix<T> temp) {
        this.matrix = Arrays.stream(temp.matrix)
                .map(arr -> arr.clone())
                .toArray(s -> temp.matrix.clone());
    }

    public ImmutableMatrix(MutableMatrix<T> temp) {
        this.matrix = Arrays.stream(temp.matrix)
                .map(arr -> arr.clone())
                .toArray(s -> temp.matrix.clone());
    }



    @Override
    public int[] getDimension() {
        try {
            int[] dimension = new int[2];
            dimension[0] = this.matrix.length;
            dimension[1] = this.matrix[0].length;
            return dimension;
        } catch (Exception ArrayIndexOutOfBoundsException) {
            return new int[]{-1,-1};
        }


    }

    @Override
    public T getElement(int i, int j) {
        return this.matrix[i][j];

    }

    @Override
    public T[] getLine(int line) {
        return this.matrix[line];
    }

    @Override
    public T[] getColumn(int column) {
        int[] dimension = getDimension();
        Object[] raw = new Object[dimension[0]];
        for (int k = 0; k < dimension[0]; k++) {
            T[] line = getLine(k);
            raw[k] = line[column];
        }
        return (T[]) raw;
    }

    @Override
    public void printMatrix() {
        int[] dimension = getDimension();
        if(dimension[0] != -1) {
            for (int i = 0; i < dimension[0]; i++) {
                System.out.print("|  ");
                for (int j = 0; j < dimension[1]; j++) {
                    System.out.print(getElement(i, j) + "  ");
                }
                System.out.print("|\n");
            }
        }
        else {
            System.out.println("Матриця пуста");
        }
    }

    @Override
    public boolean equals(Matrix<T> secondMatrix) {
        if (secondMatrix == null) {
            return false;
        }
        int[] dimension = getDimension();
        int[] dimension2 = secondMatrix.getDimension();
        if (dimension[0] != dimension2[0] || dimension[1] != dimension2[1]) {
            return false;
        }
        for (int i = 0; i < dimension[0]; i++) {
            for (int j = 0; j < dimension[1]; j++) {
                if (!getElement(i, j).equals(secondMatrix.getElement(i, j))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for(int i=0; i<this.matrix.length; i++) {
            hash =+ Arrays.hashCode(this.matrix[i]);
        }
        return hash;
    }

    public static ImmutableMatrix<Integer> createRandomLineOfInteger(int length) {
        Integer[][] tempLine = new Integer[1][length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int temp = random.nextInt(100);
            tempLine[0][i] = temp;
        }
        return new ImmutableMatrix<>(tempLine);
    }

    public ImmutableMatrix<T> setElement(int i, int j, T element){
        MutableMatrix<T> mutableMatrix = new MutableMatrix<>(this.matrix);
        mutableMatrix.setElement(i,j,element);
        return new ImmutableMatrix<T>(mutableMatrix);
    }

    public ImmutableMatrix<T> setLine(int i, T[] elements){
        MutableMatrix<T> mutableMatrix = new MutableMatrix<>(this.matrix);
        mutableMatrix.setLine(i,elements);
        return new ImmutableMatrix<T>(mutableMatrix);
    }

    public ImmutableMatrix<T> addMatrix(ImmutableMatrix<T> secondMatrix){
        MutableMatrix<T> temp = new MutableMatrix<>(this.matrix);
        MutableMatrix<T> temp2 = new MutableMatrix<>(secondMatrix.matrix);
        MutableMatrix<T> res = temp2.addMatrix(temp);
        return new ImmutableMatrix<T>(res);
    }
    public ImmutableMatrix<T> multScal(int scal){
        MutableMatrix<T> temp = new MutableMatrix<T>(this.matrix);
        MutableMatrix<T> res = temp.multScalar(scal);
        return new ImmutableMatrix<T>(res);
    }

}
