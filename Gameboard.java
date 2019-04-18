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
  
}