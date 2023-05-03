# Sudoku Solver
Uses backtracking algorithms to solve a Sudoku puzzle. 

## Usage
Simply enter the board as 9 lines of text, with each line containing 9 numbers seperated by spaces. Represent unknown cells with zeroes. Here is an example of an input (generated using ChatGPT):

```
0 0 6 1 5 0 0 0 0
0 0 0 0 9 6 5 0 1
5 0 0 0 0 0 0 0 6
2 0 0 0 0 5 0 0 0
0 1 5 0 0 0 0 6 0
0 0 0 0 0 0 0 0 0
0 0 0 0 2 0 0 0 0
0 0 0 8 0 0 0 0 0
0 0 0 0 0 0 0 0 0
```

And here is the algorithm's solved puzzle:

```
4 7 8 2 9 6 5 3 1 
5 9 1 3 7 8 2 4 6 
2 3 4 6 1 5 8 9 7 
7 1 5 4 8 9 3 6 2 
6 8 9 7 3 2 1 5 4 
8 4 3 9 2 1 6 7 5 
9 5 2 8 6 7 4 1 3 
1 6 7 5 4 3 9 2 8
```
