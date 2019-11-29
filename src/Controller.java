import java.nio.channels.SelectableChannel;

public class Controller {

    Model model;

    Controller(Model model){
        this.model = model;
    }

    public void _squareSelectCallback(Square square){
        model.prepareForSelection();
        square.setSelected(true);
    }

    public void _squareMoveCallback(Square square){
        System.out.println("Move Called");
        // Check if a piece is selected
        Square selected = model.getSelectedPiece();

        if (selected == null) { return; }

        // Get piece from square
        Piece piece = selected.piece;
        if (piece == null) { return; }

        // Try move piece
        square.setPiece(piece);
        selected.setPiece(null);
        model.prepareForSelection();
    }

}
