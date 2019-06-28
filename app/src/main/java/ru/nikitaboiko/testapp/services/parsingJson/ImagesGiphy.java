package ru.nikitaboiko.testapp.services.parsingJson;

public class ImagesGiphy {
    private OriginalGiphy original;
    private FixedHeight fixedHeight;

    public OriginalGiphy getOriginal() {
        return this.original;
    }

    public FixedHeight getFixedHeight() {
        return this.fixedHeight;
    }

    public void setOriginal(OriginalGiphy original) {
        this.original = original;
    }
}
