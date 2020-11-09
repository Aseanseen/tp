//@@author tobiasceg

package seedu.ravi.command.cap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.ravi.command.CommandResult;
import seedu.ravi.command.add.AddCommand;
import seedu.ravi.command.add.AddModuleCommand;
import seedu.ravi.command.grade.GradeCommand;
import seedu.ravi.data.ModuleManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.ravi.util.Message.MESSAGE_CAP_DISPLAY;

public class CapCommandTest {
    static final double CAP = 4.37;

    @BeforeEach
    void setupModManager() {
        ModuleManager.clearModules();
        AddCommand addModule1 = new AddModuleCommand("CS1231");
        addModule1.execute();
        GradeCommand gradeCommand1 = new GradeCommand("CS1010", 4, "A+");
        gradeCommand1.execute();
        AddCommand addModule2 = new AddModuleCommand("CG2271");
        addModule2.execute();
        GradeCommand gradeCommand2 = new GradeCommand("ACC1002", 4, "B+");
        gradeCommand2.execute();
        AddCommand addModule3 = new AddModuleCommand("CS1010X");
        addModule3.execute();
        GradeCommand gradeCommand3 = new GradeCommand("CS1010R", 4, "C+");
        gradeCommand3.execute();
    }

    @Test
    void capCommand_MessageCapDisplayWithCap_isShown() {
        CapCommand capCommand = new CapCommand();
        CommandResult commandResult = capCommand.execute();
        assertEquals(String.format("%s%.2f\n", MESSAGE_CAP_DISPLAY, CAP), commandResult.feedbackToUser);
    }
}
