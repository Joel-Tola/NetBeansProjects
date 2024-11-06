package CA_2;

// Define the enum for menu options
public enum MenuOption {
    SORT_LIST(1, "Sort a Dummy List of Staff"),
    SEARCH_LIST(2, "Search in the Staff List and Return Relevant Information"),
    ADD_STAFF_MEMBER(3, "Add New Staff Member"),
    GENERATE_RANDOM_STAFF(4, "Generate Random Staff"),
    EXIT(5, "Exit");

    private final int value;
    private final String description;

    MenuOption(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static MenuOption fromInt(int value) {
        for (MenuOption option : MenuOption.values()) {
            if (option.getValue() == value) {
                return option;
            }
        }
        return null;
    }
}