// Enums for menu options
public enum MenuOption {
    SORT(1),
    SEARCH(2),
    ASSESS_ALGORITHMS(3),
    EXIT(4);

    private final int value;

    MenuOption(int value) {
        this.value = value;
    }

    public static MenuOption getValue(int value) {
        for (MenuOption option : values()) {
            if (option.value == value) {
                return option;
            }
        }
        return null;
    }
}

enum SortingOption {
    ByTITLE(1),
    ByPAGES(2),
    BACK(3);

    private final int value;

    SortingOption(int value) {
        this.value = value;
    }

    public static SortingOption getValue(int value) {
        for (SortingOption option : values()) {
            if (option.value == value) {
                return option;
            }
        }
        return null;
    }
}

enum SortingOrder {
    ASCENDING(1),
    DESCENDING(2),
    BACK(3);

    private final int value;

    SortingOrder(int value) {
        this.value = value;
    }

    public static SortingOrder getValue(int value) {
        for (SortingOrder order : values()) {
            if (order.value == value) {
                return order;
            }
        }
        return null;
    }
}

enum SearchOption {
    ByTITLE(1),
    ByPAGES(2),
    BACK(3);

    private final int value;

    SearchOption(int value) {
        this.value = value;
    }

    public static SearchOption getValue(int value) {
        for (SearchOption option : values()) {
            if (option.value == value) {
                return option;
            }
        }
        return null;
    }
}