package seedu.duke.data.storage;

import seedu.duke.data.ModuleManager;
import seedu.duke.common.Constant;
import seedu.duke.data.TaskManager;
import seedu.duke.DukeLogger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import java.util.logging.Level;

/**
 * Manages all inputs and outputs (to and from files).
 * Encoder and Decoder are only used by InputOutputManager.
 * InputOutputManager also handles exceptions thrown by Encoder and Decoder. No exceptions are thrown from here.
 *
 * @author Sim Jun You
 */
public class InputOutputManager {
    static String root = System.getProperty("user.dir");
    static java.nio.file.Path dirPath = java.nio.file.Paths.get(root, "data");

    static String userModuleFileName = Constant.MOD_SAVE_FILE_NAME + Constant.FILE_EXT;
    static String userTaskFileName = Constant.TASK_SAVE_FILE_NAME + Constant.FILE_EXT;
    static String nusModuleFileName = Constant.NUSMOD_SAVE_FILE_NAME + Constant.FILE_EXT;
    static java.nio.file.Path userModuleFile = java.nio.file.Paths.get(String.valueOf(dirPath), userModuleFileName);
    static java.nio.file.Path userTaskFile = java.nio.file.Paths.get(String.valueOf(dirPath), userTaskFileName);
    static java.nio.file.Path nusModuleFile = java.nio.file.Paths.get(String.valueOf(dirPath), nusModuleFileName);

    private static final DukeLogger logger = new DukeLogger(InputOutputManager.class.getName());

    /**
     * Creates the save directory if it has not been created.
     * Loads the user's module and task saves into memory.
     */
    public static void start() {
        logger.getLogger().info("Starting InputOutputManager");
        File saveFolder = dirPath.toFile();
        if (!saveFolder.exists()) {
            logger.getLogger().info("Save folder does not exist, creating now");
            saveFolder.mkdir();
        } else {
            if (Files.exists(userModuleFile)) {
                logger.getLogger().info("Loading user module saves from " + userModuleFileName);
                ModuleManager.loadMods(Decoder.loadModules(userModuleFile.toString()));
            } else {
                logger.getLogger().info("Skipping user module save; file does not exist: " + userModuleFileName);
            }
            if (Files.exists(userTaskFile)) {
                logger.getLogger().info("Loading user task saves from " + userTaskFileName);
                TaskManager.loadTasks(Decoder.loadTasks(userTaskFile.toString()));
            } else {
                logger.getLogger().info("Skipping user task save; file does not exist: " + userTaskFileName);
            }
            loadNusModSave(); // will load from NUSMods API if file not found
        }
    }

    /**
     * Loads NUS Modules from the given file.
     */
    public static void loadNusModSave() {
        logger.getLogger().info("Loading NUS modules from " + nusModuleFileName);
        if (!Files.exists(nusModuleFile)) {
            ModuleManager.loadNusMods(Decoder.generateNusModsList());
        } else {
            ModuleManager.loadNusMods(Decoder.loadModules(nusModuleFile.toString()));
        }
    }

    /**
     * Updates the user's save files. Does not save NUS Modules.
     */
    public static void save() {
        logger.getLogger().info("Saving user modules and tasks into " + userModuleFileName
                + " and " + userTaskFileName);
        try {
            if (ModuleManager.getModCodeList().length != 0) {
                Encoder.saveModules(userModuleFile.toString());
            }
            if (TaskManager.getTaskCount() != 0) {
                Encoder.saveTasks(userTaskFile.toString());
            }
        } catch (ModuleManager.ModuleNotFoundException | TaskManager.TaskNotFoundException | IOException e) {
            logger.getLogger().log(Level.WARNING, e.getLocalizedMessage(), e);
        }
    }

    /**
     * Updates the user's NUS Modules save file.
     */
    public static void saveNusMods() {
        logger.getLogger().info("Saving NUS modules into " + nusModuleFileName);
        try {
            Encoder.saveNusModules(nusModuleFile.toString());
        } catch (ModuleManager.ModuleNotFoundException | IOException e) {
            logger.getLogger().log(Level.WARNING, e.getLocalizedMessage(), e);
        }
    }
}
