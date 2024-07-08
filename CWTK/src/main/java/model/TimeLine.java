package model;

import model.Card.Card;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class TimeLine implements Serializable {
    @Serial
    private static final long serialVersionUID = -3792908018842942216L;

    private ArrayList<ArrayList<CardPlaceHolder>> cards = new ArrayList<>();
    private final int len = 21;
    private int row1DestroyedCell;
    private int row2DestroyedCell;
    private Game game;


    public TimeLine(Game game) {
        this.game = game;
        Random random = new Random();

        cards.add(new ArrayList<>());
        cards.add(new ArrayList<>());

        row1DestroyedCell = random.nextInt(21);
        row2DestroyedCell = random.nextInt(21);

        for (int i=0; i<len; i++) {
            getRow1().add(null);
            getRow2().add(null);
        }

        getRow1().set(row1DestroyedCell, new DestroyedCell(null));
        getRow2().set(row2DestroyedCell, new DestroyedCell(null));
    }

    public ArrayList<CardPlaceHolder> getRow1() {
        return cards.get(0);
    }

    public ArrayList<CardPlaceHolder> getRow2() {
        return cards.get(1);
    }

    public void placeCard(Card card, int index, int row) {

        if (index >= 1 && index <= len) {
            for (int i=index-1; i<index-1+card.getDuration(); i++) {
                if (row == 1) {
                    if (i == row1DestroyedCell) {
                        return;
                    }
                }

                if (row == 2) {
                    if (i == row2DestroyedCell) {
                        return;
                    }
                }
            }


            for (int i=index-1; i<index-1+card.getDuration(); i++) {
                if (row == 1) {
                    if (getRow2().get(i) == null || getRow2().get(i).getClass() == DestroyedCell.class) {
                        getRow1().set(i, new CardPlaceHolder(card));

                        if (card.getCardType().equals(game.getUser1Character())) {
                            getRow1().get(i).buff();
                        }
                        game.setPlayer1Points(game.getPlayer1Points() + card.getPoint() / card.getDuration());
                        continue;
                    }

                    else {
                        if (card.getACC() > getRow2().get(i).getAcc()) {
                        getRow2().set(i, new DestroyedCell(null));
                        getRow1().set(i, new CardPlaceHolder(card));

                        if (card.getCardType().equals(game.getUser1Character())) {
                            getRow1().get(i).buff();
                        }
                        game.setPlayer1Points(game.getPlayer1Points() + card.getPoint() / card.getDuration());
                        continue;
                    }

                        if (card.getACC() == getRow2().get(i).getAcc()) {
                            getRow2().set(i, new DestroyedCell(null));
                            getRow1().set(i, new DestroyedCell(null));
                            continue;
                        }

                        else {
                            getRow1().set(i, new DestroyedCell(null));
                            game.setPlayer2Points(game.getPlayer2Points() - getRow2().get(i).getPoint());
                            continue;
                        }
                    }
                }

                if (row == 2) {
                    if (getRow1().get(i) == null || getRow1().get(i).getClass() == DestroyedCell.class) {
                        getRow2().set(i, new CardPlaceHolder(card));

                        if (card.getCardType().equals(game.getUser2Character())) {
                            getRow2().get(i).buff();
                        }
                        game.setPlayer2Points(game.getPlayer2Points() + card.getPoint() / card.getDuration());
                        continue;
                    }

                    else {
                        if (card.getACC() > getRow1().get(i).getAcc()) {
                            getRow1().set(i, new DestroyedCell(null));
                            getRow2().set(i, new CardPlaceHolder(card));

                            if (card.getCardType().equals(game.getUser2Character())) {
                                getRow2().get(i).buff();
                            }
                            game.setPlayer2Points(game.getPlayer2Points() + card.getPoint() / card.getDuration());
                            continue;
                        }

                        if (card.getACC() == getRow1().get(i).getAcc()) {
                            getRow2().set(i, new DestroyedCell(null));
                            getRow1().set(i, new DestroyedCell(null));
                            continue;
                        }

                        else {
                            getRow2().set(i, new DestroyedCell(null));
                            game.setPlayer1Points(game.getPlayer1Points() - getRow1().get(i).getPoint());
                            continue;
                        }
                    }
                }
            }
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("      ");

        for (int i=0; i<len; i++) {
            if (i < 9) {
                stringBuilder.append(i+1).append("       ");
            }

            else {
                stringBuilder.append(i+1).append("      ");
            }
        }

        stringBuilder.append("\n");
        stringBuilder.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        stringBuilder.append("\n");

        for (CardPlaceHolder i : getRow2()) {
            if (i == null) {
                stringBuilder.append("\t").append("--\t--");
            }

            else {
                stringBuilder.append("\t").append(i);
            }
        }

        stringBuilder.append("\n");
        stringBuilder.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        stringBuilder.append("\n");


        for (CardPlaceHolder i : getRow1()) {
            if (i == null) {
                stringBuilder.append("\t").append("--\t--");
            }

            else {
                stringBuilder.append("\t").append(i);
            }
        }

        stringBuilder.append("\n");
        stringBuilder.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        stringBuilder.append("\n");

        stringBuilder.append("      ");

        for (int i=0; i<len; i++) {
            if (i < 9) {
                stringBuilder.append(i+1).append("       ");
            }

            else {
                stringBuilder.append(i+1).append("      ");
            }
        }

        return stringBuilder.toString();
    }

    public boolean canBePlaced(int row, int duration, int index) {
        if (index < 1 || index > len) {
            return false;
        }

        if (index-1+duration > 20) {
            return false;
        }

        for (int i=index-1; i<index-1+duration; i++) {
            if (row == 1) {
                if (i == row1DestroyedCell) {
                    return false;
                }

                if (getRow1().get(i) != null) {
                    if (getRow1().get(i).getClass() == DestroyedCell.class) {
                        return false;
                    }
                }

                if (getRow1().get(i) != null && getRow1().get(i).getClass() != DestroyedCell.class) {
                    return false;
                }
            }

            if (row == 2) {
                if (i == row2DestroyedCell) {
                    return false;
                }

                if (getRow2().get(i) != null) {
                    if (getRow2().get(i).getClass() == DestroyedCell.class) {
                        return false;
                    }
                }

                if (getRow2().get(i) != null && getRow2().get(i).getClass() != DestroyedCell.class) {
                    return false;
                }
            }
        }

        return true;
    }

    public void destroyCell(int index, int row) {
        if (index > 0 && index < len) {
            if (row == 1) {
                getRow1().set(index-1, new DestroyedCell(null));
            }

            if (row == 2) {
                getRow2().set(index-1, new DestroyedCell(null));
            }
        }

    }

    public void processCell(int index) {
        if (getRow1().get(index-1) != null && getRow1().get(index-1).getClass() != DestroyedCell.class) {
            CardPlaceHolder card = getRow1().get(index-1);

            game.setPlayer2HP(game.getPlayer2HP() - card.getPoint());
            game.setPlayer1Points(game.getPlayer1Points() - card.getPoint());
        }

        if (getRow2().get(index-1) != null && getRow2().get(index-1).getClass() != DestroyedCell.class) {
            CardPlaceHolder card = getRow2().get(index-1);

            game.setPlayer1HP(game.getPlayer1HP() - card.getPoint());
            game.setPlayer2Points(game.getPlayer2Points() - card.getPoint());
        }
    }

    public void clear() {
        for (int i=0; i<len; i++) {
            getRow1().set(i, null);
            getRow2().set(i, null);
        }

        Random random = new Random();

        row1DestroyedCell = random.nextInt(21);
        row2DestroyedCell = random.nextInt(21);

        getRow1().set(row1DestroyedCell, new DestroyedCell(null));
        getRow2().set(row2DestroyedCell, new DestroyedCell(null));
    }


}
