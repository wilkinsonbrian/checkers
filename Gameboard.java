class Gameboard {
  
  public char[][] board;
  
  public Gameboard(boolean setup) {
    board = new char[8][8];
    // create an empty gameboard
    if (!setup) {
      for (int x = 0; x < 8; x++) {
        for (int y = 0; y < 8; y++) {
          setPiece(x, y, '-');
        }
      }
    } 
    // set up a gameboard with red and white pieces in the correct starting places
    else {
      for (int i = 0; i < 8; i+=2) {
        setPiece(0, i, '-');
        setPiece(0, i+1, 'r');
      }
      
      for (int i = 0; i < 8; i+=2) {
        setPiece(1, i, 'r');
        setPiece(1, i+1, '-');
      }
      
      for (int i = 0; i < 8; i+=2) {
        setPiece(2, i, '-');
        setPiece(2, i+1, 'r');
      }
      
      for (int i = 0; i < 8; i++) {
        setPiece(3, i, '-');
        setPiece(4, i, '-');
      }
      
      for (int i = 0; i < 8; i+=2) {
        setPiece(5, i, 'w');
        setPiece(5, i+1, '-');
      }
      
      for (int i = 0; i < 8; i+=2) {
        setPiece(6, i, '-');
        setPiece(6, i+1, 'w');
      }
      
      for (int i = 0; i < 8; i+=2) {
        setPiece(7, i, 'w');
        setPiece(7, i+1, '-');
      }
    }
  }
  
  private void setPiece(int x, int y, char color) {
    board[x][y] = color;
  }
  
  public String toString() {
    String checkerboard = "";
    
    for (int x = 0; x < 8; x++) {
      for (int y = 0; y < 8; y++) {
        checkerboard += board[x][y] + " ";
      }
      checkerboard += "\n";
    }
    return checkerboard;
  }
  
  public boolean move(int x, int y, boolean left) {
    boolean isRed = board[x][y] == 'r';
    boolean isWhite = board[x][y] == 'w';
    int nextX;
    int nextY;
    
    if (jump(x, y)) {
      return true;
    } 
    
    // The current space is occupied by a white piece and is trying to move up and 
    // to the left
    else if (isWhite && left) {
      nextX = x - 1;
      nextY = y - 1;
      // Is the piece within the board, but not in the first column
      if (moveOnBoard(nextX, nextY) && board[nextX][nextY] == '-') {
        setPiece(x, y, '-');
        setPiece(nextX, nextY, 'w');
        return true;
      } 
      
    }
    // The current space is occupied by a white piece and is trying to move up and 
    // to the right
    else if (isWhite && !left) {
      nextX = x - 1;
      nextY = y + 1;
      if (moveOnBoard(nextX, nextY) && board[nextX][nextY] == '-') {
        setPiece(x, y, '-');
        setPiece(nextX, nextY, 'w');
        return true;
      } 
      
    }
    
    // The current space is occupied by a red piece and is trying to move down and 
    // to the right
    else if (isRed && left) {
      nextX = x + 1;
      nextY = y + 1;
      if (moveOnBoard(nextX, nextY) && board[nextX][nextY] == '-') {
        setPiece(x, y, '-');
        setPiece(nextX, nextY, 'r');
        return true;
      } 
      
    }
    
    // The current space is occupied by a red piece and is trying to move down and 
    // to the left
    else if (isRed && !left) {
      nextX = x + 1;
      nextY = y - 1;
      if (moveOnBoard(nextX, nextY) && board[nextX][nextY] == '-') {
        setPiece(x, y, '-');
        setPiece(nextX, nextY, 'r');
        return true;
      } 
      
    }
    
    return false;
    
  }
  
  
  boolean jump(int x, int y) {
    boolean isRed = board[x][y] == 'r';
    boolean isWhite = board[x][y] == 'w';
    boolean jumped = false;
    int nextX;
    int nextY;
    if (isWhite) {
      // Testing jumping left
      nextX = x - 2;
      nextY = y - 2;
      if (moveOnBoard(nextX, nextY) && board[x-1][y-1] == 'r' && board[nextX][nextY] == '-') {
        board[x][y] = '-';
        board[x-1][y-1] = '-';
        board[nextX][nextY] = 'w';
        jumped = true;
        jump(nextX, nextY);
      }
      //Jumping up and to the right
      nextX = x - 2;
      nextY = y + 2;
      if (moveOnBoard(nextX, nextY) && board[x-1][y+1] == 'r' && board[nextX][nextY] == '-') {
        board[x][y] = '-';
        board[x-1][y+1] = '-';
        board[nextX][nextY] = 'w';
        jumped = true;
        jump(nextX, nextY);
      }
      
      
      
    }
    
    else if (isRed) {
      // Testing jumping left (down and to the right for red)
      nextX = x + 2;
      nextY = y + 2;
      if (moveOnBoard(nextX, nextY) && board[x+1][y+1] == 'w' && board[nextX][nextY] == '-') {
        board[x][y] = '-';
        board[x+1][y+1] = '-';
        board[nextX][nextY] = 'r';
        jumped = true;
        jump(nextX, nextY);
      }
      
      // Testing jumping right (down and to the left for red)
      nextX = x + 2;
      nextY = y - 2;
      if (moveOnBoard(nextX, nextY) && board[x+1][y-1] == 'w' && board[nextX][nextY] == '-') {
        board[x][y] = '-';
        board[x+1][y-1] = '-';
        board[nextX][nextY] = 'r';
        jumped = true;
        jump(nextX, nextY);
      }
    }
    
    return jumped;
  }
  
  boolean kingMe(int x, int y) {
    boolean isRed = board[x][y] == 'r';
    boolean isWhite = board[x][y] == 'w';
    // Check to see if a white piece has made it to the opposite row (0)
    if (isWhite && x == 0 && y <=7) {
      board[x][y] = 'W';
      return true;
    }
    else if (isRed && x == 7 && y <=7) {
      board[x][y] = 'R';
      return true;
    }
    return false;
  }
  
  private boolean moveOnBoard(int x, int y) {
    return x >=0 && x <=7 && y>=0 && y<=7;
  }
  
  
}