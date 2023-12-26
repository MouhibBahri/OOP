package services;

public interface IEntityManager extends ISharedFunctions {
    void ManageEntity();

    default void showOptions(String type, String msg) {
        String[] args = {type, " ".repeat(7 - type.length())};
        println("**********************************************");
        printlnFormat("*  1. Add {0}{1}                            *", args);
        printlnFormat("*  2. View {0}{1}                           *", args);
        printlnFormat("*  3. Update {0}{1}                         *", args);
        printlnFormat("*  4. Delete {0}{1}                         *", args);
        printlnFormat("*  5. View All {0}s{1}                      *", args);
        println("*  6. Go Back                                *");
        println("**********************************************");

        int choice = getIntInput(msg, 1, 6);
        switch (choice) {
            case 1:
                addEntity();
                break;
            case 2:
                viewEntity();
                break;
            case 3:
                updateEntity();
                break;
            case 4:
                deleteEntity();
                break;
            case 5:
                viewAll();
                break;
            case 6:
                return;

        }
        ManageEntity();
    }

    String getEntityID(boolean exists);

    void addEntity();

    void viewEntity();

    void updateEntity();

    void deleteEntity();

    void viewAll();
}

