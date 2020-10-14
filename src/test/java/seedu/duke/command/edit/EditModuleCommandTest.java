package seedu.duke.command.edit;

import org.junit.jupiter.api.Test;
import seedu.duke.Executor;
import seedu.duke.command.CommandResult;
import seedu.duke.data.Module;
import seedu.duke.data.ModuleManager;
import seedu.duke.data.storage.Decoder;
import seedu.duke.data.storage.InputOutputManager;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class EditModuleCommandTest {
    @Test
    void execute() throws ModuleManager.DuplicateModuleException {
        HashMap<String, String> modulesMap = Decoder.loadModules("data/" + "moduleList.json");
        //ModuleManager.add(new Module("CS1231", "AAA"));
        CommandResult result = Executor.executeCommand("edit -m cs1231 CS3224");
        System.out.println(result.feedbackToUser);
    }
}