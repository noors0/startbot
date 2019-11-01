package pl.noors.startbot;

abstract class Loader<T> {

    private T value = this.load();

    T getValue() {
        return this.value;
    }

    abstract T load();
}
