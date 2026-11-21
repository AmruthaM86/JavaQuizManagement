#include <stdio.h>
#include <omp.h>

#define MAX 1000

int main()
{
    int rows, cols, vector_size;
    int i, j, v1 = 1;
    double matrix[MAX][MAX];
    double vector[MAX];
    double serial_result[MAX];
    double parallel_result[MAX];

    printf("Enter number of rows and columns: ");
    scanf("%d %d", &rows, &cols);

    printf("Enter vector size: ");
    scanf("%d", &vector_size);

    if (cols != vector_size)
    {
        printf("\nMatrix-vector multiplication is not possible.\n");
        printf("Number of matrix columns (%d) must be equal to vector size (%d).\n", cols, vector_size);
        return 0;
    }

    if (rows > MAX || cols > MAX || vector_size > MAX)
    {
        printf("\nMaximum allowed size is %d.\n", MAX);
        return 0;
    }

    for (i = 0; i < rows; i++)
    {
        for (j = 0; j < cols; j++)
        {
            matrix[i][j] = v1++;
        }
    }

    for (i = 0; i < vector_size; i++)
    {
        vector[i] = 1;
    }

    double start = omp_get_wtime();

    for (i = 0; i < rows; i++)
    {
        serial_result[i] = 0;
        for (j = 0; j < cols; j++)
        {
            serial_result[i] += matrix[i][j] * vector[j];
        }
    }

    double end = omp_get_wtime();
    double serial_time = end - start;

    start = omp_get_wtime();

    #pragma omp parallel for private(j)
    for (i = 0; i < rows; i++)
    {
        parallel_result[i] = 0;
        for (j = 0; j < cols; j++)
        {
            parallel_result[i] += matrix[i][j] * vector[j];
        }
    }

    end = omp_get_wtime();
    double parallel_time = end - start;

    printf("\nMatrix (first row, maximum 20 elements):\n");
    for (i = 0; i < 1; i++)
    {
        for (j = 0; j < 20 && j < cols; j++)
        {
            printf("%6.1f", matrix[i][j]);
        }
        printf("\n");
    }

    printf("\nVector (maximum 10 elements):\n");
    for (i = 0; i < 10 && i < vector_size; i++)
    {
        printf("%6.1f\n", vector[i]);
    }

    printf("\nSerial Result:\n");
    for (i = 0; i < rows; i++)
    {
        printf("%10.1f", serial_result[i]);
    }

    printf("\n\nParallel Result:\n");
    for (i = 0; i < rows; i++)
    {
        printf("%10.1f", parallel_result[i]);
    }

    printf("\n\nSerial Execution Time: %f seconds", serial_time);
    printf("\nParallel Execution Time: %f seconds\n", parallel_time);

    return 0;
}