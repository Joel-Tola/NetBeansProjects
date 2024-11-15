/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

/**
 *
 * @author sljoe
 */

// Enums as per the provided code
enum MenuOption implements IMenuOptionInterface {
    ADD_EMPLOYEE(1, "Add Employee"),
    GENERATE_RANDOM_EMPLOYEE(2, "Generate Random Employees"),
    SORT(3, "Sorting"),
    SEARCH(4, "Searching"),
    EDIT_EMPLOYEE(5, "Edit Existing Employee"),
    EXIT(6, "Exit");

    private final int value;
    private final String stringValue;

    MenuOption(int value, String stringValue) {
        this.value = value;
        this.stringValue = stringValue;
    }

    public static MenuOption getValue(int value) {
        for (MenuOption option : values()) {
            if (option.value == value) {
                return option;
            }
        }
        return null;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getStringValue() {
        return stringValue;
    }
}

enum EmployeeCategory implements IMenuOptionInterface {
    MANAGER(1, "Manager"),
    STAFF(2, "Staff"),
    BACK(3, "Back");

    private final int value;
    private final String stringValue;

    EmployeeCategory(int value, String stringValue) {
        this.value = value;
        this.stringValue = stringValue;
    }

    public static EmployeeCategory getValue(int value) {
        for (EmployeeCategory option : values()) {
            if (option.value == value) {
                return option;
            }
        }
        return null;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getStringValue() {
        return stringValue;
    }
}

enum ManagerType implements IMenuOptionInterface {
    NURSING_MANAGER(1, "Nursing Manager"),
    CHIEF_MEDICAL_OFFICER(2, "Chief Medical Officer"),
    ADMINISTRATIVE_MANAGER(3, "Administrative Manager"),
    BACK(4, "Back");

    public final int value;
    public final String stringValue;

    ManagerType(int value, String stringValue) {
        this.value = value;
        this.stringValue = stringValue;
    }

    public static ManagerType getValue(int value) {
        for (ManagerType option : values()) {
            if (option.value == value) {
                return option;
            }
        }
        return null;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getStringValue() {
        return stringValue;
    }
}

enum EmployeeType implements IMenuOptionInterface {
    DOCTOR(1, "Doctor"),
    NURSE(2, "Nurse"),
    ADMINISTRATIVE_STAFF(3, "Administrative Staff"),
    BACK(4, "Back");

    public final int value;
    public final String stringValue;

    EmployeeType(int value, String stringValue) {
        this.value = value;
        this.stringValue = stringValue;
    }

    public static EmployeeType getValue(int value) {
        for (EmployeeType option : values()) {
            if (option.value == value) {
                return option;
            }
        }
        return null;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getStringValue() {
        return stringValue;
    }
}

enum DepartmentType implements IMenuOptionInterface {
    EMERGENCY(1, "Emergency"),
    PEDIATRICS(2, "Pediatrics"),
    CARDIOLOGY(3, "Cardiology"),
    BACK(4, "Back");

    private final int value;
    private final String stringValue;

    DepartmentType(int value, String stringValue) {
        this.value = value;
        this.stringValue = stringValue;
    }

    public static DepartmentType getValue(int value) {
        for (DepartmentType option : values()) {
            if (option.value == value) {
                return option;
            }
        }
        return null;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getStringValue() {
        return stringValue;
    }
}

enum EditOption implements IMenuOptionInterface {
    EDIT_NAME(1, "Edit Name"),
    EDIT_ROLE(2, "Edit Role"),
    EDIT_DEPARTMENT(3, "Edit Department"),
    FINISH_EDITING(4, "Finish Editing");

    private final int value;
    private final String stringValue;

    EditOption(int value, String stringValue) {
        this.value = value;
        this.stringValue = stringValue;
    }

    public static EditOption getValue(int value) {
        for (EditOption option : values()) {
            if (option.value == value) {
                return option;
            }
        }
        return null;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getStringValue() {
        return stringValue;
    }
}

enum GenerateEmployeeOption implements IMenuOptionInterface {
    BY_FILE(1, "By Text File"),
    BY_API(2, "By API"),
    BACK(3, "Back");

    private final int value;
    private final String stringValue;

    GenerateEmployeeOption(int value, String stringValue) {
        this.value = value;
        this.stringValue = stringValue;
    }

    public static GenerateEmployeeOption getValue(int value) {
        for (GenerateEmployeeOption option : values()) {
            if (option.value == value) {
                return option;
            }
        }
        return null;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getStringValue() {
        return stringValue;
    }
}