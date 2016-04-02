package lb.util;

/**
 * Created by Leonti on 2016-04-01.
 */
public class Pair implements Comparable<Pair> {
    public int key;
    public double value;

    public Pair(int key, double value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int compareTo(Pair o) {
        if (this.value < o.value) {
            return -1;
        } else if (this.value > o.value) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pair) {
            return this.key == ((Pair) obj).key;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return key;
    }
}
