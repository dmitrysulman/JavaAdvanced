package inter.schedule;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Map<DayOfWeek, WorkingDayTime> dayTimeMap1 = new HashMap<>();
        dayTimeMap1.put(DayOfWeek.MONDAY, new WorkingDayTimeImpl(LocalTime.of(9, 0), LocalTime.of(20, 0)));
        dayTimeMap1.put(DayOfWeek.TUESDAY, new WorkingDayTimeImpl(LocalTime.of(9, 0), LocalTime.of(20, 0)));
        dayTimeMap1.put(DayOfWeek.WEDNESDAY, new WorkingDayTimeImpl(LocalTime.of(9, 0), LocalTime.of(20, 0)));
//        dayTimeMap1.put(DayOfWeek.THURSDAY, new WorkingDayTimeImpl(LocalTime.of(9, 0), LocalTime.of(20, 0)));
        dayTimeMap1.put(DayOfWeek.FRIDAY, new WorkingDayTimeImpl(LocalTime.of(9, 0), LocalTime.of(20, 0)));
        dayTimeMap1.put(DayOfWeek.SATURDAY, new WorkingDayTimeImpl(LocalTime.of(10, 0), LocalTime.of(19, 0)));
        System.out.println(solution.getOperationTime(dayTimeMap1));
    }

    String getOperationTime(Map<DayOfWeek, WorkingDayTime> dayTimeMap) {
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            if (!dayTimeMap.containsKey(dayOfWeek)) {
                dayTimeMap.put(dayOfWeek, null);
            }
        }
        Map<DayOfWeek, WorkingDayTime> sortedDayTimeMap = new TreeMap<>(dayTimeMap);
        List<List<DayOfWeek>> dayOfWeeks = new ArrayList<>();
        List<String> workingDayTimes = new ArrayList<>();
        for (Map.Entry<DayOfWeek, WorkingDayTime> entry : sortedDayTimeMap.entrySet()) {
            DayOfWeek currentDayOfWeek = entry.getKey();
            String currentWorkingDayTime;
            if (entry.getValue() == null) {
                currentWorkingDayTime = "выходной";
            } else {
                currentWorkingDayTime = entry.getValue().getStart().toString() +
                        "-" + entry.getValue().getEnd().toString();
            }
            if (!dayOfWeeks.isEmpty() &&
                    currentWorkingDayTime.equals(workingDayTimes.get(workingDayTimes.size() - 1))) {
                dayOfWeeks.get(workingDayTimes.size() - 1).add(currentDayOfWeek);
            } else {
                List<DayOfWeek> newDayOfWeekList = new ArrayList<>();
                newDayOfWeekList.add(entry.getKey());
                dayOfWeeks.add(newDayOfWeekList);
                workingDayTimes.add(currentWorkingDayTime);
            }
        }
        List<String> result = new ArrayList<>();
        for (int i = 0; i < dayOfWeeks.size(); i++) {
            List<DayOfWeek> dayOfWeeksElement = dayOfWeeks.get(i);
            StringBuilder str = new StringBuilder(getShortDayOfWeekRu(dayOfWeeksElement.get(0)));
            if (dayOfWeeksElement.size() == 2) {
                str.append(", ")
                        .append(getShortDayOfWeekRu(dayOfWeeksElement.get(1)));
            } else if (dayOfWeeksElement.size() > 2) {
                str.append("-")
                        .append(getShortDayOfWeekRu(dayOfWeeksElement.get(dayOfWeeksElement.size() - 1)));
            }
            str.append(" ").append(workingDayTimes.get(i));
            result.add(str.toString());
        }
        return result.toString().replaceAll("[\\[\\]]", "");
    }

    private String getShortDayOfWeekRu(DayOfWeek dayOfWeek) {
        return dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("ru-RU"));
    }
}
