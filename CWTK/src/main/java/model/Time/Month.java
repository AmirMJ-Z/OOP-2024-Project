package model.Time;

public enum Month {
    January(1, "January"),
    February(2, "February"),
    March(3, "March"),
    April(4, "April"),
    May(5, "May"),
    June(6, "June"),
    July(7, "July"),
    August(8, "August"),
    September(9, "September"),
    October(10, "October"),
    November(11, "November"),
    December(12, "December");


    private int month;
    private String name;

    Month(int month, String name) {
        this.month = month;
        this.name = name;
    }

    public static Month getMonthByNum(int month) {
        return switch (month) {
            case 1 -> January;
            case 2 -> February;
            case 3 -> March;
            case 4 -> April;
            case 5 -> May;
            case 6 -> June;
            case 7 -> July;
            case 8 -> August;
            case 9 -> September;
            case 10 -> October;
            case 11 -> November;
            case 12 -> December;
            default -> null;
        };
    }

    public String toString() {
        return name;
    }
}
