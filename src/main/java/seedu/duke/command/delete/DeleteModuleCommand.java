package seedu.duke.command.delete;

import seedu.duke.command.CommandResult;
import seedu.duke.command.PromptType;
import seedu.duke.data.Module;
import seedu.duke.data.ModuleManager;
import seedu.duke.exception.ModuleNotFoundException;

import static seedu.duke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.duke.util.Message.MESSAGE_DELETE_MODULE_SUCCESS;

public class DeleteModuleCommand extends DeleteCommand {
    private final String moduleCode;
    public static final String FORMAT = DeleteCommand.COMMAND_WORD + " -m" + " <module_code>";
    public static final String HELP =   "Delete a module from the scheduler."
                                        + "\n\tFormat: " + FORMAT
                                        + "\n\tExample usage: del -m CS2113T";

    /**
     * Constructor to delete module from module list.
     *
     * @param moduleCode Module code to be deleted.
     */
    public DeleteModuleCommand(String moduleCode) {
        this.moduleCode = moduleCode.toUpperCase();
        setPromptType(PromptType.EDIT);
    }

    /**
     * Deletes the module from the module list.
     *
     * @param moduleCode Module code to be deleted.
     * @throws ModuleNotFoundException If the module is not found in the module list.
     */
    private Module deleteModule(String moduleCode) throws ModuleNotFoundException {
        ModuleManager.delete(moduleCode);
        return ModuleManager.getModule(moduleCode);
    }

    /**
     * Executes the DeleteModuleCommand to delete the module from the module list.
     *
     * @return CommandResult containing acknowledgement of the delete or errors.
     */
    @Override
    public CommandResult execute() {
        String message;
        try {
            Module moduleToDelete = deleteModule(moduleCode);
            message = String.format(MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete.toString());
        } catch (ModuleNotFoundException e) {
            message = MESSAGE_MODULE_NOT_FOUND;
        }
        return new CommandResult(message);
    }
}
