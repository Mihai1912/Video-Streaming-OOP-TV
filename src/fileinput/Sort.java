package fileinput;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public final class Sort {
    private String rating;
    private String duration;

    public Sort(final String rating, final String duration) {
        this.rating = rating;
        this.duration = duration;
    }

    public Sort() {
    }

    @Override
    public String toString() {
        return "Sort{"
                + "rating='" + rating + '\''
                + ", duration='" + duration + '\''
                + '}';
    }
}
