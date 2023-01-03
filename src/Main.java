import actionhandler.ActionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileinput.Action;
import fileinput.Input;
import fileinput.Notification;
import fileinput.User;
import helper.Helper;
import recommendation.Recommendation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException {
        String pathToInputFile = args[0];
        String resutlFile = args[1];
        String viewOutputPath = pathToInputFile.replaceAll("in" , "results");


        ObjectMapper objectMapper = new ObjectMapper();
        Input input = objectMapper.readValue(new File(pathToInputFile), Input.class);
        ArrayNode output = objectMapper.createArrayNode();

        ArrayList<Action> actions = new ArrayList<>(input.getActions());

        ActionHandler actionHandler = new ActionHandler();
        Helper helper = new Helper();
        helper.setCurrentPage("homePageUnauthentify");
        helper.setOutput(output);
        for (User user : input.getUsers()) {
            user.setLikedMovies(new ArrayList<>());
            user.setRatedMovies(new ArrayList<>());
            user.setWatchedMovies(new ArrayList<>());
            user.setPurchasedMovies(new ArrayList<>());
            user.setTokensCount(0);
            user.setNumFreePremiumMovies(15);
            user.setMoviesToWatch(new ArrayList<>());
            user.setNotifications(new ArrayList<Notification>());
            user.setSubscriptions(new ArrayList<String>());
            helper.getUsers().add(user);
        }

        for (Action action : actions) {
            actionHandler.doAction(input, action, helper);
        }

//        if (helper.getCurrentUser() != null
//                && helper.getCurrentUser().getCredentials().getAccountType().equals("premium")) {
//            Recommendation recommendation = new Recommendation();
//            recommendation.giveRecommendation(helper , input.getMovies());
//        }

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(resutlFile), output);
        objectWriter.writeValue(new File(viewOutputPath), output);
    }
}
