public class Animal {
    public String makeNoise() {
        return "*Intense animal noise*";
    }
}

class Lion extends Animal {
    @Override
    public String makeNoise() {
        return "*Intense lion noise*";
    }
}
