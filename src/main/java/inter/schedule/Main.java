package inter.schedule;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.*;

import static java.util.stream.Collectors.*;
//НЕ РАБОТАЕТ
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        Map<DayOfWeek, WorkingDayTime> dayTimeMap1 = new HashMap<>();
        dayTimeMap1.put(DayOfWeek.MONDAY, new WorkingDayTimeImpl(LocalTime.of(9, 0), LocalTime.of(20, 0)));
        dayTimeMap1.put(DayOfWeek.TUESDAY, new WorkingDayTimeImpl(LocalTime.of(9, 0), LocalTime.of(20, 0)));
        dayTimeMap1.put(DayOfWeek.WEDNESDAY, new WorkingDayTimeImpl(LocalTime.of(9, 0), LocalTime.of(20, 0)));
        dayTimeMap1.put(DayOfWeek.THURSDAY, new WorkingDayTimeImpl(LocalTime.of(9, 0), LocalTime.of(20, 0)));
        dayTimeMap1.put(DayOfWeek.FRIDAY, new WorkingDayTimeImpl(LocalTime.of(10, 0), LocalTime.of(20, 0)));
        System.out.println(main.getOperationTime(dayTimeMap1));
    }

    String getOperationTime(Map<DayOfWeek, WorkingDayTime> dayTimeMap) {
        dayTimeMap.replaceAll((k, v) -> new WorkingDayTimeImpl(v.getStart(), v.getEnd()));
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            if (!dayTimeMap.containsKey(dayOfWeek)) {
                dayTimeMap.put(dayOfWeek, new WorkingDayTimeImpl(LocalTime.of(0 , 0), LocalTime.of(0, 0)));
            }
        }
        return dayTimeMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(groupingBy(Map.Entry::getValue, mapping(Map.Entry::getKey, toList())))
                .entrySet().stream()
                .sorted(Comparator.comparing(e -> e.getValue().get(0)))
                .map(e -> {
                    WorkingDayTime workingDayTime = e.getKey();
                    List<DayOfWeek> dayOfWeeks = e.getValue();
                    StringBuilder str = new StringBuilder();
                    if (dayOfWeeks.size() == 1) {
                        str.append(dayOfWeeks.get(0).getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("ru-RU")));
                    } else if (dayOfWeeks.size() == 2) {
                        str.append(dayOfWeeks.get(0).getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("ru-RU")));
                        str.append(", ");
                        str.append(dayOfWeeks.get(1).getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("ru-RU")));
                    } else {
                        str.append(dayOfWeeks.get(0).getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("ru-RU")));
                        str.append("-");
                        str.append(dayOfWeeks.get(dayOfWeeks.size() - 1).getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("ru-RU")));
                    }
                    str.append(" ");
                    if (workingDayTime.getStart().equals(workingDayTime.getEnd())) {
                        str.append("выходной");
                    } else {
                        str.append(workingDayTime.getStart());
                        str.append("-");
                        str.append(workingDayTime.getEnd());
                    }
                    return str.toString();
                }).toList()
                .toString().replaceAll("[\\[\\]]", "");
    }
}
