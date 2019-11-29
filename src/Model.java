import java.util.ArrayList;

public class Model {

    Board board;

    public Model(){
        this.board = new Board();
    }

    public void prepareForSelection(){
        for (int y = 0; y < Board.BOARD_SIZE; y++){

            for (int x = 0; x < Board.BOARD_SIZE; x++) {
                board.squares[y][x].setSelected(false);
            }
        }
    }

    public Square getSelectedPiece(){
        for (int y = 0; y < Board.BOARD_SIZE; y++){

            for (int x = 0; x < Board.BOARD_SIZE; x++) {
                if (board.squares[y][x].isSelected()){
                    return board.squares[y][x];
                }
            }
        }

        return null;
    }

}

class Board {

    static Integer BOARD_SIZE = 8;

    Square[][] squares = new Square[BOARD_SIZE][BOARD_SIZE];

    Player playerOne;
    Player playerTwo;

    Board(){
        this.playerOne = new Player(true);
        this.playerTwo = new Player(false);

        boolean isWhite = false;

        for (int y = 0; y < BOARD_SIZE; y++){

            for (int x = 0; x < BOARD_SIZE; x++){
                squares[y][x] = new Square(isWhite);

                if (y == 1) {
                    squares[y][x].piece = new Piece(playerTwo, Piece.Type.Pawn);
                }

                if (y == 0 && x == 0){
                    squares[y][x].piece = new Piece(playerTwo, Piece.Type.Rook);
                }
                else if (y == 0 && x == 1){
                    squares[y][x].piece = new Piece(playerTwo, Piece.Type.Knight);
                }
                else if (y == 0 && x == 2){
                    squares[y][x].piece = new Piece(playerTwo, Piece.Type.Bishop);
                }
                else if (y == 0 && x == 3){
                    squares[y][x].piece = new Piece(playerTwo, Piece.Type.Queen);
                }
                else if (y == 0 && x == 4){
                    squares[y][x].piece = new Piece(playerTwo, Piece.Type.King);
                }
                else if (y == 0 && x == 5){
                    squares[y][x].piece = new Piece(playerTwo, Piece.Type.Bishop);
                }
                else if (y == 0 && x == 6){
                    squares[y][x].piece = new Piece(playerTwo, Piece.Type.Knight);
                }
                else if (y == 0 && x == 7){
                    squares[y][x].piece = new Piece(playerTwo, Piece.Type.Rook);
                }

                if (y == 6){
                    squares[y][x].piece = new Piece(playerOne, Piece.Type.Pawn);
                }
                if (y == 7 && x == 0){
                    squares[y][x].piece = new Piece(playerOne, Piece.Type.Rook);
                }
                else if (y == 7 && x == 1){
                    squares[y][x].piece = new Piece(playerOne, Piece.Type.Knight);
                }
                else if (y == 7 && x == 2){
                    squares[y][x].piece = new Piece(playerOne, Piece.Type.Bishop);
                }
                else if (y == 7 && x == 3){
                    squares[y][x].piece = new Piece(playerOne, Piece.Type.Queen);
                }
                else if (y == 7 && x == 4){
                    squares[y][x].piece = new Piece(playerOne, Piece.Type.King);
                }
                else if (y == 7 && x == 5){
                    squares[y][x].piece = new Piece(playerOne, Piece.Type.Bishop);
                }
                else if (y == 7 && x == 6){
                    squares[y][x].piece = new Piece(playerOne, Piece.Type.Knight);
                }
                else if (y == 7 && x == 7){
                    squares[y][x].piece = new Piece(playerOne, Piece.Type.Rook);
                }
                isWhite = !isWhite;
            }
            isWhite = !isWhite;
        }
    }

}

class Square {

    Piece piece;
    boolean isWhite;
    boolean isSelected = false;

    Square(boolean isWhite){
        this.isWhite = isWhite;

    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

class Player {

    ArrayList<Piece> pieceArrayList;
    public boolean isWhite;

    Player(boolean isWhite){
        this.isWhite = isWhite;

        pieceArrayList = new ArrayList<>();
        pieceArrayList.add(new Piece(this, Piece.Type.Pawn));
    }

}

class Piece {

    enum Type {
        Pawn,
        Knight,
        Bishop,
        Rook,
        Queen,
        King
    }

    Player belongsTo;
    Type type;

    Piece(Player belongsTo, Type type){
        this.belongsTo = belongsTo;
        this.type = type;
    }

    public Player getBelongsTo() {
        return belongsTo;
    }
}