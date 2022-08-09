package inter.schedule;

import java.time.LocalTime;

public interface WorkingDayTime {
    LocalTime getStart();
    LocalTime getEnd();
}
