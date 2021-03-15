import java.util.List;
import java.io.IOException;
import java.io.FileWriter;

public class MainClass {

	public static void main(String[] args) {
		MonitoredData m = new MonitoredData();
		List<MonitoredData> lista = m.readData();

		try {
			int totalDays = (int) m.countDays(lista);
			FileWriter myWriter2 = new FileWriter("Task_2.txt");
			String x = Integer.toString(totalDays);
			myWriter2.write(x);
			myWriter2.close();
		} catch (IOException e2) {
			System.out.println("Eroare la fisierul de iesire pentru taskul 2");
		}
		m.countActivities(lista);
		System.out.println("Task 1,2 si 3 executate cu succes!!!");
	}

}
