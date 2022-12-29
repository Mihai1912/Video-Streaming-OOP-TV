package pages;

import designpattern.visitorpattern.Visitor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class HomePageAuthentify extends Page {
    private String pageName;

    public HomePageAuthentify(final String pageName) {
        this.pageName = pageName;
    }

    @Override
    public String toString() {
        return "HomePageAuthentify{"
                + "pageName='" + pageName + '\''
                + '}';
    }

    @Override
    public boolean accept(final Visitor v, final String a) {
        return v.visit(this, a);
    }
}
