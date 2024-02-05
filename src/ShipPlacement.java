public interface ShipPlacement {
    boolean placeShip(int row, int col, char[][] board, boolean isHorizontal);
    boolean isValidPlacement(int row, int col, char[][] board, boolean isHorizontal);
}