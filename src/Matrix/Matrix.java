package Matrix;

public interface Matrix<T> {

    int[] getDimension();

    T getElement( int i, int j);

    T[] getLine(int i);

    T[] getColumn(int j);

    void printMatrix();

    boolean equals(Matrix<T> secondMatrix);

    int hashCode();




}
