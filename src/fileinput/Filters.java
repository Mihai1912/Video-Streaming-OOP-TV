package fileinput;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public final class Filters {
    private Sort sort;
    private Contains contains;

    public Filters(final Sort sort, final Contains contains) {
        this.sort = sort;
        this.contains = contains;
    }

    public Filters() {
    }

    @Override
    public String toString() {
        return "Filters{"
                + "sort=" + sort
                + ", contains=" + contains + '}';
    }
}
