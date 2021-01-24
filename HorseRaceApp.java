import java.util.*;
import java.util.stream.Collectors;
public class HorseRaceApp {
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter the distance in furlong: ");
		int distance = input.nextInt();
		System.out.println("Enter the number of horses: ");
		int numberOfHorses = input.nextInt();
		
		Map<String, Boolean> Horses = new HashMap<String, Boolean>();
		String name = "horse";
		int number = 1;
		boolean healthy;	
		
		Random random = new Random();
		
		for (int i = 0; i < numberOfHorses; i++) {
			String horseName = "";  
			
			horseName = name + number;
			healthy = random.nextBoolean();
			
			Horses.put(horseName, healthy);
			
			number++;
		}
		
		System.out.println(Horses);
		
		List<String> healthyHorses = Horses.entrySet()
										   .stream()
										   .filter(horses -> horses.getValue() == true)
										   .map(Map.Entry::getKey)
										   .map(e -> e.toUpperCase())
										   .collect(Collectors.toList());
		System.out.println(healthyHorses);								   
		System.out.print("\n\n\n");			 
		System.out.println("Race starts in:");
		
		try {
			Thread.sleep(500);	
		}catch (Exception e){};
		
		
		
		try {
			for(int i = 3; i >= 1; i--) {
				Thread.sleep(1000);
				System.out.println(i);
			}
		}catch (Exception e){};
		
		
		Map<String, Integer> horseAndDistanceTraveled = healthyHorses.stream()
																	 .collect(Collectors.toMap(e -> e, 0));		
		System.out.println(horseAndDistanceTraveled);
	}
}