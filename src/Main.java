import actionhandler.ActionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileinput.Action;
import fileinput.Input;
import helper.Helper;

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
        System.out.println("========" + pathToInputFile);
        System.out.println("========" + resutlFile);

        ObjectMapper objectMapper = new ObjectMapper();
        Input input = objectMapper.readValue(new File(pathToInputFile), Input.class);
        ArrayNode output = objectMapper.createArrayNode();

        ArrayList<Action> actions = new ArrayList<>(input.getActions());

        ActionHandler actionHandler = new ActionHandler();
        Helper helper = new Helper();
        helper.setCurrentPage("homePageUnauthentify");
        helper.setOutput(output);
        helper.getUsers().addAll(input.getUsers());


        for (Action action : actions) {
            actionHandler.doAction(input, action, helper);
        }

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(resutlFile), output);
    }
}
