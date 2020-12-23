package util.helpers;

import java.util.Objects;

public class KeyPair<F,S> {

    private F first;
    private S second;

    public KeyPair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyPair<?, ?> keyPair = (KeyPair<?, ?>) o;
        return Objects.equals(first, keyPair.first) &&
                Objects.equals(second, keyPair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
