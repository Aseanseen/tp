package seedu.duke.command.delete;

import seedu.duke.command.CommandResult;
import seedu.duke.command.PromptType;
import seedu.duke.data.Task;
import seedu.duke.data.TaskManager;

import static seedu.duke.util.ExceptionMessage.MESSAGE_TASK_NOT_FOUND;
import static seedu.duke.util.Message.MESSAGE_DELETE_TASK_SUCCESS;

public class DeleteTaskCommand extends DeleteCommand {
    private final int taskId;
    public static final String FORMAT = DeleteCommand.COMMAND_WORD + " -t" + " <task_index>";
    public static final String HELP =   "Delete a task from the scheduler."
                                       + "\n\tFormat: " + FORMAT 
                                       + "\n\tExample usage: del -t 1\n\n";

    /**
     * Constructor to delete task from task list.
     *
     * @param taskId ID of the task to be deleted.
     */
    public DeleteTaskCommand(int taskId) {
        this.taskId = taskId;
        setPromptType(PromptType.EDIT);
    }

    /**
     * Deletes the task from the task list.
     *
     * @param taskId ID of the task to be deleted.
     * @throws TaskManager.TaskNotFoundException If the task is not found in the task list.
     */
    private Task deleteTask(int taskId) throws TaskManager.TaskNotFoundException {
        return TaskManager.delete(taskId);
    }

    /**
     * Executes the DeleteTaskCommand to delete the task from the task list.
     *
     * @return CommandResult containing acknowledgement of the delete or errors.
     */
    @Override
    public CommandResult execute() {
        String message;
        try {
            Task taskToDelete = deleteTask(taskId);
            message = String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete.toString());
        } catch (TaskManager.TaskNotFoundException e) {
            message = MESSAGE_TASK_NOT_FOUND;
        }
        return new CommandResult(message);
    }
}
