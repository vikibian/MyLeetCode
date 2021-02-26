package com.neu.leetcode.problems.array;

public class 单词搜索_0079 {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        int[][] flags = new int[m][n];
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (board[i][j] == word.charAt(0)){
                    flags[i][j] = 1;
                    boolean res = searchWord(board,word,i,j,0,flags);
                    if (res){
                        return true;
                    }
                    flags[i][j] = 0;
                }
            }
        }

        return false;
    }

    public boolean searchWord(char[][] board,String word,int row,int col,int index,int[][] flags){
        int m = board.length;
        int n = board[0].length;
        if (board[row][col] == word.charAt(index) && index == word.length()-1){
            return true;
        }

//        while (word.charAt(index) == board[row][col]){
//            char tar = word.charAt(index+1);
//            if (row>1 && tar == board[row-1][col]){
//                row--;
//                index++;
//            } else if (col+1<n && tar == board[row][col+1]){
//                col++;
//                index++;
//            }
//
//
//        }


        if (row-1>=0&& flags[row-1][col] == 0 &&board[row-1][col] == word.charAt(index+1)){
            flags[row-1][col] = 1;
            if (searchWord(board,word,row-1,col,index+1,flags)){
                return true;
            }
            flags[row-1][col] = 0;
        }

        if (col+1<n && flags[row][col+1]==0 && board[row][col+1] == word.charAt(index+1)){
            flags[row][col+1] = 1;
            if (searchWord(board,word,row,col+1,index+1,flags)){
                return true;
            }
            flags[row][col+1] = 0;
        }

        if (row+1<m && flags[row+1][col] == 0 && board[row+1][col] == word.charAt(index+1)){
            flags[row+1][col] = 1;
            if (searchWord(board,word,row+1,col,index+1,flags)){
                return true;
            }
            flags[row+1][col] = 0;
        }

        if (col-1>=0&& flags[row][col-1]==0 && board[row][col-1] == word.charAt(index+1)){
            flags[row][col-1] = 1;
            if (searchWord(board,word,row,col-1,index+1,flags)){
                return true;
            }
            flags[row][col-1] = 0;
        }

        return false;
    }

    //官方题解 深度优先搜索
    public boolean exist1(char[][] board, String word) {
        int h = board.length,w=board[0].length;
        boolean[][] visited = new boolean[h][w];
        for (int i=0;i<h;i++){
            for (int j=0;j<w;j++){
                boolean flag = check(board,visited,i,j,word,0);
                if (flag){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean check(char[][] board, boolean[][] visited, int i, int j, String word, int k) {
        if (board[i][j] != word.charAt(k)){
            return false;
        } else if (k == word.length()-1){
            return true;
        }
        visited[i][j] = true;
        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
        boolean result = false;
        for (int[] dir : directions){
            int newi = i+dir[0],newj = j+dir[1];
            if (newi>=0 && newi<board.length && newj>=0 && newj<board[0].length){
                if (!visited[newi][newj]){
                    boolean flag = check(board,visited,newi,newj,word,k+1);
                    if (flag){
                        result = true;
                    }
                }
            }
        }
        visited[i][j] = false;
        return result;
    }
}
