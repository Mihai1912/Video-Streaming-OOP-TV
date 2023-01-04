package fileinput;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public final class Action {
    private String type;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String page;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String feature;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer count;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String movie;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Credentials credentials;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String startsWith;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Filters filters;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String objectType;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer rate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String subscribedGenre;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Movie addedMovie;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String deletedMovie;

    public Action() {
    }

    @Override
    public String toString() {
        return "Action{"
                + "type='" + type + '\''
                + ", page='" + page + '\''
                + ", feature='" + feature + '\''
                + ", count=" + count
                + ", movie='" + movie + '\''
                + ", credentials=" + credentials
                + ", startsWith='" + startsWith + '\''
                + ", filters=" + filters + '}';
    }
}
