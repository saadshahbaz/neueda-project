package ca.mcgill.ecse321.arms;

public class ARMS {
    private static String currentuser;
    public static String getCurrentuser() {
        return currentuser;
    }

    public static void setCurrentuser(String currentuser) {
        ARMS.currentuser = currentuser;
    }
}
