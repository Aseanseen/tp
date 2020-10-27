package seedu.duke.command.grade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.CommandResult;
import seedu.duke.command.add.AddCommand;
import seedu.duke.command.add.AddModuleCommand;
import seedu.duke.data.ModuleManager;
import seedu.duke.util.ExceptionMessage;
import seedu.duke.util.Message;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GradeCommandTest {
    static final String MODULE_CODE = "CG2271";
    static final String NON_EXISTENT_MODULE_CODE = "CS2101";

    @BeforeEach
    void setupModObjects() {
        ModuleManager.clearModules();
        AddCommand addModule = new AddModuleCommand(MODULE_CODE);
        addModule.execute();
    }

    @Test
    void GradeCommand_MessageGradeSuccessful_isShown() {
        GradeCommand gradeCommand = new GradeCommand(MODULE_CODE,4,"A+");
        CommandResult commandResult = gradeCommand.execute();
        assertEquals(Message.MESSAGE_GRADE_MODULE_SUCCESS, commandResult.feedbackToUser);
    }

    @Test
    void GradeCommand_MessageModuleNotFound_isShown() {
        GradeCommand gradeCommand = new GradeCommand(NON_EXISTENT_MODULE_CODE, 4, "A+");
        CommandResult commandResult = gradeCommand.execute();
        assertEquals(ExceptionMessage.MESSAGE_MODULE_NOT_FOUND, commandResult.feedbackToUser);
    }

    @Test
    void GradeCommand_MessageInvalidGrade_isShown() {
        GradeCommand gradeCommand = new GradeCommand(MODULE_CODE, 4, "SU bah");
        CommandResult commandResult = gradeCommand.execute();
        assertEquals(ExceptionMessage.MESSAGE_INVALID_GRADE, commandResult.feedbackToUser);
    }
}
