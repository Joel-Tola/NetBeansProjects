package CA_2_Joel;

enum MenuOption {
    SORT(1, "Sorting"),
    SEARCH(2, "Searching"),
    ADD_EMPLOYEE(3, "Add Employee"),
    GENERATE_RANDOM_EMPLOYEE(4, "Generate Random Employee"),
    EXIT(5, "Exit");

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

    public static String getStringValue(int value) {
        for (MenuOption option : values()) {
            if (option.value == value) {
                return option.stringValue;
            }
        }
        return null;
    }
}

enum ManagerType {
    NURSING_MANAGER(1, "Nursing Manager"),
    CHIEF_MEDICAL_OFFICER(2, "Chief Medical Officer"),
    ADMINISTRATIVE_MANAGER(3, "Administrative Manager"),
    BACK(4, "Back");

    private final int value;
    private final String stringValue;

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

    public static String getStringValue(int value) {
        for (ManagerType option : values()) {
            if (option.value == value) {
                return option.stringValue;
            }
        }
        return null;
    }
}

enum EmployeeType {
    DOCTOR(1, "Doctor"),
    NURSE(2, "Nurse"),
    ADMINISTRATIVE_STAFF(3, "Administrative Staff"),
    BACK(4, "Back");

    private final int value;
    private final String stringValue;

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

    public static String getStringValue(int value) {
        for (EmployeeType option : values()) {
            if (option.value == value) {
                return option.stringValue;
            }
        }
        return null;
    }
}

enum DepartmentType {
    DOCTOR(1, "Doctor"),
    NURSE(2, "Nurse"),
    ADMINISTRATIVE_STAFF(3, "Administrative Staff"),
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

    public static String getStringValue(int value) {
        for (DepartmentType option : values()) {
            if (option.value == value) {
                return option.stringValue;
            }
        }
        return null;
    }
}
