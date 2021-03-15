import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.FileWriter;

public class MonitoredData {
	private String startTime = new String("");
	private String endTime = new String("");
	private String activity = new String("");

	public MonitoredData() {
		super();
	}

	public MonitoredData(String startTime, String endTime, String activity) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.activity = activity;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getFinishTime() {
		return endTime;
	}

	public String getActivity() {
		return activity;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public List<MonitoredData> readData() {
		List<MonitoredData> data = new ArrayList<MonitoredData>();
		try (Stream<String> stream = Files.lines(Paths.get("Activities.txt"))) {
			List<String> d = stream.collect(Collectors.toList());

			try {
				FileWriter myWriter = new FileWriter("Task_1.txt");
				int dim = d.size();
				int i = 0;
				while (i < dim) {
					MonitoredData m = new MonitoredData();
					String a = d.get(i);
					String str[] = a.split("		");
					m.startTime += str[0];
					m.endTime += str[1];
					m.activity += str[2];
					myWriter.write(m.startTime + "      " + m.endTime + "      " + m.activity);
					myWriter.write("\n");
					data.add(m);
					i++;
				}
				myWriter.close();
			} catch (IOException e1) {
				System.out.println("Eroare la crearea primului fisier de iesire");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}

	public long countDays(List<MonitoredData> lista) {
		return lista.stream().map(x -> x.startTime.substring(8, 10)).distinct().count();
	}

	public void countActivities(List<MonitoredData> lista) {
		Map<String, Long> rez = lista.stream().map(x -> x.activity)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		try {
			FileWriter myWriter3 = new FileWriter("Task_3.txt");
			myWriter3.write(rez.values().toString());
			String s = Arrays.asList(rez).toString();
			myWriter3.write("\n");
			myWriter3.write(s);
			myWriter3.close();
		} catch (IOException e3) {
			System.out.println("Eroare la crearea fisierului pentru task3!");
		}

	}

}
