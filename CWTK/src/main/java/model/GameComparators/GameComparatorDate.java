package model.GameComparators;

import model.Game;

import model.Time.Time;
import java.util.Comparator;

public class GameComparatorDate implements Comparator<Game> {
    @Override
    public int compare(Game o1, Game o2) {
        Time time1 = o1.getTime();
        Time time2 = o2.getTime();

        if (time1.getYear() > time2.getYear()) {
            return 1;
        }

        else if (time1.getYear() < time2.getYear()) {
            return -1;
        }

        else {
            if (time1.getMonthInNum() > time2.getMonthInNum()) {
                return 1;
            }

            else if (time1.getMonthInNum() < time2.getMonthInNum()) {
                return -1;
            }

            else {
                if (time1.getDay() > time2.getDay()) {
                    return 1;
                }

                else if (time1.getDay() < time2.getDay()) {
                    return -1;
                }

                else {
                    if (time1.getHour() > time2.getHour()) {
                        return 1;
                    }

                    else if (time1.getHour() < time2.getHour()) {
                        return -1;
                    }

                    else {
                        if (time1.getMinute() > time2.getMinute()) {
                            return 1;
                        }

                        else if (time1.getMinute() > time2.getMinute()) {
                            return 1;
                        }

                        else {
                            if (time1.getSecond() > time2.getSecond()) {
                                return 1;
                            }

                            else if (time1.getSecond() < time2.getSecond()) {
                                return -1;
                            }

                            else {
                                return 0;
                            }
                        }
                    }
                }
            }
        }
    }
}
