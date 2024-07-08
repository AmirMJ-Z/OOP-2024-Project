package model.UserComparators;

import model.User;

import java.util.Comparator;

public class LevelComparator implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        if (o1.getLevel() > o2.getLevel()) {
            return 1;
        }

        else if (o1.getLevel() < o2.getLevel()) {
            return 1;
        }

        return 0;
    }
}
