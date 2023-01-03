package pages.type;

import fileinput.*;
import helper.Helper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Database {
    private static Database instance = null;

    private Database() {
    }

    /**
     * @return instance of Database
     */
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void database(Input input, Action action, Helper helper) {
        if (action.getFeature().equals("add")) {

        } else {

        }
    }
}
