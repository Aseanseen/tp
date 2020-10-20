package seedu.duke.data;

import seedu.duke.exception.DataNotFoundException;
import seedu.duke.exception.DuplicateDataException;
import seedu.duke.exception.ModuleNotProvidedException;
import seedu.duke.ui.TextUi;

import java.util.HashMap;

public class ModuleManager {
    private static HashMap<String, Module> modulesMap = new HashMap<>();
    // modulesMap is the main module list. Maps module code to module object.
    private static HashMap<String, Module> nusModsMap = new HashMap<>();
    // nusModsMap is the module list containing the Module objects created from NUSMods' JSON file of modules.

    /**
     *  Finds a module with the specified module code in the Module List.
     *
     * @param moduleCode
     *  The module code of the module to be found
     * @return
     *  The found module with the specified module code
     * @throws ModuleNotFoundException
     *  If the module is not found in the Module List
     */
    public static Module getModule(String moduleCode) throws ModuleNotFoundException {
        for (Module module : modulesMap.values()) {
            if (module.getModuleCode().equalsIgnoreCase(moduleCode)) {
                return module;
            }
        }
        throw new ModuleNotFoundException();
    }

    /**
     * Edits a module in the Module List by replacing the old module object with a new one.
     *
     * @param newModule
     *  The new module that replaces the old one.
     * @param oldModuleCode
     *  The module code of the module to be edited.
     * @throws ModuleNotProvidedException
     *  If there is no module with the new module code offered by NUS
     * @throws DuplicateModuleException
     *  If there are duplicate modules with the same module code as the new module code in the Module List
     */
    public static void edit(Module newModule, String oldModuleCode)
            throws ModuleNotProvidedException, DuplicateModuleException {
        //modulesMap.get(module.getCode()).setTitle(moduleDescription);
        Module oldModule = modulesMap.get(oldModuleCode);
        if (!modulesMap.containsKey(oldModuleCode)) {
            throw new ModuleNotProvidedException();
        }
        if (oldModule.isSameModule(newModule)) {
            throw new DuplicateModuleException();
        }
        modulesMap.remove(oldModuleCode);
        modulesMap.put(newModule.getModuleCode(), newModule);
    }

    /**
     * Checks for duplicates of the same module code in the Module List.
     * @param moduleCode
     *  The module code to check
     * @return
     *  <code>TRUE</code> if there exists a duplicate, and <code>FALSE</code> otherwise
     */
    public static boolean contains(String moduleCode) {
        for (String eachCode : modulesMap.keySet()) {
            if (eachCode.equalsIgnoreCase(moduleCode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a module to the Module List.
     * @param newModule
     *  The module object to add to the module list
     */
    public static void add(Module newModule) throws DuplicateModuleException, ModuleNotFoundException {
        if (contains(newModule.getModuleCode())) {
            throw new DuplicateModuleException();
        }
        Module verifiedNusMod = getNusModule(newModule.getModuleCode());
        newModule.setTitle(verifiedNusMod.getTitle());
        modulesMap.put(newModule.getModuleCode(), newModule);
    }

    /**
     * Removes a module from the Module List using the module code.
     * @param moduleCode
     *  The module code of the module to remove from the module list
     */
    public static boolean delete(String moduleCode) throws ModuleNotFoundException {
        if (!contains(moduleCode)) {
            throw new ModuleNotFoundException();
        }
        modulesMap.remove(moduleCode);
        return false;
    }

    /**
     * Adds a module to the NUSMods Module List.
     * @param newModule
     *  The module object to add to the module list
     */
    public static void addNusMod(Module newModule) throws DuplicateModuleException {
        if (contains(newModule.getModuleCode())) {
            throw new DuplicateModuleException();
        }
        nusModsMap.put(newModule.getModuleCode(), newModule);
    }

    /**
     *  Finds a module with the specified module code in the NUSMods Module List.
     *
     * @param moduleCode
     *  The module code of the module to be found
     * @return
     *  The found module with the specified module code
     * @throws ModuleNotFoundException
     *  If the module is not found in the Module List
     */
    public static Module getNusModule(String moduleCode) throws ModuleNotFoundException {
        for (Module module : nusModsMap.values()) {
            if (module.getModuleCode().equalsIgnoreCase(moduleCode)) {
                return module;
            }
        }
        throw new ModuleNotFoundException();
    }

    public static String[] getModCodeList() {
        String[] outputArray = modulesMap.keySet().toArray(new String[0]);
        return outputArray;
    }

    public static String[] getNusModCodeList() {
        String[] outputArray = nusModsMap.keySet().toArray(new String[0]);
        return outputArray;
    }

    /**
     * List modules in the module map.
     *
     * @return
     *  The formatted module list from TextUi or null if list is empty
     */
    public static String list() {
        if (modulesMap.size() > 0) {
            return TextUi.getIndexModuleList(modulesMap);
        } else {
            return null;
        }
    }

    /**
     * Loads the file loaded module map into ModuleManager's own module map.
     *
     * @param loadedModulesMap the loaded module map from file
     */
    public static void loadMods(HashMap<String, Module> loadedModulesMap) {
        modulesMap = loadedModulesMap;
    }

    /**
     * Loads the file loaded module map into ModuleManager's own module map.
     *
     * @param loadedModulesMap the loaded module map from file
     */
    public static void loadNusMods(HashMap<String, Module> loadedModulesMap) {
        nusModsMap = loadedModulesMap;
    }

    /**
     * Clears all modules in modulesMap.
     */
    public static void clearModules() {
        modulesMap = new HashMap<String, Module>();
    }

    /**
     * Returns the modules in the system.
     *
     * @return modulesMap
     */
    public static HashMap<String, Module> getModulesMap() {
        return modulesMap;
    }

    public static class ModuleNotFoundException extends DataNotFoundException {
    }

    public static class DuplicateModuleException extends DuplicateDataException {
    }
}
