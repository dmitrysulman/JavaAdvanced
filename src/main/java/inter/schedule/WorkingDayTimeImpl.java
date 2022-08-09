package inter.schedule;

import java.time.LocalTime;
import java.util.Objects;

public final class WorkingDayTimeImpl implements WorkingDayTime {
    private final LocalTime start;
    private final LocalTime end;

    public WorkingDayTimeImpl(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (WorkingDayTimeImpl) obj;
        return Objects.equals(this.start, that.start) &&
                Objects.equals(this.end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public String toString() {
        return "WorkingDayTimeImpl[" +
                "start=" + start + ", " +
                "end=" + end + ']';
    }
}
