import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class View {

    Model model;
    Controller controller;

    JFrame frame;
    JPanel board;

    public View(Model model, Controller controller){
        this.model = model;
        this.controller = controller;

        createWindow();
        frame.pack();
    }

    private void createWindow() {
        this.frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board = new JPanel();
        GridLayout layout = new GridLayout(Board.BOARD_SIZE, Board.BOARD_SIZE);
        board.setLayout(layout);
        drawSquares();
        frame.getContentPane().add(board);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }

    private void drawSquares() {
        for (int y = 0; y < Board.BOARD_SIZE; y++){

            for (int x = 0; x < Board.BOARD_SIZE; x++){
                System.out.println("Created Square");
                board.add(new SquareView(model.board.squares[y][x], controller, this));
            }
        }
    }

    public void redrawSquares() {
        board.removeAll();
        board.revalidate();
        drawSquares();
        frame.repaint();
    }

}

class SquareView extends JComponent implements MouseListener {

    Controller controller;

    static int WIDTH = 80;
    static int HEIGHT = 80;
    private Square square;
    private Piece piece;

    private View view;

    public SquareView(Square square, Controller controller, View v){
        this.controller = controller;
        this.square = square;
        this.piece = square.piece;
        this.addMouseListener(this);
        this.view = v;
    }

    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (!square.isWhite){
            g2d.setColor(new Color(0, 0, 0));
            g2d.drawRect(0, 0, WIDTH, HEIGHT);

            g2d.setColor(new Color(148, 101, 71));

        } else {
            g2d.setColor(new Color(0, 0, 0));
            g2d.drawRect(0, 0, WIDTH, HEIGHT);

            g2d.setColor(new Color(225, 206, 168));
        }

        g2d.fillRect(0, 0, WIDTH - 1, HEIGHT - 1);

        BufferedImage image = null;

        if (piece != null) {
            String playerText = "";
            if (piece.belongsTo.isWhite) {
                playerText = "white";
            } else {
                playerText = "black";
            }
            switch (piece.type) {

                case Rook:
                    try {
                        image = ImageIO.read(new File("C:\\dev\\Chess\\res\\rook_" + playerText + ".png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case Knight:
                    try {
                        image = ImageIO.read(new File("C:\\dev\\Chess\\res\\knight_" + playerText + ".png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case Bishop:
                    try {
                        image = ImageIO.read(new File("C:\\dev\\Chess\\res\\bishop_" + playerText + ".png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case Pawn:
                    try {
                        image = ImageIO.read(new File("C:\\dev\\Chess\\res\\pawn_" + playerText + ".png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case Queen:
                    try {
                        image = ImageIO.read(new File("C:\\dev\\Chess\\res\\queen_" + playerText + ".png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case King:
                    try {
                        image = ImageIO.read(new File("C:\\dev\\Chess\\res\\king_" + playerText + ".png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }

            g2d.drawImage(image, 0, 0, null);

            if (square.isSelected()){
                g2d.setColor(new Color(255, 255, 0, 50));
                g2d.fillRect(0, 0, WIDTH, HEIGHT);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (SwingUtilities.isLeftMouseButton(mouseEvent)){
            controller._squareSelectCallback(square);
            view.frame.repaint();
        } else if (SwingUtilities.isRightMouseButton(mouseEvent)){
            controller._squareMoveCallback(square);
            view.redrawSquares();
            view.frame.repaint();
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}

class PieceView extends JComponent {

    private Piece piece;

    PieceView(Piece piece){
        this.piece = piece;
    }

    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        String text = "NaN";

        switch (piece.type){

            case Pawn:
                text = "P";
                break;
            case Knight:
                text = "N";
                break;
            case Bishop:
                text = "B";
                break;
            case Rook:
                text = "R";
                break;
            case Queen:
                text = "Q";
                break;
            case King:
                text = "K";
                break;
        }

        //g2d.drawString(text, 0, 0);
        g2d.setColor(Color.RED);
        g2d.fillRect(0, 0, 100, 100);
    }

}
