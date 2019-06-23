package ru.nikitaboiko.testapp.services.parsingJson;

public class ImagesGiphy {
    private OriginalGiphy original;
    private Fixed_height fixed_height;

    public OriginalGiphy getOriginal() {
        return this.original;
    }

    public Fixed_height getFixed_height() {
        return this.fixed_height;
    }

    public void setOriginal(OriginalGiphy original) {
        this.original = original;
    }
}
