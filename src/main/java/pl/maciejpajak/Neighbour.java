package pl.maciejpajak;

public class Neighbour implements Comparable<Neighbour> {

    private final CifarData.ImagePointer pointer;
    private final int score;


    public Neighbour(CifarData.ImagePointer pointer, int score) {
        this.score = score;
        this.pointer = pointer;
    }

    public CifarData.ImagePointer getPointer() {
        return pointer;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(Neighbour o) {
        return (this.score > o.score) ? -1 : ((this.score == o.score) ? 0 : 1);
    }

    @Override
    public String toString() {
        return "Neighbour{" +
                "pointer=" + pointer +
                ", score=" + score +
                '}';
    }
}
