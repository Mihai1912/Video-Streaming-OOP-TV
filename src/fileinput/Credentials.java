package fileinput;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public final class Credentials {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String accountType;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String country;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String balance;

    public Credentials() {
    }

    public Credentials(final String name, final String password, final String accountType,
                       final String country, final String balance) {
        this.name = name;
        this.password = password;
        this.accountType = accountType;
        this.country = country;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Credentials{"
                + "name='" + name + '\''
                + ", password='" + password + '\''
                + ", accountType='" + accountType + '\''
                + ", country='" + country + '\''
                + ", balance=" + balance + '}';
    }
}
