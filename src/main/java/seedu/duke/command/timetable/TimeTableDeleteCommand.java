package seedu.duke.command.timetable;

import seedu.duke.command.CommandResult;
import seedu.duke.command.PromptType;
import seedu.duke.data.Lesson;
import seedu.duke.data.TimeTableManager;

import java.time.DayOfWeek;

import static seedu.duke.util.ExceptionMessage.MESSAGE_LESSON_NOT_FOUND;
import static seedu.duke.util.Message.MESSAGE_DELETE_LESSON_SUCCESS;

public class TimeTableDeleteCommand extends TimeTableCommand {
    private final DayOfWeek dayOfWeek;
    private final int lessonIndexToDelete;

    public TimeTableDeleteCommand(DayOfWeek dayOfWeek, int lessonIndexToDelete) {
        this.dayOfWeek = dayOfWeek;
        this.lessonIndexToDelete = lessonIndexToDelete;
        setPromptType(PromptType.EDIT);
    }

    /**
     * Method runs during execution. Deletes the lesson from the timetable.
     *
     * @return
     *  The deleted lesson.
     * @throws IndexOutOfBoundsException
     *  When the index given by the user is out of bounds.
     */
    public Lesson removeLessonFromTimeTable() throws IndexOutOfBoundsException {
        return TimeTableManager.deleteLesson(dayOfWeek, lessonIndexToDelete);
    }

    @Override
    public CommandResult execute() {
        String message;
        try {
            Lesson lessonToDelete = removeLessonFromTimeTable();
            message = String.format(MESSAGE_DELETE_LESSON_SUCCESS, lessonToDelete.toString());
        } catch (IndexOutOfBoundsException e) {
            message = MESSAGE_LESSON_NOT_FOUND;
        }
        return new CommandResult(message);
    }
}
