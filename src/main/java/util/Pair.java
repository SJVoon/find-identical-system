package util;

public class Pair<T> {
    private final T first;
    private final T second;

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Pair<?> other = (Pair<?>) obj;
        return (first.equals(other.first) && second.equals(other.second))
                || (first.equals(other.second) && second.equals(other.first));
    }

    @Override
    public int hashCode() {
        return first.hashCode() + second.hashCode();
    }
}
