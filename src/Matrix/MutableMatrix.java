package Matrix;

import java.util.Arrays;
import java.util.Random;

public class MutableMatrix<T> implements Matrix<T> {

    protected final T[][] matrix;

    public MutableMatrix(T[][] temp) {
        this.matrix = Arrays.stream(temp)
                .map(arr -> arr.clone())
                .toArray(s -> temp.clone());
    }

    public MutableMatrix() {
        this.matrix = (T[][]) new Object[0][0];
    }

    public MutableMatrix(int i, int j) {
        this.matrix = (T[][]) new Object[i][j];
    }

    public MutableMatrix(MutableMatrix<T> temp) {
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
            return new int[]{-1, -1};
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

        if (dimension[0] != -1) {
            for (int i = 0; i < dimension[0]; i++) {
                System.out.print("|  ");
                for (int j = 0; j < dimension[1]; j++) {
                    System.out.print(getElement(i, j) + "  ");
                }
                System.out.print("|\n");
            }
        } else {
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
        for (int i = 0; i < this.matrix.length; i++) {
            hash = +Arrays.hashCode(this.matrix[i]);
        }
        return hash;
    }

    public static MutableMatrix<Integer> createRandomLineOfInteger(int length) {
        Integer[][] tempLine = new Integer[1][length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int temp = random.nextInt(100);
            tempLine[0][i] = temp;
        }
        return new MutableMatrix<>(tempLine);
    }

    public void setElement(int i, int j, T element) {
        this.matrix[i][j] = element;
    }

    public void setLine(int i, T[] elements) {
        if (this.matrix.length == elements.length) {
            for (int j = 0; j < elements.length; j++) {
                setElement(i, j, elements[j]);
            }
        } else {
            System.out.println("Строку неможливо заповнити");
        }
    }

    public MutableMatrix addMatrix(MutableMatrix<T> secondMatrix) {
        int[] dim1 = this.getDimension();
        int[] dim2 = secondMatrix.getDimension();
        if (dim1[0] != dim2[0] || dim1[1] != dim2[1] && dim1[0]!=-1 && dim1[1]!=-1) {
            System.out.println("Матриці неможливо скласти");
            return new MutableMatrix<T>();
        } else {
            T element1 = this.getElement(0, 0);
            T element2 = secondMatrix.getElement(0, 0);
            if (element1 instanceof Number && element2 instanceof Number) {
                MutableMatrix<Number> resultMatrix = new MutableMatrix<Number>(dim2[0], dim2[1]);
                for (int i = 0; i < dim2[0]; i++) {
                    for (int j = 0; j < dim2[1]; j++) {
                        Number e1 = (Number) this.getElement(i, j);
                        Number e2 = (Number) secondMatrix.getElement(i, j);
                        Number result = e1.doubleValue() + e2.doubleValue();
                        resultMatrix.setElement(i, j, result);
                    }
                }
                return new MutableMatrix<Number>(resultMatrix);
            } else if (element1 instanceof Boolean && element2 instanceof Boolean) {
                MutableMatrix<Boolean> resultMatrix = new MutableMatrix<Boolean>(dim2[0], dim2[1]);
                for (int i = 0; i < dim2[0]; i++) {
                    for (int j = 0; j < dim2[1]; j++) {
                        Boolean e1 = (Boolean) this.getElement(i, j);
                        Boolean e2 = (Boolean) secondMatrix.getElement(i, j);
                        if (e1 || e2) {
                            resultMatrix.setElement(i, j, true);
                        } else {
                            resultMatrix.setElement(i, j, false);
                        }
                    }
                }
                return new MutableMatrix<Boolean>(resultMatrix);
            } else if (element1 instanceof Character && element2 instanceof Character) {
                MutableMatrix<String> resultMatrix = new MutableMatrix<String>(dim2[0], dim2[1]);
                for (int i = 0; i < dim2[0]; i++) {
                    for (int j = 0; j < dim2[1]; j++) {
                        Character e1 = (Character) this.getElement(i, j);
                        Character e2 = (Character) secondMatrix.getElement(i, j);
                        String result = e1.toString() + e2.toString();
                        resultMatrix.setElement(i, j, result);
                    }
                }
                return new MutableMatrix<String>(resultMatrix);
            } else if (element1 instanceof String && element2 instanceof String) {
                MutableMatrix<String> resultMatrix = new MutableMatrix<String>(dim2[0], dim2[1]);
                for (int i = 0; i < dim2[0]; i++) {
                    for (int j = 0; j < dim2[1]; j++) {
                        String e1 = (String) this.getElement(i, j);
                        String e2 = (String) secondMatrix.getElement(i, j);
                        String result = e1 + e2;
                        resultMatrix.setElement(i, j, result);
                    }
                }
                return new MutableMatrix<String>(resultMatrix);
            } else {
                System.out.println("Неможливо скласти матриці");
                return new MutableMatrix<T>();
            }
        }
    }

    public MutableMatrix multScalar(int scalar){
        int[] dim = getDimension();
        T element1 = this.getElement(0, 0);
        if (element1 instanceof Number) {
            MutableMatrix<Number> result = new MutableMatrix<Number>(dim[0], dim[1]);
            for (int i = 0; i < dim[0]; i++) {
                for (int j = 0; j < dim[1]; j++) {
                    Number el = (Number) getElement(i,j);
                    Number res = el.doubleValue() * scalar;
                    result.setElement(i,j, res);
                }
            }
            return result;
        }
        else if (element1 instanceof Boolean) {
            return this;
        }
        if (element1 instanceof String && scalar > 0) {
            MutableMatrix<String> result = new MutableMatrix<String>(dim[0], dim[1]);
            for (int i = 0; i < dim[0]; i++) {
                for (int j = 0; j < dim[1]; j++) {
                    String el = (String) getElement(i,j);
                    result.setElement(i,j, String.valueOf(el).repeat(scalar));
                }
            }
            return result;
        }
        else if(element1 instanceof String){
            System.out.println("Неможливо строку помножити на недодатне число");
            return this;
        }
        else if(element1 instanceof Character && scalar <= 0){
            System.out.println("Неможливо строку помножити на недодатне число");
            return this;
        }
        else if(element1 instanceof Character && scalar > 0){
            MutableMatrix<String> result = new MutableMatrix<String>(dim[0], dim[1]);
            for (int i = 0; i < dim[0]; i++) {
                for (int j = 0; j < dim[1]; j++) {
                    String el = getElement(i,j).toString();
                    result.setElement(i,j, String.valueOf(el).repeat(scalar));
                }
            }
            return result;
        }
        else {
            System.out.println("Множення неможливе");
            return this;
        }
    }
}

